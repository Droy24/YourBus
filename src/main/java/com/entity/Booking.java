package com.entity;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.joda.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.wrapper.BookingDTO;

@Entity
@Table(name = "Booking")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "bookingId", scope = Booking.class)
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer bookingId;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private Bus bus;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinColumn(name = "userid", nullable = true) // change nullable to false
	private User user;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "seat_booking", joinColumns = { @JoinColumn(name = "bookingId") }, inverseJoinColumns = {
			@JoinColumn(name = "seatid") })
	private List<Seat> seat;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinTable(name = "Source_booking")
	@JoinColumn(name = "stationId", nullable = true)
	private Station from;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	@JoinTable(name = "Destination_booking")
	@JoinColumn(name = "stationId", nullable = true)
	private Station destination;

	private int fare;

	private LocalDate dateOfJourney;

	private LocalDate dateOfBooking;

	public Booking() {
	}

	public Booking(Integer bookingId, Bus busId, User user, List<Seat> seat, Station from, Station destination,
			int fare, LocalDate dateOfjourney, LocalDate dateOfBooking) {
		this.bookingId = bookingId;
		this.bus = busId;
		this.user = user;
		this.seat = seat;
		this.from = from;
		this.destination = destination;
		this.fare = fare;
		this.dateOfJourney = dateOfjourney;
		this.dateOfBooking = dateOfBooking;
	}

	public Booking(BookingDTO bookingDTO) {
		this.bookingId = bookingDTO.getBookingId();
		if (bookingDTO.getBusDTO() != null)
			this.bus = new Bus(bookingDTO.getBusDTO());
		
		if (bookingDTO.getSeatDTO() != null)
			this.seat = bookingDTO.getSeatDTO().stream().map(s -> new Seat(s)).collect(Collectors.toList());
		if (bookingDTO.getFrom() != null)
			this.from = new Station(bookingDTO.getFrom());
		if (bookingDTO.getDestination() != null)
			this.destination = new Station(bookingDTO.getDestination());
		this.fare = bookingDTO.getFare();
		this.dateOfJourney = bookingDTO.getDateOfJourney();
		this.dateOfBooking = bookingDTO.getDateOfBooking();
	}

	public Booking(Bus bus, Station from, Station destination, List<Seat> seat, LocalDate dateOfJourney, User user,
			int fare, LocalDate dateOfBooking) {
		this.fare = fare;
		this.user = user;
		this.bus = bus;
		this.from = from;
		this.dateOfBooking = dateOfBooking;
		this.destination = destination;
		this.dateOfJourney = dateOfJourney;
		if (!seat.isEmpty()) {
			this.seat = seat;
		}

	}

	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}

	/*
	 * public User getUser() { return user; }
	 * 
	 * public void setUser(User user) { this.user = user; }
	 * 
	 */ public List<Seat> getSeat() {
		return seat;
	}

	public void setSeat(List<Seat> seat) {
		this.seat = seat;
	}

	public Station getFrom() {
		return from;
	}

	public Bus getBus() {
		return this.bus;
	}

	public void setBus(Bus bus) {
		this.bus = bus;
	}

	public LocalDate getDateOfJourney() {
		return dateOfJourney;
	}

	public void setDateOfJourney(LocalDate dateOfJourney) {
		this.dateOfJourney = dateOfJourney;
	}

	public void setFrom(Station from) {
		this.from = from;
	}

	public Station getDestination() {
		return destination;
	}

	public void setDestination(Station destination) {
		this.destination = destination;
	}

	public int getFare() {
		return fare;
	}

	public void setFare(int fare) {
		this.fare = fare;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDate getDateOfBooking() {
		return dateOfBooking;
	}

	public void setDateOfBooking(LocalDate dateOfBooking) {
		this.dateOfBooking = dateOfBooking;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bookingId == null) ? 0 : bookingId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Booking other = (Booking) obj;
		if (bookingId == null) {
			if (other.bookingId != null)
				return false;
		} else if (!bookingId.equals(other.bookingId))
			return false;
		return true;
	}

}