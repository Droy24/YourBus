package com.wrapper;

import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.LocalDate;

import com.entity.Booking;
import com.entity.Cancellation;
import com.entity.Seat;
import com.entity.User;

public class CancellationDTO {

	private Long cancellationId;

	private BookingDTO bookingDTO;

	private int refund;

	private List<SeatDTO> seatsCancelled;

	private LocalDate dateOfCancellation;

	private UserDTO userDTO;

	public CancellationDTO() {
	}

	public CancellationDTO(Long cancellationId, BookingDTO bookingDTO, int refund, List<SeatDTO> seatscancelled,
			LocalDate dateOfcancellation, UserDTO userDTO) {
		this.cancellationId = cancellationId;
		this.bookingDTO = bookingDTO;
		this.refund = refund;
		this.seatsCancelled = seatscancelled;
		this.dateOfCancellation = dateOfcancellation;
		this.userDTO = userDTO;
	}

	public CancellationDTO(Cancellation cancellation) {
		Booking booking = cancellation.getBooking();
		List<Seat> seatsCancelled = cancellation.getSeatsCancelled();
		User user = cancellation.getUser();
		this.cancellationId = cancellation.getCancellationId();
		this.dateOfCancellation = cancellation.getDateOfCancellation();
		this.refund = cancellation.getRefund();
		if (booking != null)
			this.bookingDTO = new BookingDTO(booking);
		if (seatsCancelled != null)
			this.seatsCancelled = seatsCancelled.stream().map(SeatDTO::new).collect(Collectors.toList());
		if (user != null)
			this.userDTO = new UserDTO(user);
	}

	public Long getCancellationId() {
		return cancellationId;
	}

	public void setCancellationId(Long cancellationId) {
		this.cancellationId = cancellationId;
	}

	public BookingDTO getBookingDTO() {
		return bookingDTO;
	}

	public void setBookingDTO(BookingDTO bookingDTO) {
		this.bookingDTO = bookingDTO;
	}

	public int getRefund() {
		return refund;
	}

	public void setRefund(int refund) {
		this.refund = refund;
	}

	public List<SeatDTO> getSeatsCancelled() {
		return seatsCancelled;
	}

	public void setSeatsCancelled(List<SeatDTO> seatscancelled) {
		this.seatsCancelled = seatscancelled;
	}

	public LocalDate getDateOfCancellation() {
		return dateOfCancellation;
	}

	public void setDateOfCancellation(LocalDate dateOfcancellation) {
		this.dateOfCancellation = dateOfcancellation;
	}

	public UserDTO getUserDTO() {
		return userDTO;
	}

	public void setUserDTO(UserDTO userDTO) {
		this.userDTO = userDTO;
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
		CancellationDTO other = (CancellationDTO) obj;
		if (cancellationId == null) {
			if (other.cancellationId != null)
				return false;
		} else if (!cancellationId.equals(other.cancellationId))
			return false;
		return true;
	}

}
