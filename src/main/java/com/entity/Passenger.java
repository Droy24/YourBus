package com.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Passenger")
public class Passenger {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long passengerid;

	private int fare;

	private String passengerName;

	public Passenger() {
	}

	public Passenger(Long passengerid, int fare, String passengerName) {
		this.passengerid = passengerid;
		this.fare = fare;
		this.passengerName = passengerName;
	}

	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengername) {
		this.passengerName = passengername;
	}

	public Long getPassengerid() {
		return passengerid;
	}

	public void setPassengerid(Long passengerid) {
		this.passengerid = passengerid;
	}

	public int getFare() {
		return fare;
	}

	public void setFare(int fare) {
		this.fare = fare;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((passengerid == null) ? 0 : passengerid.hashCode());
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
		Passenger other = (Passenger) obj;
		if (passengerid == null) {
			if (other.passengerid != null)
				return false;
		} else if (!passengerid.equals(other.passengerid))
			return false;
		return true;
	}

	/*
	 * private Bus bus;
	 * 
	 * public void calculateFare() { String type=bus.getBustype();
	 * if(type.equalsIgnoreCase("ac")||type.equalsIgnoreCase("a.c."))this.fare=20*
	 * route.getDistance(source ,destination); }
	 */
}