package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.service.CancellationService;
import com.wrapper.CancellationDTO;

import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cancel")
public class CancellationController {

	@Autowired
	CancellationService cancellationService;
	
	@GetMapping
	public List<CancellationDTO> getAllCancellation() {
		return cancellationService.getAll();
	}
	
	@GetMapping("/{id}")
	public String getCancellation(@PathVariable Long cancelId) {
		return cancellationService.getById(cancelId);
	}
	
	@PostMapping
	@ResponseBody
	public String addCancellation(@RequestBody CancellationDTO cancellationDTO)
	{
		System.out.println("in add cancel ticket");
		return cancellationService.add(cancellationDTO);
	}
	
	@DeleteMapping("/all")
	public String deleteAll()
	{
		return cancellationService.deleteAll();
	}
	
	@DeleteMapping
	@ResponseBody
	public String deteleCancellation(@RequestBody CancellationDTO cancellationDTO) 
	{
		return cancellationService.delete(cancellationDTO);
	}
}
