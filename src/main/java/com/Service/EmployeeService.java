package com.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Employee;
import com.Repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeerepository;

	public String add(List<Employee> acc) {
		// TODO Auto-generated method stub
		System.out.println("in Employee add");
		for (Employee a : acc) {
			employeerepository.save(a);
		}
		return "save completed";
	}

	public List<Employee> getAll() {
		// TODO Auto-generated method stub
		System.out.println("Employee get all");
		return employeerepository.findAll();
	}

	public Optional<Employee> get(Integer id) {
		// TODO Auto-generated method stub
		System.out.println("Employee get");
		return employeerepository.findById(id);
	}

	public String delete(Integer id) {
		// TODO Auto-generated method stub
		System.out.println("Employee delete");
		employeerepository.deleteById(id);
		return "Succesful deletion";
	}

	public String delete(List<Employee> acc) {
		// TODO Auto-generated method stub
		System.out.println("Employee delete all");
		employeerepository.deleteAll(acc);
		return "Multiple deletion successful";
	}
}
