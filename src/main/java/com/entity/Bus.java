package com.entity;

import java.util.ArrayList;
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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.joda.time.DateTime;
import com.wrapper.BusDTO;

@Entity
@Table(name = "Bus")
// @JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class,
// property = "busId", scope = Bus.class)
public class Bus {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long busId;

	@Column(name = "platename")
	private String plateName;

	@Column(name = "seatsbooked")
	private int seatsbooked;

	@Column(name = "totalseats")
	private int totalSeats;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable
	private List<Seat> seat = new ArrayList<>();

	@ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.MERGE })
	private Route route;

	@Column(name = "dailystarttime")
	private DateTime dailyStartTime;

	@Column(name = "dailyStoptime")
	private DateTime dailyStopTime;

	@Column(name = "busType")
	private int busType;

	public Bus() {
	}

	public Bus(BusDTO busdto) {
		// if(busId!=null)
		this.busId = busdto.getBusId();
		this.busType = busdto.getBusType();
		this.totalSeats = busdto.getTotalSeats();
		this.seatsbooked = busdto.getSeatsbooked();
		if (busdto.getDailyStartTime() != null) {
			this.dailyStartTime = busdto.getDailyStartTime();
		}
		if (busdto.getDailyStopTime() != null) 
		{
			this.dailyStopTime = busdto.getDailyStopTime();
		}
		this.plateName = busdto.getPlateName();
		if (busdto.getRoute() != null)
			this.route = new Route(busdto.getRoute());
		if (busdto.getSeat() != null && !busdto.getSeat().isEmpty()) 
		{
			this.seat = busdto.getSeat().stream().map(Seat::new).collect(Collectors.toList());
		} 
		else 
		{
			System.out.println("in bus seat constructor");
			this.seat = createSeats(this.totalSeats);
			System.out.println("seat created " + seat.size());
		}
	}

	public static List<Seat> createSeats(int n) {
		List<Seat> seats = new ArrayList<>();
		for (int i = 0; i < n; i++) {
			Seat seat = new Seat();
			seat.setSeatName("A-"+i);
			seats.add(seat);
		}
		return seats;
	}

	public Bus(Long busId, String plateName, int busType, int seatsBooked) {
		this.busId = busId;
		this.plateName = plateName;
		this.busType = busType;
		this.seatsbooked = seatsBooked;
	}

	public int getSeatsbooked() {
		return this.seatsbooked;
	}

	public Route getRoute() {
		return route;
	}

	public void setRoute(Route route) {
		this.route = route;
	}

	public int getBusType() {
		return busType;
	}

	public void setBusType(int busType) {
		this.busType = busType;
	}

	public List<Seat> getSeat() {
		return seat;
	}

	public void setSeat(List<Seat> seat) {
		this.seat = seat;
	}

	public void setSeatsbooked(int seatsbooked) {
		this.seatsbooked = seatsbooked;
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

	public int getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((busId == null) ? 0 : busId.hashCode());
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
		Bus other = (Bus) obj;
		if (busId == null) {
			if (other.busId != null)
				return false;
		} else if (!busId.equals(other.busId))
			return false;
		return true;
	}
}
