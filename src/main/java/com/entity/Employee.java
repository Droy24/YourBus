package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.joda.time.LocalDate;

@Entity
@Table(name = "Employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "empid")
	private int empId;

	@Column(name = "type")
	private String type;

	@Column(name = "name")
	private String name;

	@Column(name = "job")
	private String job;

	@Column(name = "address")
	private String address;

	@Column(name = "Salary")
	private int salary;

	@Column(name = "joindate")
	private LocalDate joinDate;

	public Employee() {
	}

	public Employee(int id, String type, String name, String job, String address, int salary, LocalDate joindate) {
		this.empId = id;
		this.type = type;
		this.name = name;
		this.job = job;
		this.address = address;
		this.salary = salary;
		this.joinDate = joindate;
	}

	public int getId() {
		return empId;
	}

	public void setId(int empid) {
		this.empId = empid;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public LocalDate getJoindate() {
		return joinDate;
	}

	public void setJoindate(LocalDate joindate) {
		this.joinDate = joindate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + empId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (empId != other.empId)
			return false;
		return true;
	}
	
}
