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
		if(bookingRepository.findById(id)==null)
		return null;
		else 
		return new BookingDTO(bookingRepository.findById(id).get());
	}

	public String add(Long busId,int numberOfSeats, Integer sourceId, Integer destinationId,Integer userId) {
		List<Seat> seatList=new ArrayList<>();
		if(busService.availableSeats(busId,sourceId,destinationId)>numberOfSeats)
		{
			seatList=getSeats(busId,numberOfSeats,sourceId,destinationId);
			Booking booking = new Booking();
			booking.setBus(busRepository.findById(busId).get());
			booking.setFrom(stationRepository.findById(sourceId).get());
			booking.setDestination(stationRepository.findById(destinationId).get());
			
			booking.setSeat(seatList);
			User user =userRepository.findById(userId).get();
			booking.setUser(user);
			bookingRepository.save(booking);
		}
		return "Bus Seat unavailable";
	}
	
	public List<Seat> getSeats(Long busId,int numberOfSeats,Integer sourceId,Integer destinationId){
		
		return null;
	}

	public List<BookingDTO> getAllBookings() {
		return bookingRepository.findAll().stream().map(m->new BookingDTO(m)).collect(Collectors.toList());
	}

	public String saveOrUpdateBooking(List<BookingDTO> booking) {
		System.out.println("new booking");
		List<Booking> book= booking.stream().map(m->new Booking(m)).collect(Collectors.toList());
		bookingRepository.saveAll(book);
		return "Successful booking";
	}
	
	public String saveOrUpdateBooking(Integer id,BookingDTO booking) {
		System.out.println("new/update single booking");
		Optional<Booking> singlebook= bookingRepository.findById(id);
		if(singlebook==null) return "Entry not found";
		
		booking.setBookingId(id);
		
		bookingRepository.save(new Booking(booking));
		return "Successful booking";
	}

	public String delete(List<BookingDTO> acc) 
	{
		List<Booking> booking = acc.stream().map(s->new Booking(s)).collect(Collectors.toList());
		bookingRepository.deleteAll(booking);
		return "Multiple Successful deletion";
	}
	
	public String deleteById(Integer bookingId)
	{
		bookingRepository.deleteById(bookingId);
		return "Successful deletion";
	}
}