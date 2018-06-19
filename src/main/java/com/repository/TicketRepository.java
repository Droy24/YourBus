package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer> {

}
