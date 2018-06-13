package com;

import java.util.List;

//import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "Ticket")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "ticketId")
public class Ticket {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int ticketId;

	
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(name = "ticket_passenger")
	List<User> passengers; // map

	@Column(name = "fare")
	private int fare;

	// @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Column(name = "source")
	String source;

	// @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Column(name = "destination")
	String destination;

	@Column(name = "busName")
	String busName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "busid")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Bus busId;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Transaction trans;

	public Ticket() {
	}

	public Ticket(int ticketId, int fare, String source, String destination, Bus busId, Transaction trans) {
		this.ticketId = ticketId;
		this.fare = fare;
		this.source = source;
		this.destination = destination;
	}

	public Ticket(int ticketId, List<String> passengers, int fare, String source, String destination, Bus busId,
			Transaction trans) {

		this.ticketId = ticketId;
		//this.passengers = passengers;
		this.fare = fare;
		this.source = source;
		this.destination = destination;
		this.busId = busId;
		this.trans = trans;
	}

	public Ticket(int ticketId, int fare, Bus busId, String busName, Transaction trans) {
		this.ticketId = ticketId;
		this.busName = busName;
		// this.passengers = passengers;
		this.fare = fare;
		// this.source = source;
		// this.destination = destination;
		this.busId = busId;
		this.trans = trans;
	}
/*
	public int numberofseats() {
		return passengers.size();
	}
*/
	public int getTicketId() {
		return ticketId;
	}

	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}
/*
	public List<String> getPassengers() {
		return passengers;
	}

	public void setPassengers(List<String> passengers) {
		this.passengers = passengers;
	}
*/
	public int getFare() {
		return fare;
	}

	public void setFare(int fare) {
		this.fare = fare;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDestination() {
		return destination;
	}

	public void setDestination(String destination) {
		this.destination = destination;
	}

	public Bus getBusId() {
		return busId;
	}

	public void setBusId(Bus busId) {
		this.busId = busId;
	}

	public Transaction getTrans() {
		return trans;
	}

	public void setTrans(Transaction trans) {
		this.trans = trans;
	}

	public String getBusName() {
		return busName;
	}

	public void setBusName(String busName) {
		this.busName = busName;
	}

}
