package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Booking;
import com.entity.Bus;
import com.entity.Seat;
import com.entity.Station;
import com.entity.User;
import com.repository.BookingRepository;
import com.repository.BusRepository;
import com.repository.StationRepository;
import com.repository.UserRepository;
import com.utility.Mail;
import com.wrapper.BookingDTO;
import com.wrapper.BusDTO;

@Service
public class BookingService {

	@Autowired
	private ApplicationService applicationService;

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

	public String sendMail(Mail mail) 
	{
		if(mail==null)
		mailclient.prepareAndSend("devasheesh.roy@wittybrains.com", "1st mail");
		else 
			mailclient.prepareAndSend(mail.getTo(), mail.getContent());
		return "Mail sent"; 
	}
	
	public BookingDTO get(Integer id) 
	{
		if (bookingRepository.findById(id) == null) 
		{
			return null;
		}
		else 
		{
			return new BookingDTO(bookingRepository.findById(id).get());
		}
	}

	public String add(BookingDTO bookingDTO) {
		LocalDate date;
		List<Seat> seats;
		int numberOfSeats;
		Bus bus;
		Station source;
		User user;
		Station destination;
		if(!bookingDTO.getSeatDTO().isEmpty()) {
		seats = bookingDTO.getSeatDTO().stream().map(Seat::new).collect(Collectors.toList());}
		else
		{
			return "Mention the seats for booking";
		}
		if(bookingDTO.getNumberOfSeats()>0) {
			numberOfSeats= bookingDTO.getNumberOfSeats();}
		if(bookingDTO.getBusDTO().equals(null)) 
		{
			return "Fill bus";
		}
		else
		{
			bus = busRepository.findById(bookingDTO.getBusDTO().getBusId()).get();
			
		}
		if(bookingDTO.getFrom().equals(null)) 
		{
			return "Fill source station" ;
		}
		else
		{
			source= stationRepository.findById(bookingDTO.getFrom().getStationId()).get();
		}
		if(bookingDTO.getDestination().equals(null))
		{
			return  "Fill destination station";
		}
		else
		{
			destination = stationRepository.findById(bookingDTO.getDestination().getStationId()).get();
		}
		if(bookingDTO.getUserDTO().equals(null)) 
		{
			return "Fill user Id /details";
		}
		else
		{
			user= userRepository.findById(bookingDTO.getUserDTO().getUserid()).get();
		}
		if(bookingDTO.getDateOfJourney().equals(null))
		{
			return "Fill date of journey";
		}
		else
		{
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
		Bus bus = busRepository.findById(busId).get();
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
		int size = booking.getSeat().size();
		Bus bus = booking.getBus();

		bus.setSeatsbooked(bus.getSeatsbooked() - size);
		busService.saveAndUpdateBus(new BusDTO(bus));
		bookingRepository.deleteById(bookingId);

		return "Successful deletion";
	}

}