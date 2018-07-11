package com.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.joda.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wrapper.UserDTO;

@Entity
@Table(name = "User")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userid;

	@Column(name = "name")
	private String name;

	@Column(name = "email")
	private String email;

	@Column(name = "username")
	private String username;

	@Column(name = "password")
	private String password;

	@Column(name = "type")
	private String type;

	@Column(name = "question")
	private String question;

	@Column(name = "answer")
	private String answer;

	@OneToOne(cascade = CascadeType.ALL)
	private Account wallet;

	@Column(name = "mobile")
	private Long mobile;

	@Column(name = "address")
	private String address;

	@Column(name = "insuranceId")
	private String insuranceId; // boolean type

	@Column(name = "date")
	private LocalDate birthday;

	@Column(name = "age")
	private int age;

	@Column(name = "role")
	private String role;
	
	// @OneToMany
	// private Booking ticket;

	public User() {
	}

	public User(UserDTO userDTO) {
		this.userid = userDTO.getUserid();
		this.address = userDTO.getAddress();
		this.age = userDTO.getAge();
		this.answer = userDTO.getAnswer();
		this.birthday = userDTO.getBirthday();
		this.email = userDTO.getEmail();
		this.insuranceId = userDTO.getInsuranceId();
		this.mobile = userDTO.getMobile();
		this.name = userDTO.getName();
		this.password = userDTO.getPassword();
		this.question = userDTO.getQuestion();
		this.type = userDTO.getType();
		this.username = userDTO.getUsername();
		this.role=userDTO.getRole();
		if (userDTO.getWallet() != null)
			this.wallet = new Account(userDTO.getWallet());
	}

	public User(String name, String email, String username, String password, String question, String answer,
			Long mobile, String address, String insurance, LocalDate birthday, int age) {

		// this.userid = userid;
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
		this.question = question;
		this.answer = answer;
		this.mobile = mobile;
		this.address = address;
		this.insuranceId = insurance;
		this.birthday = birthday;
		this.age = age;
		
	}
	public User(String name, String email, String username, String password, String question, String answer,
			Long mobile, String address, String insurance, LocalDate birthday, int age,String role) {

		// this.userid = userid;
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
		this.question = question;
		this.answer = answer;
		this.mobile = mobile;
		this.address = address;
		this.insuranceId = insurance;
		this.birthday = birthday;
		this.age = age;
		this.role=role;
		
	}
	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public Long getUserid() {
		return userid;
	}

	public void setUserid(Long userid) {
		this.userid = userid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Account getWallet() {
		return wallet;
	}

	public void setWallet(Account wallet) {
		this.wallet = wallet;
	}

	// public List<Ticket> getTicket() {
	// return ticket;
	// }
	//
	// public void setTicket(List<Ticket> ticket) {
	// this.ticket = ticket;
	// }

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getInsurance() {
		return insuranceId;
	}

	public void setInsurance(String insuranceId) {
		this.insuranceId = insuranceId;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	/*
	 * public Booking getTicket() { return ticket; }
	 * 
	 * public void setTicket(Booking ticket) { this.ticket = ticket; }
	 */
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getInsuranceId() {
		return insuranceId;
	}

	public void setInsuranceId(String insuranceId) {
		this.insuranceId = insuranceId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
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
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		return true;
	}

}
