package com;

import java.sql.Time;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.Wrapper.BusDTO;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "Bus")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "busId",scope = Bus.class)
public class Bus {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long busId;

	@Column(name = "platename")
	private String plateName;

	@Column(name = "seatsbooked")
	private int seatsbooked;

	@Column(name = "totalseats")
	private int seats;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable
	private List<Seat> seat;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(name = "bus_stops")
	private List<Station> stops;
	
	@OneToOne
	private Route route;

	@Column(name = "dailystarttime")
	private Time dailyStartTime;

	@Column(name = "dailyStoptime")
	private Time dailyStopTime;


	@Column(name = "busType")
	private String busType;

	public Bus(Long busId,String plateName,String busType) {
		this.busId = busId;
		this.plateName = plateName;
		// this.seatsbooked = seatsbooked;
		this.busType = busType;
	}

	public int getSeatsbooked() {
		return this.seat.size();
	}

	public List<Seat> getSeat() {
		return seat;
	}

	public void setSeat(List<Seat> seat) {
		this.seat = seat;
	}

	public String getBustype() {
		return busType;
	}

	public void setBustype(String bustype) {
		this.busType = bustype;
	}

	public void setSeatsbooked(int seatsbooked) {
		this.seatsbooked = seatsbooked;
	}

	public List<Station> getStops() {
		return stops;
	}

	public void setStops(List<Station> stops) {
		this.stops = stops;
	}

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public Bus() {
	}

	public Bus(Long busId, String plateName, String dailyStartTime, String dailyStopTime, String type, Employee owner, int totalseats) {
		this.seats = totalseats;
		// this.seatsbooked=seatsbooked;
		this.busId = busId;
		this.plateName = plateName;
		this.dailyStartTime = java.sql.Time.valueOf(dailyStartTime);
		this.dailyStopTime = java.sql.Time.valueOf(dailyStopTime);
		
		// this.stop = stop;
		this.busType = type;
	}

	public Long getBusId() {
		return busId;
	}

	public void setBusId(Long busId) {
		this.busId = busId;
	}

	public String getPlateName() {
		return plateName;
	}

	public void setPlateName(String plateName) {
		this.plateName = plateName;
	}

	public Time getDailyStartTime() {
		return dailyStartTime;
	}

	public void setDailyStartTime(Time dailyStartTime) {
		this.dailyStartTime = dailyStartTime;
	}

	public Time getDailyStopTime() {
		return dailyStopTime;
	}

	public void setDailyStopTime(Time dailyStopTime) {
		this.dailyStopTime = dailyStopTime;
	}

	public String getType() {
		return busType;
	}

	public void setType(String type) {
		this.busType = type;
	}
	
	public void setBus(BusDTO busdto) {
		
	}
}
