package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Account")
public class Account {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int accountId;

	@Column(name = "money")
	private int money;

	public Account() {
	}

	public Account(int money) {

		this.money = money;
	}

	public int getAccountid() {
		return accountId;
	}

	public void setAccountid(int accountid) {
		this.accountId = accountid;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

}
