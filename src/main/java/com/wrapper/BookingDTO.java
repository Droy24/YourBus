package com.wrapper;

import java.util.List;
import java.util.stream.Collectors;
import org.joda.time.LocalDate;
import com.entity.Booking;
import com.entity.Bus;
import com.entity.Seat;
import com.entity.Station;
import com.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class BookingDTO {
	
	@JsonIgnore
	private Integer bookingId;

	@JsonIgnore
	private BusDTO busDTO;

	@JsonIgnore
	private UserDTO userDTO;

	@JsonIgnore
	private List<SeatDTO> seatDTO;

	@JsonIgnore
	private StationDTO from;

	@JsonIgnore
	private StationDTO destination;

	@JsonIgnore
	private int fare;

	@JsonIgnore
	private LocalDate dateOfJourney;

	@JsonIgnore
	private int numberOfSeats;

	@JsonIgnore
	public int getNumberOfSeats() {
		return numberOfSeats;
	}

	@JsonIgnore
	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	public BookingDTO() {
	}

	public BookingDTO(Booking booking) {
		Bus bus = booking.getBus();
		User user= booking.getUser();
		List<Seat> seat = booking.getSeat();
		Station from =booking.getFrom();
		Station destination =booking.getDestination();
		
		this.bookingId = booking.getBookingId();
		if (bus != null)
			this.busDTO = new BusDTO(bus.getBusId(), bus.getPlateName());
		if (user != null)
			this.userDTO = new UserDTO(user.getUserid(),user.getName());
		if (seat != null)
			this.seatDTO = seat.stream().map(s -> new SeatDTO(s.getSeatid(),s.getSeatName())).collect(Collectors.toList());
		if (from != null)
			this.from = new StationDTO(from.getStationId(),from.getStationname());
		if (destination != null)
			this.destination = new StationDTO(destination.getStationId(),destination.getStationname());
		this.fare = booking.getFare();
		this.dateOfJourney = booking.getDateOfJourney();
		if (this.seatDTO != null)
			this.numberOfSeats = booking.getSeat().size();
	}

	/*
	 * public BookingDTO(Booking booking,String ss) { this.bookingId =
	 * booking.getBookingId(); if(booking.getBus()!=null) this.BusId = new
	 * BusDTO(booking.getBus()); if(booking.getUser()!=null) this.user =
	 * booking.getUser(); this.seat = booking.getSeat().stream().map(s->new
	 * SeatDTO(s)).collect(Collectors.toList()); if(booking.getFrom()!=null)
	 * this.from = new StationDTO(booking.getFrom());
	 * if(booking.getDestination()!=null) this.destination = new
	 * StationDTO(booking.getDestination()); this.fare = booking.getFare();
	 * this.dateOfJourney = booking.getDateOfJourney(); }
	 */
	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	public BusDTO getBusDTO() {
		return busDTO;
	}

	public void setBusDTO(BusDTO busDTO) {
		this.busDTO = busDTO;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}

	public List<SeatDTO> getSeatDTO() {
		return seatDTO;
	}

	public void setSeatDTO(List<SeatDTO> seatDTO) {
		this.seatDTO = seatDTO;
	}

	public StationDTO getFrom() {
		return from;
	}

	public void setFrom(StationDTO from) {
		this.from = from;
	}

	public StationDTO getDestination() {
		return destination;
	}

	public void setDestination(StationDTO destination) {
		this.destination = destination;
	}

	public int getFare() {
		return fare;
	}

	public void setFare(int fare) {
		this.fare = fare;
	}

	public LocalDate getDateOfJourney() {
		return dateOfJourney;
	}

	public void setDateOfJourney(LocalDate dateOfJourney) {
		this.dateOfJourney = dateOfJourney;
	}
}