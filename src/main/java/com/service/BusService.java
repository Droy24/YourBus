package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Booking;
import com.entity.Bus;
import com.entity.Seat;
import com.entity.Station;
import com.entity.User;
import com.exception.UnprocessableEntityException;
import com.repository.BookingRepository;
import com.repository.BusRepository;
import com.repository.RouteRepository;
import com.repository.StationRepository;
import com.wrapper.BusDTO;
import com.wrapper.StationDTO;

@Service
public class BusService {

	@Autowired
	private BookingService bookingService;

	@Autowired
	private BusRepository busRepository;

	@Autowired
	private BookingRepository bookingRepository;

	@Autowired
	private StationRepository stationRepository;

	@Autowired
	private StationService stationService;

	@Autowired
	private RouteService routeService;

	@Autowired
	private RouteRepository routeRepository;

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public String add(List<BusDTO> dto) 
	{
		logger.info("To save and update Bus.");
		System.out.println("in bus add");
		List<StationDTO> stationList = new ArrayList<>();
		for (BusDTO busdto : dto) {
			validateBus(busdto);
			/*
			 * int numberOfTotalSeats=busdto.getSeats(); for(int
			 * i=0;i<numberOfTotalSeats;i++) { StationDTO st=new StationDTO();
			 * stationList.add(st); } stationService.add(stationList);
			 */ busRepository.save(populateBus(busdto));
		}
		return "save completed";
	}

	private Bus populateBus(BusDTO busdto) {
		Bus bus = new Bus(busdto);
		return bus;
	}

	public List<BusDTO> getAll() {
		System.out.println("Bus get all");
		List<Bus> bs = busRepository.findAll();
		List<BusDTO> busdtolist = bs.stream().map(s -> new BusDTO(s)).collect(Collectors.toList());
		return busdtolist;
	}

	public BusDTO get(Long id) {
		System.out.println("Bus get");
		Optional<Bus> bus = busRepository.findById(id);
		if (bus.isPresent()) {
			return new BusDTO(bus.get());
		}
		return null;
	}

	public String delete(Long id) {
		System.out.println("Account delete");
		Optional<Bus> b = busRepository.findById(id);
		if (b.isPresent()) {
			Bus bus = b.get();
			List<Station> stations = stationRepository.findAll();
			for (Station st : stations) {
				if (st.getBusList().contains(bus))
					st.getBusList().remove(bus);
			}
			stationRepository.saveAll(stations);
			busRepository.deleteById(id);
			return "Successful deletion";
		}

		return "Invalid id";
	}

	public String delete(List<BusDTO> busList) {
		busList.stream().forEach(s -> validateBus(s));
		System.out.println("Account delete all");
		List<Bus> bus = busList.stream().map(s -> new Bus(s)).collect(Collectors.toList());
		busRepository.deleteAll(bus);
		return "Multiple deletion successful";
	}

	public int availableSeats(Long busId, Integer sourceId, Integer destinationId, LocalDate date) {
		List<Seat> totalSeatsList = busRepository.findById(busId).get().getSeat();
		List<Seat> bookedList = bookedSeats(busId, sourceId, destinationId, date);
		return totalSeatsList.size() - bookedList.size();
	}

