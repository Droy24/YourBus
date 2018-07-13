package com.controller;

import java.util.List;

import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Seat;
import com.entity.User;
import com.service.BookingService;
import com.service.BusService;
import com.service.UserService;
import com.utility.Mail;
import com.wrapper.BookingDTO;

@RestController
@RequestMapping("/booking")
public class BookingController {

	@Autowired
	BusService busService;

	@Autowired
	UserService userService;

	@Autowired
	BookingService bookingService;
	
	/**
	 * get booking by id
	 * 
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/{id}")
	public BookingDTO getBooking(@PathVariable(value = "id") Integer id) {
		return bookingService.get(id);
	}

	@PostMapping(value="/mail")
	@ResponseBody
	public String sendMail(@RequestBody Mail mail)
	{
		return bookingService.sendMail(mail);
	}
	/**
	 * Get number of available seats in a particular bus by giving
	 * busId,sourceId,destinationId in Get request as Parameters
	 * 
	 * @param busId
	 * @param sourceId
	 * @param destinationId
	 * @return
	 */
	
	@GetMapping("/{busId}/{sourceId}/{destinationId}/{date}")
	public int availableSeats(@PathVariable("busId") Long busId, @PathVariable("sourceId") Integer sourceId,
			@PathVariable("destinationId") Integer destinationId, @PathVariable("date") String strdate) {
		LocalDate date = new LocalDate(strdate);
		return busService.availableSeats(busId, sourceId, destinationId, date);
	}

	/**
	 * Get Request to get all booking list
	 * 
	 * @return
	 */
	
	@GetMapping
	public List<BookingDTO> getAllBookings() {
		return bookingService.getAllBookings();
	}
	/**
	 * 
	 * @return
	 */
	@PostMapping("/editedmail")
	public String sendEditedMail() {
		return bookingService.sendEditedMail();
	}
	
	/**
	 * PostRequest to add single booking for admin
	 * 
	 * @param booking
	 * @return
	 */
	
	
	@PostMapping("/new")
	@ResponseBody
	public String addBooking(@RequestBody BookingDTO booking) {
		return bookingService.saveOrUpdateBooking(booking);
	}

	/**
	 * save or update bookings
	 * 
	 * @param id
	 * @param booking
	 * @return
	 */
	
	@PostMapping(value = "/{id}")
	@ResponseBody
	public String saveAndUpdateBooking(@PathVariable(value = "id") Integer id, @RequestBody BookingDTO booking) {
		bookingService.saveOrUpdateBooking(id, booking);
		return "";
	}

	/**
	 * postMapping to do a booking. The booking details to be sent in responseBody
	 * of PostMapping
	 * 
	 * @param bookingDTO
	 * @return
	 */
	
	@PostMapping
	@ResponseBody
	public String newBooking(@RequestBody BookingDTO bookingDTO) {
		return bookingService.add(bookingDTO);
	}

	/**
	 * PostMapping to do booking by sending userId, numberOfSeats , busId, sourceId,
	 * destinationId in url . Method checks is the numberOfSeats is available or not
	 * in given busId. If the number of seats is available ,booking is done.
	 * 
	 * @param userId
	 * @param numberOfSeats
	 * @param busId
	 * @param sourceId
	 * @param destinationId
	 * @return
	 */

	@PostMapping(value = "/{userId}/{numberOfSeats}/{busId}/{sourceId}/{destinationId}/{localdate}")
	@ResponseBody
	public String add(@PathVariable("userId") Long userId, @PathVariable("numberOfSeats") int numberOfSeats,
			@PathVariable("busId") Long busId, @PathVariable("sourceId") Integer sourceId,
			@PathVariable("destinationId") Integer destinationId, @RequestBody List<Seat> seats,
			@PathVariable(value = "localdate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfJourney) {
		User user = new User(userService.get(userId));
		return bookingService.add(busId, seats, sourceId, destinationId, user, dateOfJourney);
	}

	/**
	 * deletes the given list of bookings explicitly as given in responseBody of
	 * delete Mapping
	 * 
	 * @param acc
	 * @return
	 */
	
	@DeleteMapping
	@ResponseBody
	public String deleteAll(@RequestBody List<BookingDTO> acc) {
		return bookingService.delete(acc);
	}

	/**
	 * Deletes booking by id (Cancellation of ticket). Also adds an entry in
	 * cancellation ticket.
	 * 
	 * @param bookingId
	 * @return
	 */

	@DeleteMapping("/{id}")
	@ResponseBody
	public String deleteById(@PathVariable("id") Integer bookingId) {
		System.out.println("in delete by id booking");
		return bookingService.deleteById(bookingId);
	}

}
