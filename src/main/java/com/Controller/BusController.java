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

import com.Bus;
import com.Service.BusService;

@RestController
@RequestMapping("/bus")
public class BusController {

	@Autowired
	BusService busService;

//	@GetMapping(name = "/test")
//	public void test() {
//		System.out.println("checking");
//	}

	@PostMapping
	@ResponseBody
	public String newacc(@RequestBody List<Bus> acc) {
		return busService.add(acc);

	}
	
	@GetMapping(value = "/{id}")
	public Optional<Bus> getid(@PathVariable(value = "id") Integer id) {
		return busService.get(id);
	}

	@GetMapping()
	public List<Bus> getAll() {
		return busService.getAll();
	}

	@DeleteMapping(value = "/{id}")
	@ResponseBody
	public String deleteById(@PathVariable(value = "id") Integer id) {
		return busService.delete(id);
	}

	@DeleteMapping()
	@ResponseBody
	public String deleteByBody(@RequestBody List<Bus> bus) {
		return busService.delete(bus);
	}
}
