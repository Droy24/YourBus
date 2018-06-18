package com.Entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "User")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userid;

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

	@OneToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "user_ticket", joinColumns = { @JoinColumn(name = "userid") }, inverseJoinColumns = {
			@JoinColumn(name = "ticketid") })
	@Column(name = "ticketmapped")
	List<Ticket> ticket;

	@Column(name = "mobile")
	String mobile;

	@Column(name = "address")
	String address;

	@Column(name = "insurance")
	int insurance; // boolean type

	@Column(name = "date")
	private Date birthday;

	@Column(name = "age")
	private int age;

	
	public User() {
	}

	public User(String name, String email, String username, String password, String question, String answer,
			String mobile, String address, int insurance, Date birthday, int age) {

		// this.userid = userid;
		this.name = name;
		this.email = email;
		this.username = username;
		this.password = password;
		this.question = question;
		this.answer = answer;
		this.mobile = mobile;
		this.address = address;
		this.insurance = insurance;
		this.birthday = birthday;
		this.age = age;
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

	
	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getInsurance() {
		return insurance;
	}

	public void setInsurance(int insurance) {
		this.insurance = insurance;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
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
