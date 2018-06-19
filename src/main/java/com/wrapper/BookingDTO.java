package com.wrapper;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.LocalDate;

import com.entity.Booking;
import com.entity.Bus;
import com.entity.Seat;
import com.entity.Station;
import com.entity.User;

public class BookingDTO {
private Integer bookingId;
	
	private BusDTO BusId;
	
	private User user;
	
	private List<SeatDTO> seat;
	
	private StationDTO from;
	
	private StationDTO destination;
	
	private int fare;
	
	private LocalDate dateOfJourney;

	public BookingDTO() {}
	
	public BookingDTO(Booking booking) {
		this.bookingId = booking.getBookingId();
		if(booking.getBus()!=null)
		this.BusId = new BusDTO(booking.getBus());
		if(booking.getUser()!=null)
		this.user = booking.getUser();
		this.seat = booking.getSeat().stream().map(s->new SeatDTO(s)).collect(Collectors.toList());
		if(booking.getFrom()!=null)
		this.from = new StationDTO(booking.getFrom());
		if(booking.getDestination()!=null)
		this.destination = new StationDTO(booking.getDestination());
		this.fare = booking.getFare();
		this.dateOfJourney = booking.getDateOfJourney();
	}

	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	public BusDTO getBusId() {
		return BusId;
	}

	public void setBusId(BusDTO busId) {
		BusId = busId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<SeatDTO> getSeat() {
		return seat;
	}

	public void setSeat(List<SeatDTO> seat) {
		this.seat = seat;
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