package com.wrapper;

import com.entity.Account;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class AccountDTO {

	@JsonIgnore
	private int accountId;

	@JsonIgnore
	private int money;

	public AccountDTO(Account account) {
		this.accountId = account.getAccountid();
		this.money = account.getMoney();
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
