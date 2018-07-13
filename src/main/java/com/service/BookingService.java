package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Booking;
import com.entity.Bus;
import com.entity.Seat;
import com.entity.Station;
import com.entity.User;
import com.exception.UnprocessableEntityException;
import com.repository.BookingRepository;
import com.repository.BusRepository;
import com.repository.StationRepository;
import com.repository.UserRepository;
import com.security.AuthenticationService;
import com.utility.Mail;
import com.wrapper.BookingDTO;
import com.wrapper.BusDTO;

@Service
public class BookingService {

	@Autowired
	private ApplicationService applicationService;
	
	@Autowired
	private AuthenticationService authenticationService;
	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private BusService busService;

	@Autowired
	private StationRepository stationRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BusRepository busRepository;

	@Autowired
	private MailClient mailclient;

	@Autowired
	private MailContentBuilder mailbuilder;

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public String sendMail(Mail mail) {
		if (mail == null)
			mailclient.prepareAndSend("devasheesh.roy@wittybrains.com", "1st mail");
		else
			mailclient.prepareAndSend(mail.getTo(), mail.getContent());
		return "Mail sent";
	}

	public String sendEditedMail() {
		return "";
	}
	
	public BookingDTO get(Integer id) {
		if (bookingRepository.findById(id) == null) {
			return null;
		} else {
			return new BookingDTO(bookingRepository.findById(id).get());
		}
	}

	public String add(BookingDTO bookingDTO) {
		LocalDate date;
		List<Seat> seats;
		int numberOfSeats;
		Bus bus;
		Station source;
		Station destination;
		validateAddBooking(bookingDTO);

		User user = authenticationService.getAuthenticatedUser();
		System.out.println("user authenticated");
		//		if (bookingDTO.getUserDTO().equals(null)) {
//			return "Fill user Id /details";
//		} else {
//			user = userRepository.findById(bookingDTO.getUserDTO().getUserid()).get();
//		}
		if(user==null)return "invalid user";
		
		
		

		if (!bookingDTO.getSeatDTO().isEmpty()) {

			seats = bookingDTO.getSeatDTO().stream().map(Seat::new).collect(Collectors.toList());
		} else {
			return "Mention the seats for booking";
		}
		if (bookingDTO.getNumberOfSeats() > 0) {
			numberOfSeats = bookingDTO.getNumberOfSeats();
		}
		if (bookingDTO.getBusDTO().equals(null)) {
			return "Fill bus";
		} else {
			bus = busRepository.findById(bookingDTO.getBusDTO().getBusId()).get();

		}
		if (bookingDTO.getFrom().equals(null)) {
			return "Fill source station";
		} else {
			source = stationRepository.findById(bookingDTO.getFrom().getStationId()).get();
		}
		if (bookingDTO.getDestination().equals(null)) {
			return "Fill destination station";
		} else {
			destination = stationRepository.findById(bookingDTO.getDestination().getStationId()).get();
		}
		
		if (bookingDTO.getDateOfJourney().equals(null)) {
			return "Fill date of journey";
		} else {
			date = bookingDTO.getDateOfJourney();
		}

		return add(bus.getBusId(), seats, source.getStationId(), destination.getStationId(), user, date);

	}

	public String add(Long busId, List<Seat> seats, Integer sourceId, Integer destinationId, User user,
			LocalDate date) {
		int numberOfSeats = seats.size();
		Bus bus = busRepository.findById(busId).get();
		int available = busService.availableSeats(busId, sourceId, destinationId, date);
		if (available > numberOfSeats) {
			List<Seat> totalSeat = bus.getSeat();
			if (totalSeat.isEmpty())
				return "no seats in db";
			List<Seat> seatsBooked = busService.bookedSeats(busId, sourceId, destinationId, date);
			List<Seat> newSeatBooking = new ArrayList<>();
			int i = 0;
			for (Seat seat : seats) {
				if (!totalSeat.contains(seat)) {
					return "Invalid Seat";
				}
			}

			for (Seat seat : seatsBooked) {
				if (seats.contains(seat)) {
					return "Seats already occupied , SeatId : " + seat.getSeatid();
				}
			}

			System.out.println("before distance");
			/*
			 * for (Seat seat : totalSeat) { if (!seatsBooked.isEmpty() && seatsBooked !=
			 * null) { if (!seatsBooked.contains(seat)) { newSeatBooking.add(seat); i++; if
			 * (i == numberOfSeats) break; } } else { newSeatBooking.add(seat); i++; if (i
			 * == numberOfSeats) break; } }
			 */

			LocalDate dateOfBooking = LocalDate.now();
			int distance = distanceBetween(busId, sourceId, destinationId);
			int fare = calculateFare(bus.getBusType(), distance) * numberOfSeats;
			Booking booking = new Booking(bus, stationRepository.findById(sourceId).get(),
					stationRepository.findById(destinationId).get(), seats, date, user, fare, dateOfBooking);
			bookingRepository.save(booking);
			Long id = bus.getBusId();

			int x = bus.getSeatsbooked() + numberOfSeats;
			bus.setSeatsbooked(x);
			busRepository.save(bus);

			return "Booking Confirmed " + booking.getBookingId() + " From: " + booking.getFrom().getStationname()
					+ " To: " + booking.getDestination().getStationname();
		}
		return "Bus Seat unavailable";
	}

