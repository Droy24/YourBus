package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.entity.Booking;
import com.entity.Bus;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer>{

	List<Booking> findByBus(Bus bus);

}