	public List<Seat> bookedSeats(Long busId, Integer sourceId, Integer destinationId, LocalDate date) {
		Optional<Bus> bus = busRepository.findById(busId);
		if (bus.isPresent()) {
			List<Seat> totalSeatsList = bus.get().getSeat();
			List<Seat> seatBookedList = new ArrayList<>();
			if (sourceId != destinationId) {
				int totalSeats = bus.get().getTotalSeats();
				List<Station> stations = bus.get().getRoute().getStops();
				Station source = stationRepository.findById(sourceId).get();
				Station destination = stationRepository.findById(destinationId).get();
				int flag = 0;
				List<Station> route = new ArrayList<>();
				/**
				 * Create the route the bus will use while traveling in between the 2 stations
				 */
				for (Station s : stations) {
					if (s.getStationId().equals(source.getStationId()) || flag == 1) {
						flag = 1;
						route.add(s);
					}
					if (s.getStationId().equals(destination.getStationId())) {
						flag = 0;
					}
				}

				/**
				 * Create route of given bus between given stations
				 */

				int bookedSeats = 0;
				List<Booking> booklist = bookingRepository.findByBus(bus.get());
				/**
				 * Create list of bookedSeats so that we do not book these seats again
				 */
				for (Booking book : booklist) {
					Station from = book.getFrom();
					Station to = book.getDestination();
					LocalDate journeyDate = book.getDateOfJourney();

					if (route.size() == 2 && route.contains(from) && route.contains(to)) {
						if (book.getDateOfJourney() != null) {
							if (date.isEqual(book.getDateOfJourney())) {
								seatBookedList.addAll(book.getSeat());
								bookedSeats += book.getSeat().size();
							}
						}
					} else {
						if ((route.contains(from) && !route.get(0).equals(from))
								|| (route.contains(to) && !route.get(route.size() - 1).equals(to))) {
							if (book.getDateOfJourney() != null) {
								if (date.isEqual(book.getDateOfJourney())) {
									seatBookedList.addAll(book.getSeat());
									bookedSeats += book.getSeat().size();
								}
							}
						}
					}
				}
				return seatBookedList;
			}
			return null;
		}
		return null;
	}

	public String bookBus(Long busId, Integer sourceId, Integer destinationId, List<Seat> seats, LocalDate date,
			User user) {
		String message = bookingService.add(busId, seats, sourceId, destinationId, user, date);
		return message;
	}

	public String saveAndUpdateBus(BusDTO busDTO) {
		Bus b = new Bus(busDTO);
		if (busDTO.getBusId() == null) {
			busRepository.save(b);
			return "new bus created";
		} else {
			Optional<Bus> optionalBus = busRepository.findById(busDTO.getBusId());
			Bus bus = optionalBus.get();
			if (b.getBusType() != 0)
				bus.setBusType(b.getBusType());
			if (b.getDailyStartTime() != null)
				bus.setDailyStartTime(b.getDailyStartTime());
			if (b.getDailyStopTime() != null)
				bus.setDailyStopTime(b.getDailyStopTime());
			if (b.getPlateName() != null)
				bus.setPlateName(b.getPlateName());
			if (b.getRoute() != null)
				bus.setRoute(b.getRoute());
			if (b.getSeat() != null)
				bus.setSeat(b.getSeat());
			if (b.getTotalSeats() > 0)
				bus.setTotalSeats(b.getTotalSeats());
			if (b.getSeatsbooked() > 0 && b.getTotalSeats() > b.getSeatsbooked())
				bus.setSeatsbooked(b.getSeatsbooked());
			if (b.getBusType() == 1 || b.getBusType() == 2 || b.getBusType() == 3 || b.getBusType() == 4)
				bus.setBusType(b.getBusType());
			busRepository.save(bus);
			return "bus updated";
		}
	}

	public void validateBus(BusDTO busDTO) {
		if (busDTO.getTotalSeats() == 0) {
			logger.error("Please enter valid total seats.");
			throw new UnprocessableEntityException("Please enter valid total seats.");
		}
		if (busDTO.getRoute() == null) {
			if (routeRepository.findById(busDTO.getRoute().getRouteId()).get() == null) {
				logger.error("Please enter valid route.");
				throw new UnprocessableEntityException("Route does not exists.");
			}
			logger.error("Please enter valid route.");
			throw new UnprocessableEntityException(" Please enter valid route.");
		}
		if (busDTO.getTotalSeats() == 0) {
			logger.error("Please enter valid route.");
			throw new UnprocessableEntityException(" Please enter total route.");
		}
	}

}