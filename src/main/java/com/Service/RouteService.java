package com.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Entity.Route;
import com.Repository.RouteRepository;
import com.Wrapper.RouteDTO;

@Service
public class RouteService {

	@Autowired
	private RouteRepository routeRepository;

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
		System.out.println("Employee get");
		Optional<Route> route = routeRepository.findById(id);
		if (route.isPresent()) {
			return new RouteDTO(route.get());
		}
		return null;
	}

	public String delete(Integer id) {
		System.out.println("Employee delete");
		routeRepository.deleteById(id);
		return "Succesful deletion";
	}

	public String delete(List<RouteDTO> acc) {
		System.out.println("Route delete all");
		List<Route> routelist = new LinkedList<Route>();
		for (RouteDTO route : acc)
			routelist.add(new Route(route));
		routeRepository.deleteAll(routelist);
		return "Multiple deletion successful";
	}
}