	private int distanceBetween(Long busId, Integer sourceId, Integer destinationId) {
		validateDistanceBetween(busId, sourceId, destinationId);
		Bus bus = busRepository.findById(busId).get();
		if (bus == null)
			return 0;
		Station source = stationRepository.findById(sourceId).get();
		if (source == null)
			return 0;
		Station destination = stationRepository.findById(destinationId).get();
		if (destination == null)
			return 0;
		int initialIndex = 0, i = 0, destinationIndex = 0;
		Integer initialDistance = null, finalDistance = null;
		List<Station> stationList = bus.getRoute().getStops();
		List<Integer> distance = bus.getRoute().getDistance();
		for (Station stn : stationList) {
			Integer stationId = stn.getStationId();
			if (stationId == sourceId) {
				{
					initialIndex = i;
					initialDistance = distance.get(i);
				}
			}
			if (stationId == destinationId) {
				destinationIndex = i;
				finalDistance = distance.get(i);
			}
			i++;
		}
		int travelDistance = finalDistance - initialDistance;
		return travelDistance;
	}

	public int calculateFare(int i, int distance) {
		int fare = 0;
		if (i == 1) {
			fare = distance * applicationService.getAcFare();
		}
		if (i == 2) {
			fare = distance * applicationService.getNonAcFare();
		}
		if (i == 3) {
			fare = distance * applicationService.getSitting();
		}
		if (i == 4) {
			fare = distance * applicationService.getSittingAcFare();
		}
		return fare;
	}

	public List<Seat> getSeats(Long busId, int numberOfSeats, Integer sourceId, Integer destinationId) {
		return null;
	}

	public List<BookingDTO> getAllBookings() {
		return bookingRepository.findAll().stream().map(m -> new BookingDTO(m)).collect(Collectors.toList());
	}

	public String saveOrUpdateBooking(BookingDTO booking) {
		System.out.println("new booking");
		Booking book = new Booking(booking);
		bookingRepository.save(book);
		return "Successful booking";
	}

	public String saveOrUpdateBooking(Integer id, BookingDTO booking) // Remove ID
	{
		System.out.println("new/update single booking");
		id = booking.getBookingId();
		if (id == null) {
			bookingRepository.save(new Booking(booking));
			return "Booking added";
		} else {
			Booking bk = new Booking(booking);
			Booking book = bookingRepository.findById(id).get();
			book.setBus(bk.getBus());
			book.setDateOfJourney(bk.getDateOfJourney());
			book.setDestination(bk.getDestination());
			book.setFare(bk.getFare());
			book.setFrom(bk.getFrom());
			book.setSeat(bk.getSeat());
			book.setUser(bk.getUser());
			bookingRepository.save(book);
			return "booking updated";
		}
	}

	public String delete(List<BookingDTO> acc) {
		List<Booking> booking = acc.stream().map(s -> new Booking(s)).collect(Collectors.toList());
		bookingRepository.deleteAll(booking);
		return "Multiple Successful deletion";
	}

	public String deleteById(Integer bookingId) {
		Booking booking = bookingRepository.findById(bookingId).get();
		if (booking == null)
			return "Booking id does not exists";
		int size = booking.getSeat().size();
		Bus bus = booking.getBus();

		bus.setSeatsbooked(bus.getSeatsbooked() - size);
		busService.saveAndUpdateBus(new BusDTO(bus));
		bookingRepository.deleteById(bookingId);

		return "Successful deletion";
	}

	public void validateAddBooking(BookingDTO bookingDTO) {
		logger.info("To validate Booking details");
		if (bookingDTO.getBusDTO().getBusId() <= 0) {
			logger.error("Please enter valid booking Id.");
			throw new UnprocessableEntityException("Please enter valid booking Id.");
		}
		if (bookingDTO.getDateOfJourney() == null || bookingDTO.getDateOfJourney().isBefore(LocalDate.now())) {
			logger.error("Please enter valid date.");
			throw new UnprocessableEntityException("Please enter valid date.");
		}
		if (bookingDTO.getSeatDTO() == null || bookingDTO.getSeatDTO().isEmpty()) {
			logger.error("Please enter valid seats.");
			throw new UnprocessableEntityException("Please enter valid seats.");
		}
		if (bookingDTO.getFrom() == null) {
			logger.error("Please enter valid source.");
			throw new UnprocessableEntityException("Please enter valid source.");
		}
		if (bookingDTO.getDestination() == null) {
			logger.error("Please enter valid destination.");
			throw new UnprocessableEntityException("Please enter valid destination.");
		}
		logger.info("Validation of Booking success");
	}

	private void validateDistanceBetween(Long busId, Integer sourceId, Integer destinationId) {
		if (busId < 1) {
			logger.error("Please enter valid bus Id");
			throw new UnprocessableEntityException("Please enter valid bus Id.");
		}
		if (sourceId < 1) {
			logger.error("Please enter valid source Id.");
			throw new UnprocessableEntityException("Please enter valid source Id.");
		}
		if (destinationId < 1) {
			logger.error("Please enter valid destination Id.");
			throw new UnprocessableEntityException("Please enter valid destination Id.");
		}
	}

}