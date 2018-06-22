package com.wrapper;

import org.joda.time.LocalDate;

import com.entity.User;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDTO {

	@JsonIgnore
	private Integer userid;

	@JsonIgnore
	private String name;

	@JsonIgnore
	private String email;

	@JsonIgnore
	private String username;

	@JsonIgnore
	private String password;

	@JsonIgnore
	private String type;

	@JsonIgnore
	private String question;

	@JsonIgnore
	private String answer;

	@JsonIgnore
	private AccountDTO wallet;

	@JsonIgnore
	private Long mobile;

	@JsonIgnore
	private String address;

	@JsonIgnore
	private String insuranceId; // boolean type

	@JsonIgnore
	private LocalDate birthday;

	@JsonIgnore
	private int age;
	
	public UserDTO() {}
	
	public UserDTO(User user) {
		this.userid=user.getUserid();
		this.name=user.getName();
		this.email=user.getEmail();
		this.username=user.getUsername();
		this.password=user.getPassword();
		this.type=user.getType();
		this.question=user.getQuestion();
		this.answer=user.getAnswer();
		
		if(user.getWallet()!=null)
		this.wallet=new AccountDTO(user.getWallet());
		
		this.mobile=user.getMobile();
		this.address=user.getAddress();
		this.insuranceId=user.getInsurance();
		this.birthday=user.getBirthday();
		this.age=user.getAge();
	}

	public UserDTO(Integer userId,String userName) {
		this.userid=userId;
		this.username=userName;
	}
	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
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

}
