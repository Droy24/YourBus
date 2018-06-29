package com.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Seat;
import com.repository.SeatRepository;
import com.wrapper.SeatDTO;

@Service
public class SeatService {

	@Autowired
	private SeatRepository seatRepository;

	public SeatDTO get(Long id) {
		Optional<Seat> seat = seatRepository.findById(id);
		if(!seat.isPresent())
			return null;
		return new SeatDTO(seat.get());
	}

	public List<SeatDTO> getAll() {
		System.out.println("In seat get all 2");
		return (seatRepository.findAll().stream().map(s -> new SeatDTO(s)).collect(Collectors.toList()) != null)
				? (seatRepository.findAll().stream().map(s -> new SeatDTO(s)).collect(Collectors.toList()))
				: null;
	}

	public String addOrUpdateSeat(Long id, SeatDTO seat) {
		SeatDTO seatdto = new SeatDTO(seatRepository.findById(id).get());
		if (seatdto == null)
			seatdto.setSeatid(id);
		seatdto.setArmyquota(seat.isArmyquota());
		seatdto.setLadiesquota(seat.isLadiesquota());
		seatdto.setOldquota(seat.isOldquota());
		seatdto.setPhysicalquota(seat.isPhysicalquota());
		seatdto.setType(seat.getType());
		seatRepository.save(new Seat(seatdto));
		return "";
	}

	public String add(SeatDTO seat) {
	
		if (seat != null) 
		{
			seatRepository.save(new Seat(seat));
			return "Saved Successfully";
		}
		return "Seat invalid";
	}



	public String delete(Long id) {
		if (!seatRepository.findById(id).isPresent())
			return "No Seat on given id";
		seatRepository.delete(seatRepository.findById(id).get());
		return "Seat Successfully deleted";
	}

	public String delete(List<SeatDTO> seatdto) {
		if (seatdto.isEmpty())
			return "List is Empty";
		seatRepository.deleteAll(seatdto.stream().map(s -> new Seat(s)).collect(Collectors.toList()));
		return "Multiple Seats deleted ";
	}
	
	

}
