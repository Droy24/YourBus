package com.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Ticket;
import com.Repository.TicketRepository;

@Service
public class TicketService {

	@Autowired
	TicketRepository ticketrepository;

	public String add(List<Ticket> acc) {
		// TODO Auto-generated method stub
		System.out.println("in Ticket add");
		for (Ticket a : acc) {
			ticketrepository.save(a);
		}
		return "save completed";
	}

	public List<Ticket> getAll() {
		// TODO Auto-generated method stub
		System.out.println("Ticket get all");
		return ticketrepository.findAll();
	}

	public Optional<Ticket> get(Integer id) {
		// TODO Auto-generated method stub
		System.out.println("Ticket get");
		return ticketrepository.findById(id);
	}

	public String delete(Integer id) {
		// TODO Auto-generated method stub
		System.out.println("Ticket delete");
		ticketrepository.deleteById(id);
		return "Succesful deletion";
	}

	public String delete(List<Ticket> acc) {
		// TODO Auto-generated method stub
		System.out.println("Ticket delete all");
		ticketrepository.deleteAll(acc);
		return "Multiple deletion successful";
	}
}
