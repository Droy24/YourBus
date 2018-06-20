package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Booking;
import com.entity.Seat;
import com.entity.User;
import com.repository.BookingRepository;
import com.repository.BusRepository;
import com.repository.StationRepository;
import com.repository.UserRepository;
import com.wrapper.BookingDTO;

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

	public String add(Long busId, int numberOfSeats, Integer sourceId, Integer destinationId, Integer userId) {
		int available=busService.availableSeats(busId, sourceId, destinationId);
		System.out.println("available seats : " +available);
		if ( available> numberOfSeats) {
			List<Seat> totalSeat = busRepository.findById(busId).get().getSeat();
			if (totalSeat.isEmpty())
				return "no seats in db";
			

			List<Seat> seatsBooked = busService.bookedSeats(busId, sourceId, destinationId);
			
			seatsBooked.stream().forEach(s -> System.out.println("booked seats with id in booking service :"+s.getSeatid()));
			List<Seat> newSeatBooking = new ArrayList<>();
			int i = 0;

			for (Seat seat : totalSeat) 
			{
				if (!seatsBooked.isEmpty() && seatsBooked !=null) 
				{
					if (!seatsBooked.contains(seat)) 
					{
						
						newSeatBooking.add(seat);
						i++;
						if (i == numberOfSeats)
							break;
					}
				}
				else 
				{
					newSeatBooking.add(seat);
					i++;
					if (i == numberOfSeats)
						break;
				}
			}

			Booking booking = new Booking(
					busRepository.findById(busId).get(),
					stationRepository.findById(sourceId).get(),
					stationRepository.findById(destinationId).get(),		
					newSeatBooking);
			
			bookingRepository.save(booking);

			return "Booking Confirmed " + booking.getBookingId()+" From: "+booking.getFrom()+" To: "+booking.getDestination();
		}
		return "Bus Seat unavailable";
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

	public String saveOrUpdateBooking(Integer id, BookingDTO booking) {
		System.out.println("new/update single booking");
		Optional<Booking> singlebook = bookingRepository.findById(id);
		if (singlebook == null)
			return "Entry not found";

		booking.setBookingId(id);

		bookingRepository.save(new Booking(booking));
		return "Successful booking";
	}

	public String delete(List<BookingDTO> acc) {
		List<Booking> booking = acc.stream().map(s -> new Booking(s)).collect(Collectors.toList());
		bookingRepository.deleteAll(booking);
		return "Multiple Successful deletion";
	}

	public String deleteById(Integer bookingId) {
		bookingRepository.deleteById(bookingId);
		return "Successful deletion";
	}
}