package com.wrapper;

import java.util.List;

import com.entity.Seat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown=true)
public class SeatDTO {

	private Long seatid;

	private boolean oldquota;

	private boolean ladiesquota;

	private boolean physicalquota;

	private boolean armyquota;

	private String type; // single lower ,single upper, double lower, double upper ,sitting

	private String seatName;
	// private BusDTO bus;

	public SeatDTO() {
	}

	public SeatDTO(Seat seat) {
		this.seatid = seat.getSeatid();
		this.oldquota = seat.isOldquota();
		this.ladiesquota = seat.isLadiesquota();
		this.physicalquota = seat.isPhysicalquota();
		this.armyquota = seat.isArmyquota();
		this.type = seat.getType();
		this.seatName = seat.getSeatName();
		// if(seat.getBooking()!=null)
		// this.bookingdto=seat.getBooking().stream().map(s->new
		// Booking()).collect(Collectors.toList());
		// this.bus=new BusDTO (seat.getBus());
	}

	public SeatDTO(Long seatId, String seatName) {
		this.seatid = seatId;
		this.seatName = seatName;
	}

	public Long getSeatid() {
		return seatid;
	}

	public void setSeatid(Long seatid) {
		this.seatid = seatid;
	}

	public boolean isOldquota() {
		return oldquota;
	}

	public void setOldquota(boolean oldquota) {
		this.oldquota = oldquota;
	}

	public boolean isLadiesquota() {
		return ladiesquota;
	}

	public void setLadiesquota(boolean ladiesquota) {
		this.ladiesquota = ladiesquota;
	}

	public boolean isPhysicalquota() {
		return physicalquota;
	}

	public void setPhysicalquota(boolean physicalquota) {
		this.physicalquota = physicalquota;
	}

	public boolean isArmyquota() {
		return armyquota;
	}

	public void setArmyquota(boolean armyquota) {
		this.armyquota = armyquota;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSeatName() {
		return seatName;
	}

	public void setSeatName(String seatName) {
		this.seatName = seatName;
	}
	/*
	 * public BusDTO getBus() { return bus; }
	 * 
	 * public void setBus(BusDTO bus) { this.bus = bus; }
	 */

}
