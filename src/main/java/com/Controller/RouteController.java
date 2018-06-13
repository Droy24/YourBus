package com.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Route;
import com.Service.RouteService;

@RestController
@RequestMapping("/route")
public class RouteController {
	@Autowired
	RouteService routeService;

	@PostMapping
	@ResponseBody
	public String newacc(@RequestBody List<Route> acc) {
		return routeService.add(acc);

	}

	@GetMapping(value = "/{id}")
	public Optional<Route> getid(@PathVariable(value = "id") Integer id) {
		return routeService.get(id);
	}

	@GetMapping()
	public List<Route> getAll() {
		return routeService.getAll();
	}

	@DeleteMapping(value = "/{id}")
	@ResponseBody
	public String deleteById(@PathVariable(value = "id") Integer id) {
		return routeService.delete(id);
	}

	@DeleteMapping()
	@ResponseBody
	public String deleteByBody(@RequestBody List<Route> acc) {
		return routeService.delete(acc);
	}

}
