package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
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
import com.wrapper.BookingDTO;
import com.wrapper.BusDTO;

@Service
public class BookingService {

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

	public BookingDTO get(Integer id) {
		if (bookingRepository.findById(id) == null)
			return null;
		else
			return new BookingDTO(bookingRepository.findById(id).get());
	}

	public String add(BookingDTO bookingDTO) 
{
		int numberOfSeats = bookingDTO.getNumberOfSeats();
		Bus bus = busRepository.findById(bookingDTO.getBusDTO().getBusId()).get();
		Station source = stationRepository.findById(bookingDTO.getFrom().getStationId()).get();
		Station destination =stationRepository.findById(bookingDTO.getDestination().getStationId()).get();
		User user= userRepository.findById(bookingDTO.getUserDTO().getUserid()).get(); 
		LocalDate date =bookingDTO.getDateOfJourney();
		return add(bus.getBusId(),numberOfSeats,source.getStationId(),destination.getStationId(),user,date);
	}

	public String add(Long busId, int numberOfSeats, Integer sourceId, Integer destinationId, User user,LocalDate date) 
	{
		Bus bus = busRepository.findById(busId).get();
		int available = busService.availableSeats(busId, sourceId, destinationId,date);
		if (available > numberOfSeats) {
			List<Seat> totalSeat = bus.getSeat();
			if (totalSeat.isEmpty())
				return "no seats in db";
			List<Seat> seatsBooked = busService.bookedSeats(busId, sourceId, destinationId,date);
			List<Seat> newSeatBooking = new ArrayList<>();
			int i = 0;
			for (Seat seat : totalSeat) {
				if (!seatsBooked.isEmpty() && seatsBooked != null) {
					if (!seatsBooked.contains(seat)) {
						newSeatBooking.add(seat);
						i++;
						if (i == numberOfSeats)
							break;
					}
				} else 
				{
					newSeatBooking.add(seat);
					i++;
					if (i == numberOfSeats)
						break;
				}
			}

			Booking booking = new Booking(bus, stationRepository.findById(sourceId).get(),
					stationRepository.findById(destinationId).get(), newSeatBooking,date,user);
			bookingRepository.save(booking);
			Long id = bus.getBusId();

			int x = bus.getSeatsbooked() + numberOfSeats;
			bus.setSeatsbooked(x);

			bus.setPlateName("Thousand Sunny");
			busRepository.save(bus);

			return "Booking Confirmed " + booking.getBookingId() + " From: " + booking.getFrom().getStationname()
					+ " To: " + booking.getDestination().getStationname();
		}
		return "Bus Seat unavailable";
	}

	public List<Seat> getSeats(Long busId, int numberOfSeats, Integer sourceId, Integer destinationId)
 {
		return null;
	}

	public List<BookingDTO> getAllBookings()
 {
		return bookingRepository.findAll().stream().map(m -> new BookingDTO(m)).collect(Collectors.toList());
	}

	public String saveOrUpdateBooking(BookingDTO booking) 
{
		System.out.println("new booking");
		Booking book = new Booking(booking);
		bookingRepository.save(book);
		return "Successful booking";
	}

	public String saveOrUpdateBooking(Integer id, BookingDTO booking)      		//Remove ID
 {
		System.out.println("new/update single booking");
		id=booking.getBookingId();
		if (id == null)
		{
			bookingRepository.save(new Booking(booking));
			return "Booking added";
		}
		else 
		{
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

	public String delete(List<BookingDTO> acc) 
{
		List<Booking> booking = acc.stream().map(s -> new Booking(s)).collect(Collectors.toList());
		bookingRepository.deleteAll(booking);
		return "Multiple Successful deletion";
	}

	public String deleteById(Integer bookingId) 
{
		Booking booking = bookingRepository.findById(bookingId).get();
		int size = booking.getSeat().size();
		Bus bus = booking.getBus();

		bus.setSeatsbooked(bus.getSeatsbooked() - size);
		busService.saveAndUpdateBus(new BusDTO(bus));
		bookingRepository.deleteById(bookingId);

		return "Successful deletion";
	}
}