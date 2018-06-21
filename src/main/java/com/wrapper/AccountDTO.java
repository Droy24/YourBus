package com.wrapper;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.entity.Account;

public class AccountDTO {
	
	private int accountId;

	private int money;
	
	public  AccountDTO(Account account)
	{
		this.accountId=account.getAccountid();
		this.money=account.getMoney();
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}
}
