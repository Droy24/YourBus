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

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.joda.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.wrapper.BookingDTO;

@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "bookingId", scope = Booking.class)
public class Booking {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer bookingId;

	@ManyToOne(fetch = FetchType.LAZY,cascade=CascadeType.MERGE)
	@JsonIgnore
	private Bus bus;
/*
	@ManyToOne(fetch = FetchType.LAZY,cascade=CascadeType.MERGE)
	@JoinColumn(name = "userid", nullable = true) // change nullable to false
	@JsonIgnore
	private User user;

*/
	
	@ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinTable(name = "seat_booking", 
             joinColumns = {@JoinColumn(name = "bookingId") }, 
             inverseJoinColumns = { @JoinColumn(name = "seatid") })
	private List<Seat> seat;

	@ManyToOne(fetch = FetchType.LAZY,cascade=CascadeType.MERGE)
	@JoinTable(name = "Source_booking")
	@JoinColumn(name = "stationId", nullable = true)
	@JsonIgnore
	private Station from;

	@ManyToOne(fetch = FetchType.LAZY,cascade=CascadeType.MERGE)
	@JoinTable(name = "Destination_booking")
	@JoinColumn(name = "stationId", nullable = true)
	@OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private Station destination;

	private int fare;

	private LocalDate dateOfJourney;

	public Booking() {
	}

	public Booking(Integer bookingId, Bus busId, User user, List<Seat> seat, Station from, Station destination,
			int fare, LocalDate dateOfjourney) {
		this.bookingId = bookingId;
		this.bus = busId;
	//	this.user = user;
		this.seat = seat;
		this.from = from;
		this.destination = destination;
		this.fare = fare;
		this.dateOfJourney = dateOfjourney;
	}

	public Booking(BookingDTO bookingDTO) {
		this.bookingId = bookingDTO.getBookingId();
		this.bus = new Bus(bookingDTO.getBusId());
	//	this.user = bookingDTO.getUser();
		this.seat = bookingDTO.getSeat().stream().map(s -> new Seat(s)).collect(Collectors.toList());
		this.from = new Station(bookingDTO.getFrom());
		this.destination = new Station(bookingDTO.getDestination());
		this.fare = bookingDTO.getFare();
		this.dateOfJourney = bookingDTO.getDateOfJourney();
	}
	
	public Booking(Bus bus,Station from,Station destination,List<Seat> seat) {
	this.bus=bus;
	this.from=from;
	this.destination=destination;
	if(!seat.isEmpty()) this.seat=seat;
	
	}

	public Integer getBookingId() {
		return bookingId;
	}

	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
/*
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

*/	public List<Seat> getSeat() {
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
}