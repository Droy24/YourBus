package com.Entity;

import java.sql.Time;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.Wrapper.BusDTO;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) 
	{
		this.route = route;
	}

	public String getBusType() {
		return busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	@OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE},orphanRemoval=true)
	@JoinTable
	@JsonIgnore
	private List<Seat> seat;
	
	@OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REMOVE},orphanRemoval=true)
	@JsonIgnore
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

	public int getSeats() {
		return seats;
	}

	public void setSeats(int seats) {
		this.seats = seats;
	}

	public Bus() {}

	public Bus(BusDTO busdto) 
	{
		this.busId=busdto.getBusId();
		this.busType=busdto.getBusType();
		if(busdto.getDailyStartTime()!=null)
			{this.dailyStartTime=busdto.getDailyStartTime();}
		if(busdto.getDailyStopTime()!=null)
		{this.dailyStopTime=busdto.getDailyStopTime();}
		this.plateName=busdto.getPlateName();
		if(busdto.getRoute()!=null)
			this.route=new Route(busdto.getRoute());
		if(busdto.getSeat()!=null)
		{this.seat=busdto.getSeat().stream().map(s->new Seat(s)).collect(Collectors.toList());}
		this.seats=busdto.getSeats();
		this.seatsbooked=busdto.getSeatsbooked();
	}
/*	
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
*/
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
	
	
}
