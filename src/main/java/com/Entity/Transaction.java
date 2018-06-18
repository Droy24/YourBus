package com.Entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Transaction")
public class Transaction {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int transactionId;
	
	@Column(name = "bankname")
	private String bank;

	@OneToOne(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
	private Account accId; // maps

	@OneToOne(fetch = FetchType.LAZY,cascade=CascadeType.ALL)
	private User uId;

	@Column(name = "transactiondate")
	private LocalDate dateOfTransaction;

	public Transaction() {this.dateOfTransaction = java.time.LocalDate.now();}
	
	public Transaction(String bank)
	{
		System.out.println("one arg");
		this.bank=bank;
		this.dateOfTransaction=java.time.LocalDate.now();
	}
	public Transaction(String bank, User uid) {
	
//		this.transactionId = transactionid;
		this();
		System.out.println("2 arg");
		this.bank = bank;
		this.uId = uid;
		this.dateOfTransaction = java.time.LocalDate.now();
	}
	
	public Transaction(String bank, Account accid, User uid) {
		
//		this.transactionId = transactionid;
		this(bank,uid);
		this.bank = bank;
		System.out.println("3 arg");
		this.accId = accid;
		this.uId = uid;
		this.dateOfTransaction = java.time.LocalDate.now();
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

}
