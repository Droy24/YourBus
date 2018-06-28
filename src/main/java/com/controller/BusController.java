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

import com.service.BusService;
import com.wrapper.BusDTO;

@RestController
@RequestMapping("/bus")
public class BusController {

	@Autowired
	private BusService busService;

	@PostMapping("/many")
	@ResponseBody
	public String newacc(@RequestBody List<BusDTO> acc) {
		System.out.println("in controller");
		return busService.add(acc);
	}

	@PostMapping
	@ResponseBody
	public String saveAndUpdateBus(@RequestBody BusDTO busDTO) {
		return busService.saveAndUpdateBus(busDTO);
	}

	@GetMapping(value = "/{id}")
	public BusDTO getid(@PathVariable(value = "id") Long id) {
		return busService.get(id);
	}

	@GetMapping()
	public List<BusDTO> getAll() {
		return busService.getAll();
	}

	@DeleteMapping(value = "/{id}")
	@ResponseBody
	public String deleteById(@PathVariable(value = "id") Long id) 
	{
		return busService.delete(id);
	}

	@DeleteMapping()
	@ResponseBody
	public String deleteByBody(@RequestBody List<BusDTO> bus) {
		return busService.delete(bus);
	}

	@GetMapping("/{busId}/{from}/{to}")
	public int availableSeats(@PathVariable("busId")Long busId,@PathVariable("from")Integer sourceId,@PathVariable("to")Integer destinationId )
	{
		return availableSeats(busId,sourceId,destinationId);
	}
}
