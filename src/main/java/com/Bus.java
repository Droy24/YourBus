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
	List<Seat> seat;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinTable(name = "bus_stops")
	List<Station> stops;

	@Column(name = "dailystarttime")
	private Time dailyStartTime;

	@Column(name = "dailyStoptime")
	private Time dailyStopTime;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Employee driver;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Employee conductor;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Employee busOwner;

	@Column(name = "busType")
	private String busType;

	public Bus(Long busId, String plateName, int seats, List<Seat> seat, List<Station> stops, String dailyStartTime,
			String dailyStopTime, Employee driver, Employee conductor, Employee busOwner, String bustype) {
		this.busId = busId;
		this.plateName = plateName;
		// this.seatsbooked = seatsbooked;
		this.seats = seats;
		this.seat = seat;
		this.stops = stops;
		this.dailyStartTime = java.sql.Time.valueOf(dailyStartTime);
		this.dailyStopTime = java.sql.Time.valueOf(dailyStartTime);
		this.driver = driver;
		this.conductor = conductor;
		this.busOwner = busOwner;
		this.busType = bustype;
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

	public Bus(Long busId, String plateName, String dailyStartTime, String dailyStopTime, Employee driver,
			Employee conductor, Employee busOwner, String type, Employee owner, int totalseats) {
		this.seats = totalseats;
		// this.seatsbooked=seatsbooked;
		this.busId = busId;
		this.plateName = plateName;
		this.dailyStartTime = java.sql.Time.valueOf(dailyStartTime);
		this.dailyStopTime = java.sql.Time.valueOf(dailyStopTime);
		this.driver = driver;
		this.conductor = conductor;
		this.busOwner = busOwner;
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

	public Employee getDriver() {
		return driver;
	}

	public void setDriver(Employee driver) {
		this.driver = driver;
	}

	public Employee getConductor() {
		return conductor;
	}

	public void setConductor(Employee conductor) {
		this.conductor = conductor;
	}

	public Employee getBusOwner() {
		return busOwner;
	}

	public void setBusOwner(Employee busOwner) {
		this.busOwner = busOwner;
	}

	// public List<Station> getStop() {
	// return stop;
	// }
	//
	// public void setStop(List<Station> stop) {
	// this.stop = stop;
	// }

	public String getType() {
		return busType;
	}

	public void setType(String type) {
		this.busType = type;
	}
}
