package com.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Bus;
import com.entity.Route;
import com.repository.BusRepository;
import com.repository.RouteRepository;
import com.repository.StationRepository;
import com.wrapper.RouteDTO;

@Service
public class RouteService {

	@Autowired
	private RouteRepository routeRepository;
	
	@Autowired
	private BusRepository busRepository;

	@Autowired 
	private StationRepository stationrepository;
	
	public String add(RouteDTO acc) {
		System.out.println("in Route add");
//		for (RouteDTO a : acc) {
			Route route = new Route(acc);
			if(route!=null)
			{
			routeRepository.save(route);
			}
		
		return "save completed";
	}

	public List<RouteDTO> getAll() {
		System.out.println("Route get all");
		List<Route> routelist = routeRepository.findAll();
		List<RouteDTO> dtolist = new LinkedList<RouteDTO>();
		for (Route dto : routelist)
			dtolist.add(new RouteDTO(dto));
		return dtolist;
	}

	public RouteDTO get(Integer id) {
		System.out.println("Route get");
		Optional<Route> route = routeRepository.findById(id);
		if (route.isPresent()) {
			return new RouteDTO(route.get());
		}
		return null;
	}

	public String delete(Integer id) {
		System.out.println("Route delete");
		Optional<Route> route =routeRepository.findById(id);
		if(route.isPresent())
		{
			Route rt= route.get();
			List<Bus> bus = busRepository.findAll();
			if(bus!=null)
			{
				for(Bus bs:bus) {
					if(bs.getRoute().equals(rt))
						bs.setRoute(null);
				}
				busRepository.saveAll(bus);
			}
			routeRepository.deleteById(id);
			return "Succesful deletion";
		}
		return "No entry";
	}

	public String delete(List<RouteDTO> acc) 
	{
		System.out.println("Route delete all");
		List<Route> routelist = new LinkedList<Route>();
		for (RouteDTO route : acc)
			routelist.add(new Route(route));
		routeRepository.deleteAll(routelist);
		return "Multiple deletion successful";
	}
}
