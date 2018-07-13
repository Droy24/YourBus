package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.LocalDate;
import org.joda.time.Period;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Booking;
import com.entity.Cancellation;
import com.entity.Seat;
import com.exception.UnprocessableEntityException;
import com.repository.BookingRepository;
import com.repository.BusRepository;
import com.repository.CancellationRepository;
import com.wrapper.BookingDTO;
import com.wrapper.CancellationDTO;
import com.wrapper.SeatDTO;

@Service
public class CancellationService {

	@Autowired
	CancellationRepository cancellationRepository;

	@Autowired
	BookingRepository bookingRepository;

	@Autowired
	BusRepository busRepository;

	@Autowired
	BookingService bookingService;

	@Autowired
	BusService busService;

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public List<CancellationDTO> getAll() {
		List<CancellationDTO> cancellationList = cancellationRepository.findAll().stream().map(CancellationDTO::new)
				.collect(Collectors.toList());
		if (cancellationList.isEmpty())
			return null;
		return cancellationList;
	}

	int amountRefund(Period timeBetween, int fare) {
		if (timeBetween.getDays() == 0 && timeBetween.getHours() > 0) {
			return fare / 2;
		}
		if (timeBetween.getDays() > 1) {
			return (4 * fare / 5);
		}
		return fare;
	}

	public String add(CancellationDTO cancellationDTO) {
		int refund = 0;
		Integer bookingId = null;
		LocalDate dateOfJourney = null;
		if (cancellationDTO.getBookingDTO().getBookingId() > 0)
			bookingId = cancellationDTO.getBookingDTO().getBookingId();
		else {
			return "Enter valid Bus Id";
		}
		Booking booking = bookingRepository.findById(bookingId).get();
		if (booking == null)
			return "Booking doesnt exists";
		LocalDate cancellationDate = LocalDate.now();
		if (booking.getDateOfJourney() != null)
			dateOfJourney = booking.getDateOfJourney();
		else {
			return "valid dateOfJourney";
		}
		if (cancellationDate.isAfter(dateOfJourney)) 
		{
			return " Ticket cannot be cancelled after Journey date";
		}
		List<Seat> cancelSeats = new ArrayList<>();
		List<Seat> bookedSeats = booking.getSeat();
		if (cancellationDTO.getSeatsCancelled() != null) {
			cancelSeats = cancellationDTO.getSeatsCancelled().stream().map(Seat::new).collect(Collectors.toList());

			if (bookedSeats.containsAll(cancelSeats)) {
				int cancelSeatSize = cancelSeats.size();
				int bookedSeatSize = bookedSeats.size();
				int bookingFare = booking.getFare();
				refund = (cancelSeatSize * bookingFare) / bookedSeatSize;

			} else {
				return "Seat id are invalid";
			}
		} else {
			cancelSeats = booking.getSeat();
			cancellationDTO
					.setSeatsCancelled(booking.getSeat().stream().map(SeatDTO::new).collect(Collectors.toList()));
			refund = booking.getFare();
		}

		Period timeBetween = new Period(cancellationDate, dateOfJourney);
		System.out.println("Period between" + timeBetween.getDays());
		/**
		 * Set refund amount to 50% if cancellation on last date .. invalid if after
		 * dateOf Journey and 75% refund on before 1 day
		 */
		refund = amountRefund(timeBetween, refund);
		// List<Seat> seats =
		// bookingDTO.getSeatDTO().stream().map(Seat::new).collect(Collectors.toList());

		cancellationDTO.setDateOfCancellation(cancellationDate);
		cancellationDTO.setRefund(refund);

		List<Seat> newBooking = new ArrayList<>();
		for (Seat seat : booking.getSeat()) {
			if (!cancelSeats.contains(seat)) {
				newBooking.add(seat);
			}
		}
		System.out.println("===========" + newBooking.size() + "============");
		if (!newBooking.isEmpty()) {
			booking.setSeat(newBooking);
		}

		if (newBooking.isEmpty()) {
			booking.setSeat(newBooking);
			cancellationDTO.setBookingDTO(null);

		} else {
			cancellationDTO.setBookingDTO(new BookingDTO(booking));
		}
		Cancellation cancellation = new Cancellation(cancellationDTO);
		cancellationRepository.save(cancellation);
		return "Cancellation added";
	}

	public String delete(CancellationDTO cancellationDTO) {
		Cancellation cancel = new Cancellation(cancellationDTO);
		Long cancellationId = null;
		if (cancel.getCancellationId() > 0)
			cancellationId = cancel.getCancellationId();
		else {
			logger.error("Please enter valid route.");
			throw new UnprocessableEntityException("Route does not exists.");
		}
		if (cancellationRepository.findById(cancellationId).isPresent()) {
			cancellationRepository.deleteById(cancellationId);
			return "Cancel ticket entry deleted";
		} else {
			return "Id does not exists";
		}
	}

	public String getById(Long cancelId) {
		Cancellation cancel = cancellationRepository.findById(cancelId).get();
		if (cancel == null)
			return "Id not found";
		return cancel.toString();
	}

	public String deleteAll() {
		cancellationRepository.deleteAll();
		return "All cancellation entries deleted";
	}

}
