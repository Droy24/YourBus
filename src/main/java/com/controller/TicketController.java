package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Ticket;
import com.service.TicketService;

@RestController
@RequestMapping("/tickets")
public class TicketController {

	@Autowired
	private TicketService ticketService;

	// @GetMapping(name = "/test")
	// public void test() {
	// System.out.println("checking");
	// }

	@PostMapping
	@ResponseBody
	public String newacc(@RequestBody List<Ticket> acc) {
		return ticketService.add(acc);

	}

	@GetMapping(value = "/{id}")
	public Optional<Ticket> getid(@PathVariable(value = "id") Integer id) {
		return ticketService.get(id);
	}

	@GetMapping()
	public List<Ticket> getAll() {
		return ticketService.getAll();
	}

	@DeleteMapping(value = "/{id}")
	@ResponseBody
	public String deleteById(@PathVariable(value = "id") Integer id) {
		return ticketService.delete(id);
	}

	@DeleteMapping()
	@ResponseBody
	public String deleteByBody(@RequestBody List<Ticket> acc) {
		return ticketService.delete(acc);
	}
}
