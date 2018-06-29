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

import com.entity.Bus;
import com.service.StationService;
import com.wrapper.StationDTO;

@RestController
@RequestMapping("/stations")
public class StationController {

	@Autowired
	private StationService stationService;

	// @GetMapping(name = "/test")
	// public void test() {
	// System.out.println("checking");
	// }

	@PostMapping
	@ResponseBody
	public String newStations(@RequestBody List<StationDTO> acc) {
		return stationService.add(acc);
	}

	@GetMapping(value = "/available/{id1}/{id2}")
	public List<Bus> getAvailable(@PathVariable(value = "id1") int source,
			@PathVariable(value = "id2") int destination) {
		return stationService.findBus(source, destination);
	}

	@GetMapping(value = "/{id}")
	public StationDTO getById(@PathVariable(value = "id") Integer id) {
		return stationService.get(id);
	}

	@GetMapping()
	public List<StationDTO> getAll() {
		return stationService.getAll();
	}

	@DeleteMapping(value = "/{id}")
	@ResponseBody
	public String deleteById(@PathVariable(value = "id") Integer id) {
		return stationService.delete(id);
	}

	@DeleteMapping()
	@ResponseBody
	public String deleteByBody(@RequestBody List<StationDTO> acc) {
		return stationService.delete(acc);
	}
}