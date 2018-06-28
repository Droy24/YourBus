package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Account;
import com.repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountrepository;

	/**
	 * @param acc
	 * @return
	 */
	public String add(List<Account> acc) {
		System.out.println("in account add");
		for (Account a : acc) {
			accountrepository.save(a);
		}
		return "save completed";
	}

	public List<Account> getAll() {
		return accountrepository.findAll();
	}

	public Optional<Account> get(Integer id) {
		System.out.println("Account get");
		return accountrepository.findById(id);
	}

	public String delete(Integer id) {
		System.out.println("Account delete");
		accountrepository.deleteById(id);
		return "Succesful deletion";
	}

	public String delete(List<Account> acc) {
		System.out.println("Account delete all");
		accountrepository.deleteAll(acc);
		return "Multiple deletion successful";
	}

}
