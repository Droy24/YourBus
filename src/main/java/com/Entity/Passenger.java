package com.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="passenger")
public class Passenger {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long passengerid;
	
	//private Route route;
	
	public String getPassengerName() {
		return passengerName;
	}

	public void setPassengerName(String passengername) {
		this.passengerName = passengername;
	}

	private int fare;

	private String passengerName;
	
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
	
	/*
	private Bus bus;
	
	
	public void calculateFare() {
		String type=bus.getBustype();
		if(type.equalsIgnoreCase("ac")||type.equalsIgnoreCase("a.c."))this.fare=20*route.getDistance(source ,destination);
	}
	*/
}
