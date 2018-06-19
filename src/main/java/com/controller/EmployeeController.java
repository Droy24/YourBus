package com.controller;

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

import com.entity.Employee;
import com.service.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeservice;

	@PostMapping
	@ResponseBody
	public String newacc(@RequestBody List<Employee> acc) {
		return employeeservice.add(acc);

	}

	@GetMapping(value = "/{id}")
	public Optional<Employee> getid(@PathVariable(value = "id") Integer id) {
		return employeeservice.get(id);
	}

	@GetMapping()
	public List<Employee> getAll() {
		return employeeservice.getAll();
	}

	@DeleteMapping(value = "/{id}")
	@ResponseBody
	public String deleteById(@PathVariable(value = "id") Integer id) {
		return employeeservice.delete(id);
	}

	@DeleteMapping()
	@ResponseBody
	public String deleteByBody(@RequestBody List<Employee> acc) {
		return employeeservice.delete(acc);
	}

}
