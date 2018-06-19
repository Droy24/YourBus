package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.service.BookingService;
import com.wrapper.BookingDTO;

@RestController
@RequestMapping("/booking")
public class BookingController {
	
	@Autowired
	BookingService bookingService;
	
	@GetMapping(value="/{id}")
	public BookingDTO getBooking(@PathVariable(value="id") Integer id)
	{
		return bookingService.get(id);
	}
	
	@GetMapping
	public List<BookingDTO> getAllBookings(){
		return bookingService.getAllBookings();
	}
	
	@PostMapping
	@ResponseBody
	public String addBooking(@RequestBody BookingDTO booking)
	{
		return bookingService.saveOrUpdateBooking(booking);
	}
	
	@PostMapping(value="/{id}")
	@ResponseBody
	public String saveAndUpdateBooking(@PathVariable(value="id")Integer id, @RequestBody BookingDTO booking)
	{
		bookingService.saveOrUpdateBooking(id,booking);
		return "";
	}
	
	@PostMapping(value= "/{userId}/{numberOfSeats}/{busId}/{sourceId}/{destinationId}")
	@ResponseBody
	public String add(@PathVariable("userId") Integer userId,@PathVariable("numberOfSeats")int numberOfSeats,
			@PathVariable("busId")Long busId,
	@PathVariable("sourceId") Integer sourceId,@PathVariable("destinationId") Integer destinationId) 
	{
		return bookingService.add(busId, numberOfSeats, sourceId, destinationId, userId);
	}
	
	@DeleteMapping
	@ResponseBody
	public String deleteAll(@RequestBody List<BookingDTO> acc) {
		return bookingService.delete(acc);
	}
}
