package com.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.joda.time.LocalDate;

@Entity
@Table(name = "Transaction")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int transactionId;

	@Column(name = "bankname")
	private String bank;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Account accId; // maps

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private User uId;

	@Column(name = "transactiondate")
	private LocalDate dateOfTransaction;

	public Transaction() {
	}

	public Transaction(String bank) {
		System.out.println("one arg");
		this.bank = bank;
	}

	public Transaction(String bank, User uid) {

		// this.transactionId = transactionid;
		this();
		System.out.println("2 arg");
		this.bank = bank;
		this.uId = uid;
	}

	public Transaction(String bank, Account accid, User uid) {

		// this.transactionId = transactionid;
		this(bank, uid);
		this.bank = bank;
		System.out.println("3 arg");
		this.accId = accid;
		this.uId = uid;
	}

	public int getTransactionid() {
		return transactionId;
	}

	public void setTransactionid(int transactionid) {
		this.transactionId = transactionid;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public Account getAccid() {
		return accId;
	}

	public void setAccid(Account accid) {
		this.accId = accid;
	}

	public User getUid() {
		return uId;
	}

	public void setUid(User uid) {
		this.uId = uid;
	}

	public LocalDate getDateoftransaction() {
		return dateOfTransaction;
	}

	public void setDateoftransaction(LocalDate dateoftransaction) {
		this.dateOfTransaction = dateoftransaction;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + transactionId;
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
		Transaction other = (Transaction) obj;
		if (transactionId != other.transactionId)
			return false;
		return true;
	}
	
	
}