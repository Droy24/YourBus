package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.service.RouteService;
import com.wrapper.RouteDTO;

@RestController
@RequestMapping("/route")
public class RouteController {

	@Autowired
	private RouteService routeService;

	@PostMapping
	@ResponseBody
	public String newacc(@RequestBody RouteDTO acc) {
		return routeService.add(acc);
	}

	@GetMapping(value = "/{id}")
	public RouteDTO getid(@PathVariable(value = "id") Integer id) {
		return routeService.get(id);
	}

	@GetMapping()
	public List<RouteDTO> getAll() {
		return routeService.getAll();
	}

	@DeleteMapping(value = "/{id}")
	@ResponseBody
	public String deleteById(@PathVariable(value = "id") Integer id) {
		return routeService.delete(id);
	}

	@DeleteMapping()
	@ResponseBody
	public String deleteByBody(@RequestBody List<RouteDTO> acc) {
		return routeService.delete(acc);
	}
}