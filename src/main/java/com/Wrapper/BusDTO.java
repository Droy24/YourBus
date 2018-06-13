package com.Wrapper;

import java.sql.Time;
import java.util.List;

import com.Bus;
import com.Route;
import com.Seat;
import com.Station;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@SuppressWarnings("deprecation")
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class BusDTO {
	
	public Long getBusId() {
		return busId;
	}
	public void setBusId(Long busId) {
		this.busId = busId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((busId == null) ? 0 : busId.hashCode());
		result = prime * result + ((plateName == null) ? 0 : plateName.hashCode());
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
		BusDTO other = (BusDTO) obj;
		if (busId == null) {
			if (other.busId != null)
				return false;
		} else if (!busId.equals(other.busId))
			return false;
		if (plateName == null) {
			if (other.plateName != null)
				return false;
		} else if (!plateName.equals(other.plateName))
			return false;
		return true;
	}
	
	public BusDTO() {}
	
	public BusDTO(Long busId, String plateName, int seatsbooked, int seats, String busType,
			Time dailyStartTime, Time dailyStopTime)
	{
		this.busId = busId;
		this.plateName = plateName;
		this.seatsbooked = seatsbooked;
		this.seats = seats;
		this.busType = busType;
		this.dailyStartTime = dailyStartTime;
		this.dailyStopTime = dailyStopTime;
	}
	
	public BusDTO(Bus bus) {
		this.busId = bus.getBusId();
		this.plateName = bus.getPlateName();
//		this.seatsbooked = bus.getSeatsbooked();
		this.seats = bus.getSeats();
		this.busType = bus.getBustype();
		
	}
	
	public String getPlateName() {
		return plateName;
	}
	public void setPlateName(String plateName) {
		this.plateName = plateName;
	}
	public int getSeatsbooked() {
		return seatsbooked;
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
	public String getBusType() {
		return busType;
	}
	public void setBusType(String busType) {
		this.busType = busType;
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
	private Long busId;
	private String plateName;
	private int seatsbooked;
//	private List<Station> stops;
	private int seats;
	private String busType;
	private Time dailyStartTime;
	private Time dailyStopTime;
	private List<Integer> stationId;
	private List<Integer> fare;
	private Route route;
	private List<Station> stops; // Stationname
	private List<Seat> seat;
	
}
