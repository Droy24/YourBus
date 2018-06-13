package com.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Account;
import com.Repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	AccountRepository accountrepository;

	public String add(List<Account> acc) {
		// TODO Auto-generated method stub
		System.out.println("in account add");
		for (Account a : acc) {
			accountrepository.save(a);
		}
		return "save completed";
	}

	public List<Account> getAll() {
		// TODO Auto-generated method stub
		System.out.println("Account get all");
		return accountrepository.findAll();
	}

	public Optional<Account> get(Integer id) {
		// TODO Auto-generated method stub
		System.out.println("Account get");
		return accountrepository.findById(id);
	}

	public String delete(Integer id) {
		// TODO Auto-generated method stub
		System.out.println("Account delete");
		accountrepository.deleteById(id);
		return "Succesful deletion";
	}

	public String delete(List<Account> acc) {
		// TODO Auto-generated method stub
		System.out.println("Account delete all");
		accountrepository.deleteAll(acc);
		return "Multiple deletion successful";
	}

}
