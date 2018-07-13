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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class BookingDTO {

	private Integer bookingId;

	private BusDTO busDTO;

//	private UserDTO userDTO;

	private List<SeatDTO> seatDTO;

	private StationDTO from;

	private StationDTO destination;

	private int fare;

	private LocalDate dateOfJourney;

	private int numberOfSeats;

	private LocalDate dateOfBooking;
	
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
		User user = booking.getUser();
		List<Seat> seat = booking.getSeat();
		Station from = booking.getFrom();
		Station destination = booking.getDestination();
		this.dateOfBooking=booking.getDateOfBooking();
		this.bookingId = booking.getBookingId();
		if (bus != null)
			this.busDTO = new BusDTO(bus);
		
		if (seat != null)
			this.seatDTO = seat.stream().map(s -> new SeatDTO(s)).collect(Collectors.toList());
		if (from != null)
			this.from = new StationDTO(from);
		if (destination != null)
			this.destination = new StationDTO(destination);
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

	/*public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
	}
*/
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

	public LocalDate getDateOfBooking() {
		return dateOfBooking;
	}

	public void setDateOfBooking(LocalDate dateOfBooking) {
		this.dateOfBooking = dateOfBooking;
	}
}