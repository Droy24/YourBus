package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Ticket;
import com.repository.TicketRepository;

@Service
public class TicketService {

	@Autowired
	private TicketRepository ticketrepository;

	public String add(List<Ticket> acc) {
		System.out.println("in Ticket add");
		for (Ticket a : acc) {
			ticketrepository.save(a);
		}
		return "save completed";
	}

	public List<Ticket> getAll() {
		System.out.println("Ticket get all");
		return ticketrepository.findAll();
	}

	public Optional<Ticket> get(Integer id) {
		System.out.println("Ticket get");
		return ticketrepository.findById(id);
	}

	public String delete(Integer id) {
		System.out.println("Ticket delete");
		ticketrepository.deleteById(id);
		return "Succesful deletion";
	}

	public String delete(List<Ticket> acc) {
		System.out.println("Ticket delete all");
		ticketrepository.deleteAll(acc);
		return "Multiple deletion successful";
	}
}
