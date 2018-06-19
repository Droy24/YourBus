package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.service.BookingService;
import com.wrapper.BookingDTO;

@RestController
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
	public String addBooking(@RequestBody List<BookingDTO> booking)
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
	
	@DeleteMapping
	@ResponseBody
	public String deleteAll(@RequestBody List<BookingDTO> acc) {
		return bookingService.delete(acc);
	}
}
