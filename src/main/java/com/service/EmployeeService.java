package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Employee;
import com.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeerepository;

	public String add(List<Employee> acc) {
		System.out.println("in Employee add");
		for (Employee a : acc) {
			employeerepository.save(a);
		}
		return "save completed";
	}

	public List<Employee> getAll() {
		System.out.println("Employee get all");
		return employeerepository.findAll();
	}

	public Optional<Employee> get(Integer id) {
		System.out.println("Employee get");
		return employeerepository.findById(id);
	}

	public String delete(Integer id) {
		System.out.println("Employee delete");
		employeerepository.deleteById(id);
		return "Succesful deletion";
	}

	public String delete(List<Employee> acc) {
		System.out.println("Employee delete all");
		employeerepository.deleteAll(acc);
		return "Multiple deletion successful";
	}
}
