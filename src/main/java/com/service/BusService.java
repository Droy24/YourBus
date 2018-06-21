package com.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Booking;
import com.entity.Bus;
import com.entity.Seat;
import com.entity.Station;
import com.repository.BookingRepository;
import com.repository.BusRepository;
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

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public String add(List<BusDTO> dto) {
		logger.info("To save and update Bus.");
		System.out.println("in bus add");
		List<StationDTO> stationList = new ArrayList<>();
		for (BusDTO busdto : dto) {
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

	public String delete(List<BusDTO> acc) {
		System.out.println("Account delete all");
		List<Bus> bus = acc.stream().map(s -> new Bus(s)).collect(Collectors.toList());
		busRepository.deleteAll(bus);
		return "Multiple deletion successful";
	}

	public int availableSeats(Long busId, Integer sourceId, Integer destinationId) {
		List<Seat> totalSeatsList = busRepository.findById(busId).get().getSeat();
		List<Seat> bookedList = bookedSeats(busId, sourceId, destinationId);
		return totalSeatsList.size() - bookedList.size();
	}

	public List<Seat> bookedSeats(Long busId, Integer sourceId, Integer destinationId) {
		Optional<Bus> bus = busRepository.findById(busId);
		if (bus.isPresent()) {

			List<Seat> totalSeatsList = bus.get().getSeat();
			List<Seat> seatBookedList = new ArrayList<>();

			if (sourceId != destinationId) {
				int totalSeats = bus.get().getTotalSeats();
				List<Station> stations = bus.get().getRoute().getStops();
				if (stations.isEmpty())
					System.out.println("no stations in route 1");
				Station source = stationRepository.findById(sourceId).get();
				Station destination = stationRepository.findById(destinationId).get();
				int flag = 0;
				List<Station> route = new ArrayList<>();
				for (Station s : stations) {
					if (s.getStationId().equals(source.getStationId()) || flag == 1) {
						flag = 1;
						route.add(s);
					}
					if (s.getStationId().equals(destination.getStationId())) {
						flag = 0;
					}
				}
				int bookedSeats = 0;
				List<Booking> booklist = bookingRepository.findByBus(bus.get());

				for (Booking book : booklist) {
					Station from = book.getFrom();
					Station to = book.getDestination();
					if (route.size() == 2 && route.contains(from) && route.contains(to)) {
						seatBookedList.addAll(book.getSeat());
						bookedSeats += book.getSeat().size();
					} else {
						if ((route.contains(from) && !route.get(0).equals(from))
								|| (route.contains(to) && !route.get(route.size() - 1).equals(to))) {
							seatBookedList.addAll(book.getSeat());
							bookedSeats += book.getSeat().size();
						}
					}
				}
				// System.out.println("in bookedSeats method 7");
				seatBookedList.stream().forEach(s -> System.out.println(" " + s.getSeatid()));
				return seatBookedList;

			}
			return null;
		}
		return null;
	}

	// public List<Bus> getinbetween(int source, int destination) {
	// // TODO Auto-generated method stub
	// // List<Bus> lst=new LinkedList<>();
	// // for(Bus ls:Bus) {
	// //
	// // }
	// return busrepository.findAllByInbetweenContaining(source,destination);
	//
	// }

	public String bookBus(Long busId, Integer sourceId, Integer destinationId, int numberOfSeats) {
		String message = bookingService.add(busId, sourceId, destinationId, numberOfSeats, numberOfSeats);
		return message;
	}

	public String saveAndUpdateBus(BusDTO busDTO) {
		Bus b = new Bus(busDTO);
		Optional<Bus> optionalBus = busRepository.findById(b.getBusId());
		Bus bus = optionalBus.get();

		if (b.getBusId() == null) {
			busRepository.save(b);
			return "new bus created";
		} else {
			bus.setBusType(b.getBusType());
			bus.setDailyStartTime(b.getDailyStartTime());
			bus.setDailyStopTime(b.getDailyStopTime());
			bus.setPlateName(b.getPlateName());
			if (b.getRoute() != null)
				bus.setRoute(b.getRoute());
			if (b.getSeat() != null)
				bus.setSeat(b.getSeat());
			bus.setSeatsbooked(b.getSeatsbooked());
			bus.setTotalSeats(b.getTotalSeats());
			bus.setType(b.getType());

			busRepository.save(bus);
			return "bus updated";
		}
	}
}
