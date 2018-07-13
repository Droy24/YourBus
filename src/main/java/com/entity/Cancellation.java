package com.entity;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.joda.time.LocalDate;

import com.wrapper.BookingDTO;
import com.wrapper.CancellationDTO;
import com.wrapper.SeatDTO;

@Entity
@Table(name = "Cancel")
public class Cancellation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long cancellationId;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private Booking booking;

	private int refund;

	@ManyToMany(fetch = FetchType.LAZY,cascade=CascadeType.MERGE)
	@JoinTable(name = "seat_cancellation", 
	joinColumns = { @JoinColumn(name = "cancellationId") }, inverseJoinColumns = {@JoinColumn(name = "seatid") })
	private List<Seat> seatsCancelled;

	private LocalDate dateOfCancellation;

	@ManyToOne
	private User user;

	public Cancellation() {
	}

	public Cancellation(Long cancellationId, Booking booking, int refund, List<Seat> seatscancelled,
			LocalDate dateOfcancellation) 
	{
		this.cancellationId = cancellationId;
		if(booking!=null)
		this.booking = booking;
		this.refund = refund;
		this.seatsCancelled = seatscancelled;
		this.dateOfCancellation = dateOfcancellation;
	}

	public Cancellation(CancellationDTO cancellationDTO) 
	{
		List<SeatDTO> seatsCancelled = cancellationDTO.getSeatsCancelled();
		BookingDTO booking = cancellationDTO.getBookingDTO(); 
			
		this.cancellationId = cancellationDTO.getCancellationId();
		this.dateOfCancellation = cancellationDTO.getDateOfCancellation();
		this.refund = cancellationDTO.getRefund();
		if (booking != null)
			this.booking = new Booking(cancellationDTO.getBookingDTO());
		if (seatsCancelled != null)
			this.seatsCancelled = cancellationDTO.getSeatsCancelled().stream().map(Seat::new).collect(Collectors.toList());
	}

	public Long getCancellationId() {
		return cancellationId;
	}

	public void setCancellationId(Long cancellationId) {
		this.cancellationId = cancellationId;
	}

	public Booking getBooking() {
		return booking;
	}

	public void setBooking(Booking booking) {
		this.booking = booking;
	}

	public int getRefund() {
		return refund;
	}

	public void setRefund(int refund) {
		this.refund = refund;
	}

	public List<Seat> getSeatsCancelled() {
		return seatsCancelled;
	}

	public void setSeatsCancelled(List<Seat> seatscancelled) {
		this.seatsCancelled = seatscancelled;
	}

	public LocalDate getDateOfCancellation() {
		return dateOfCancellation;
	}

	public void setDateOfcancellation(LocalDate dateOfcancellation) {
		this.dateOfCancellation = dateOfcancellation;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void setDateOfCancellation(LocalDate dateOfCancellation) {
		this.dateOfCancellation = dateOfCancellation;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cancellationId == null) ? 0 : cancellationId.hashCode());
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
		Cancellation other = (Cancellation) obj;
		if (cancellationId == null) {
			if (other.cancellationId != null)
				return false;
		} else if (!cancellationId.equals(other.cancellationId))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Cancellation [cancellationId=" + cancellationId + ", booking=" + booking + ", refund=" + refund
				+ ", seatscancelled=" + seatsCancelled + ", dateOfcancellation=" + dateOfCancellation + "]";
	}

}
