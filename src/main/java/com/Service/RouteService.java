package com.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

import com.Route;
import com.Repository.RouteRepository;

@Service
public class RouteService {
	
	RouteRepository routeRepository;
	
	public String add(List<Route> acc) {
		// TODO Auto-generated method stub
		System.out.println("in Route add");
		for (Route a : acc) {
			routeRepository.save(a);
		}
		return "save completed";
	}

	public List<Route> getAll() {
		// TODO Auto-generated method stub
		System.out.println("Route get all");
		return routeRepository.findAll();
	}

	public Optional<Route> get(Integer id) {
		// TODO Auto-generated method stub
		System.out.println("Employee get");
		return routeRepository.findById(id);
	}

	public String delete(Integer id) {
		// TODO Auto-generated method stub
		System.out.println("Employee delete");
		routeRepository.deleteById(id);
		return "Succesful deletion";
	}

	public String delete(List<Route> acc) {
		// TODO Auto-generated method stub
		System.out.println("Route delete all");
		routeRepository.deleteAll(acc);
		return "Multiple deletion successful";
	}

}
