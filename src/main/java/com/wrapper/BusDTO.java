package com.wrapper;

import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.DateTime;

import com.entity.Bus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonIgnoreProperties(ignoreUnknown=true)
public class BusDTO {

	private Long busId;

	private String plateName;

	private int seatsbooked;

	private int totalSeats;

	private int busType;

	private DateTime dailyStartTime;

	private DateTime dailyStopTime;

	private List<Integer> fare;

	private RouteDTO route;

	private List<SeatDTO> seat;

	public BusDTO() {
	}

	public BusDTO(Bus bus) {
		this.busId = bus.getBusId();
		this.plateName = bus.getPlateName();
		this.seatsbooked = bus.getSeatsbooked();
		this.totalSeats = bus.getTotalSeats();
		this.busType = bus.getBusType();
		this.dailyStartTime = bus.getDailyStartTime();
		this.dailyStopTime = bus.getDailyStopTime();
		if (bus.getRoute() != null)
			this.route = new RouteDTO(bus.getRoute());
		if (bus.getSeat() != null)
			this.seat = bus.getSeat().stream().map(s -> new SeatDTO(s)).collect(Collectors.toList());
	}

	public BusDTO(Long busId, String plateName) {
		this.busId = busId;
		this.plateName = plateName;
	}

	public Long getBusId() {
		return busId;
	}

	public void setBusId(Long busId) {
		this.busId = busId;
	}

	public List<Integer> getFare() {
		return fare;
	}

	public void setFare(List<Integer> fare) {
		this.fare = fare;
	}

	public RouteDTO getRoute() {
		return route;
	}

	public void setRoute(RouteDTO route) {
		this.route = route;
	}

	public List<SeatDTO> getSeat() {
		return seat;
	}

	public void setSeat(List<SeatDTO> seat) {
		this.seat = seat;
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

	public int getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	public int getBusType() {
		return busType;
	}

	public void setBusType(int busType) {
		this.busType = busType;
	}

	public DateTime getDailyStartTime() {
		return dailyStartTime;
	}

	public void setDailyStartTime(DateTime dailyStartTime) {
		this.dailyStartTime = dailyStartTime;
	}

	public DateTime getDailyStopTime() {
		return dailyStopTime;
	}

	public void setDailyStopTime(DateTime dailyStopTime) {
		this.dailyStopTime = dailyStopTime;
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

}
