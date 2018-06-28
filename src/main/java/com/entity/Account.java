package com.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.wrapper.AccountDTO;

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
	public Account(AccountDTO accountDTO) 
	{
		this.accountId=accountDTO.getAccountId();
		this.money=accountDTO.getMoney();
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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountId;
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
		Account other = (Account) obj;
		if (accountId != other.accountId)
			return false;
		return true;
	}

}
