package com.wrapper;

import org.joda.time.LocalDate;

import com.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDTO {

	private Long userid;

	private String name;

	private String email;

	private String username;

	private String password;

	private String type;

	private String question;

	private String answer;

	private AccountDTO wallet;

	private Long mobile;

	private String address;

	private String insuranceId; // boolean type

	private LocalDate birthday;

	private int age;
	
	private String role;

	public UserDTO() {
	}

	public UserDTO(User user) {
		this.userid = user.getUserid();
		this.name = user.getName();
		this.email = user.getEmail();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.type = user.getType();
		this.question = user.getQuestion();
		this.answer = user.getAnswer();

		if (user.getWallet() != null)
			this.wallet = new AccountDTO(user.getWallet());

		this.mobile = user.getMobile();
		this.address = user.getAddress();
		this.insuranceId = user.getInsurance();
		this.birthday = user.getBirthday();
		this.age = user.getAge();
		this.role=user.getRole();
	}

	public UserDTO(Long userId, String userName) {
		this.userid = userId;
		this.username = userName;
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
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

	public AccountDTO getWallet() {
		return wallet;
	}

	public void setWallet(AccountDTO wallet) {
		this.wallet = wallet;
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

	public String getInsuranceId() {
		return insuranceId;
	}

	public void setInsuranceId(String insuranceId) {
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
