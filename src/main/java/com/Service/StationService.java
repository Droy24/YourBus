package com.Service;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Bus;
import com.Station;
import com.Repository.BusRepository;
import com.Repository.StationRepository;

@Service
public class StationService {

	@Autowired
	StationRepository stationRepository;

	@Autowired
	BusRepository busRepository;

	public String add(List<Station> acc) {
		// TODO Auto-generated method stub
		System.out.println("in Station add");
		for (Station a : acc) {
			stationRepository.save(a);
		}
		return "save completed";
	}

	public List<Station> getAll() {
		// TODO Auto-generated method stub
		System.out.println("Station get all");
		return stationRepository.findAll();
	}

	public Optional<Station> get(Integer id) {
		// TODO Auto-generated method stub
		System.out.println("Station get");
		return stationRepository.findById(id);
	}

	public String delete(Integer id) {
		// TODO Auto-generated method stub
		System.out.println("Station delete");
		stationRepository.deleteById(id);
		return "Succesful deletion";
	}

	public String delete(List<Station> acc) {
		// TODO Auto-generated method stub
		System.out.println("Station delete all");
		stationRepository.deleteAll(acc);
		return "Multiple deletion successful";
	}

	public String findBus(int source, int destination) {
		System.out.println("====Source===" + source);
		Optional<Station> startPoint = stationRepository.findById(source);
		if (startPoint.isPresent()) {
			Optional<Station> endPoint = stationRepository.findById(destination);
			if (endPoint.isPresent()) {
//				if (startPoint.get().equals(endPoint.get())) {
//					return "Source and Destination should not same.";
//				}
				System.out.println("Start Station Point " + startPoint.get() + " endPoint => " + endPoint.get());
				List<Bus> busess = busRepository.findAll();

				busess.stream().flatMap(dt -> {
					System.out.println("Bus Id=> " + dt.getBusId() + " Bus Name => " + dt.getPlateName());
					return dt.getStops().stream();
				}).forEach(System.out::println);

//				 List<Bus> buses = busRepository.findBusStops(source,
//						 destination);

				List<Bus> buses = busess.stream().filter(
						dt -> dt.getStops().contains(startPoint.get()) && dt.getStops().contains(endPoint.get()))
						.collect(Collectors.toList());
				if (buses.isEmpty())
					return "No Buses in between";
				buses.forEach(dt -> {
					System.out.println("Bus Id=> " + dt.getBusId() + " Bus Name => " + dt.getPlateName());
				});
				return "success";
			}
			return "Destination is not found";
		}
		return "Source is not found";
	}
}
