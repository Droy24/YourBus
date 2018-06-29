package com.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Bus;
import com.entity.Route;
import com.entity.Station;
import com.exception.UnprocessableEntityException;
import com.repository.BusRepository;
import com.repository.RouteRepository;
import com.repository.StationRepository;
import com.wrapper.StationDTO;

@Service
public class StationService {

	@Autowired
	private StationRepository stationRepository;

	@Autowired
	private RouteRepository routeRepository;

	@Autowired
	BusRepository busRepository;

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public String add(List<StationDTO> acc) {
		System.out.println("in Station add");
		for (StationDTO a : acc) {
			Station st = new Station(a);
			stationRepository.save(st);
		}
		return "successfully added";
	}

	public List<StationDTO> getAll() {
		System.out.println("Station get all");
		List<Station> stationlist = stationRepository.findAll();
		List<StationDTO> dtolist = stationlist.stream().map(s -> new StationDTO(s)).collect(Collectors.toList());
		return dtolist;
	}

	public StationDTO get(Integer id) {
		System.out.println("Station get");
		Optional<Station> st = stationRepository.findById(id);
		if (st.isPresent())
			return new StationDTO(st.get());
		return null;
	}

	public String delete(Integer id) {
		System.out.println("Station delete");
		Optional<Station> st = stationRepository.findById(id);
		if (!st.isPresent())
			return "Id does not exists";
		Station station = st.get();
		List<Route> routes = routeRepository.findAll();
		if (routes != null) {
			for (Route r : routes) {
				if (r.getStops().contains(station)) {
					r.getStops().remove(station);
				}
			}
			routeRepository.saveAll(routes);
		}
		stationRepository.deleteById(id);
		return "Succesful deletion";
	}

	public String delete(List<StationDTO> acc) {
		System.out.println("Station delete all");
		List<Station> stationlist = stationRepository.findAll();
		if (stationlist.isEmpty())
			return " No entry in database.";
		List<Station> deletelist = acc.stream().map(s -> new Station(s)).collect(Collectors.toList());

		for (Station s : deletelist) {
			List<Route> routes = routeRepository.findAll();
			if (routes != null) {
				for (Route r : routes) {
					if (r.getStops().contains(s)) {
						r.getStops().remove(s);
					}
				}
				routeRepository.saveAll(routes);
			}
			stationRepository.deleteById(s.getStationId());
		}
		return "Multiple deletion successful";
	}

	public List<Bus> findBus(int source, int destination) {
		Optional<Station> startPoint = stationRepository.findById(source);
		if (startPoint.isPresent()) {
			Optional<Station> endPoint = stationRepository.findById(destination);
			if (endPoint.isPresent()) {
				if (startPoint.get().equals(endPoint.get())) 
				{
					return null;
				}
				List<Bus> allbus = busRepository.findAll();
				Station start = startPoint.get();
				Station end = endPoint.get();
				List<Bus> buses = allbus.stream()
						.filter(s -> s.getRoute().getStops().contains(start) && s.getRoute().getStops().contains(end))
						.collect(Collectors.toList());
				if (buses == null) {
					return null;
				}
				return buses;
			}
			System.out.println("Destination not found");
			return null;
		}
		System.out.println("Source is not found");
		return null;
	}
	
	public void validateStation(StationDTO station)
	{
		if(station.getStationId()<1) {
			logger.error("Please enter valid seat Id.");
			throw new UnprocessableEntityException("Please enter valid seat Id.");
		}
		
	}
}
