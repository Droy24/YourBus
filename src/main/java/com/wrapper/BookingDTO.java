package com.wrapper;

import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.LocalDate;

import com.entity.Booking;

public class BookingDTO {
	private Integer bookingId;

	private BusDTO busDTO;

	private UserDTO userDTO;

	private List<SeatDTO> seatDTO;

	private StationDTO from;

	private StationDTO destination;

	private int fare;

	private LocalDate dateOfJourney;

	private int numberOfSeats;

	public int getNumberOfSeats() {
		return numberOfSeats;
	}

	public void setNumberOfSeats(int numberOfSeats) {
		this.numberOfSeats = numberOfSeats;
	}

	public BookingDTO() {
	}

	public BookingDTO(Booking booking) {
		this.bookingId = booking.getBookingId();
		if (booking.getBus() != null)
			this.busDTO = new BusDTO(booking.getBus());
		if (booking.getUser() != null)
			this.userDTO = new UserDTO(booking.getUser());
		if (booking.getSeat() != null)
			this.seatDTO = booking.getSeat().stream().map(s -> new SeatDTO(s)).collect(Collectors.toList());
		if (booking.getFrom() != null)
			this.from = new StationDTO(booking.getFrom());
		if (booking.getDestination() != null)
			this.destination = new StationDTO(booking.getDestination());
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