package com.entity;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.wrapper.SeatDTO;

@Entity
@Table(name = "Seat")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class, property = "seatid", scope = Seat.class)
public class Seat {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long seatid;

	@Column(name = "oldquota", columnDefinition = "boolean default false")
	private boolean oldquota;

	@Column(name = "ladiesquota", columnDefinition = "boolean default false")
	private boolean ladiesquota;

	@Column(name = "physicalquota", columnDefinition = "boolean default false")
	private boolean physicalquota;

	@Column(name = "armyquota", columnDefinition = "boolean default false")
	private boolean armyquota;

	@Column(name = "type")
	private String type; // single lower ,single upper, double lower, double upper ,sitting

	@Column(name="seatName")
	private String seatName;
	/*
	 * @ManyToOne(fetch = FetchType.LAZY)
	 * 
	 * @JoinColumn(name = "busId", nullable = false)
	 * 
	 * @OnDelete(action = OnDeleteAction.CASCADE)
	 * 
	 * @JsonIgnore private Bus bus;
	 */

	public Seat() {
	}

	public Seat(SeatDTO seatdto) {
		this.seatid = seatdto.getSeatid();
		this.armyquota = seatdto.isArmyquota();
		// if (seatdto.getBookingdto() != null)
		// this.booking =
		// seatdto.getBookingdto().stream().map(Booking::new).collect(Collectors.toList());
		this.ladiesquota = seatdto.isLadiesquota();
		this.oldquota = seatdto.isOldquota();
		this.physicalquota = seatdto.isPhysicalquota();
		this.type = seatdto.getType();
		this.seatName=seatdto.getSeatName();
	}

	public Seat(boolean oldquota, boolean ladiesquota, boolean physicalquota, boolean armyquota) {
		this.oldquota = oldquota;
		this.ladiesquota = ladiesquota;
		this.physicalquota = physicalquota;
		this.armyquota = armyquota;
	}

	public Seat(boolean oldquota, boolean ladiesquota, boolean physicalquota, boolean armyquota, String type) {
		this(oldquota, ladiesquota, physicalquota, armyquota);
		this.type = type;
	}

	public boolean isArmyquota() {
		return armyquota;
	}

	public void setArmyquota(boolean armyquota) {
		this.armyquota = armyquota;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((seatid == null) ? 0 : seatid.hashCode());
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
		Seat other = (Seat) obj;
		if (seatid == null) {
			if (other.seatid != null)
				return false;
		} else if (!seatid.equals(other.seatid))
			return false;
		return true;
	}
	

	/*
	 * public Bus getBus() { return bus; }
	 * 
	 * public void setBus(Bus bus) { this.bus = bus; }
	 */
}
