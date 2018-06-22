package com.wrapper;

import java.util.List;
import java.util.stream.Collectors;

import com.entity.Seat;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class SeatDTO {

	@JsonIgnore
	private Long seatid;

	@JsonIgnore
	private boolean oldquota;

	@JsonIgnore
	private boolean ladiesquota;

	@JsonIgnore
	private boolean physicalquota;

	@JsonIgnore
	private boolean armyquota;

	@JsonIgnore
	private List<BookingDTO> bookingdto;

	@JsonIgnore
	private String type; // single lower ,single upper, double lower, double upper ,sitting

	@JsonIgnore
	private String seatName;
	// private BusDTO bus;

	public SeatDTO() {
	}

	public SeatDTO(Seat seat) 
	{
		this.seatid = seat.getSeatid();
		this.oldquota = seat.isOldquota();
		this.ladiesquota = seat.isLadiesquota();
		this.physicalquota = seat.isPhysicalquota();
		this.armyquota = seat.isArmyquota();
		this.type = seat.getType();
		this.seatName=seat.getSeatName();
		// if(seat.getBooking()!=null)
		// this.bookingdto=seat.getBooking().stream().map(s->new
		// Booking()).collect(Collectors.toList());
		// this.bus=new BusDTO (seat.getBus());
	}

	public SeatDTO(Long seatId,String seatName) 
	{
		this.seatid=seatId;
		this.seatName=seatName;
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

	public List<BookingDTO> getBookingdto() {
		return bookingdto;
	}

	public void setBookingdto(List<BookingDTO> bookingdto) {
		this.bookingdto = bookingdto;
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
