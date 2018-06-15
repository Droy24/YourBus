package com.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Entity.Bus;
import com.Entity.Station;
import com.Repository.BusRepository;
import com.Repository.StationRepository;
import com.Wrapper.StationDTO;

@Service
public class StationService {

	@Autowired
	private StationRepository stationRepository;

	@Autowired
	BusRepository busRepository;

	public String add(List<StationDTO> acc) {
		System.out.println("in Station add");

		for (StationDTO a : acc) {
			Station st = new Station(a);
			stationRepository.save(st);
		}
		return "successfully added";
	}

	public List<StationDTO> getAll() 
	{
		System.out.println("Station get all");
		List<Station> stationlist = stationRepository.findAll();
		List<StationDTO> dtolist = stationlist.stream().map(s->new StationDTO(s)).collect(Collectors.toList());
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
		stationRepository.deleteById(id);
		return "Succesful deletion";
	}

	public String delete(List<StationDTO> acc) {
		System.out.println("Station delete all");
		List<Station> stationlist = stationRepository.findAll();
		if(stationlist.isEmpty())return " No entry in database.";
		List<Station> deletelist= acc.stream().map(s->new Station(s)).collect(Collectors.toList());
		stationRepository.deleteAll(deletelist);
		return "Multiple deletion successful";
	}

	public String findBus(int source, int destination) {
		System.out.println("====Source===" + source);

		Optional<Station> startPoint = stationRepository.findById(source);
		if (startPoint.isPresent()) {
			Optional<Station> endPoint = stationRepository.findById(destination);
			if (endPoint.isPresent()) {

				// if (startPoint.get().equals(endPoint.get())) {
				// return "Source and Destination should not same.";
				// }

				System.out.println("Start Station Point " + startPoint.get() + " endPoint => " + endPoint.get());
				List<Bus> allbus = busRepository.findAll();

				// busess.stream().flatMap(dt -> {
				// System.out.println("Bus Id=> " + dt.getBusId() + " Bus Name => " +
				// dt.getPlateName());
				// return dt.getStops().stream();
				// }).forEach(System.out::println);

				// List<Bus> buses = busRepository.findBusStops(source,destination);

				Station start= startPoint.get();
				Station end= endPoint.get();
//				List<Bus> busses= start.getBusList().stream().map(s->s.getRoute()).collect(Collectors.toList()).stream().
//						filter(st->st.getStops().contains(end)).collect(Collectors.toList()).
				List<Bus> buses =allbus.stream()
						.filter(s->s.getRoute().getStops().contains(start)&& s.getRoute().getStops().contains(end))
						.collect(Collectors.toList());
				
				if(buses==null)return "Not found";
				System.out.println(buses.toString());
				
				/*
				  List<Bus> buses = busess.stream().filter( dt ->
				  dt.getStops().contains(startPoint.get()) &&
				  dt.getStops().contains(endPoint.get())) .collect(Collectors.toList());
				 */

				/*
				 * List<Bus> buses=busess.stream().filter(r->r.getRoute())
				 * 
				 * 
				 * if (buses.isEmpty()) return "No Buses in between"; buses.forEach(dt -> {
				 * System.out.println("Bus Id=> " + dt.getBusId() + " Bus Name => " +
				 * dt.getPlateName()); });
				 */

				return "success";
			}
			return "Destination is not found";
		}
		return "Source is not found";
	}
}
