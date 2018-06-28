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

import com.service.SeatService;
import com.wrapper.SeatDTO;

@RestController
@RequestMapping("/seat")
public class SeatController {

	@Autowired
	private SeatService seatService;

	@GetMapping
	public List<SeatDTO> getAllSeat() {
		System.out.println("in seat get all");
		return seatService.getAll();
	}

	@GetMapping("/{id}")
	public SeatDTO getSeat(@PathVariable Long id) {
		return seatService.get(id);
	}

	@PostMapping
	@ResponseBody
	public String addSeats(@RequestBody SeatDTO seat) {
		return seatService.add(seat);
	}

	@PostMapping("/{id}")
	@ResponseBody
	public String addOrUpdateSeat(@PathVariable Long id, @RequestBody SeatDTO seat) {
		return seatService.addOrUpdateSeat(id, seat);
	}

	@DeleteMapping(value = "/{id}")
	public String deleteSeatById(@PathVariable Long id) {
		return seatService.delete(id);
	}

	@DeleteMapping
	@ResponseBody
	public String deleteSeats(@RequestBody List<SeatDTO> seatdto) {
		return seatService.delete(seatdto);
	}
}
