package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.Transaction;
import com.repository.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	private TransactionRepository transactionrepository;

	public String add(List<Transaction> acc) {
		System.out.println("in Station add");
		for (Transaction a : acc) {
			transactionrepository.save(a);
		}
		return "save completed";
	}

	public List<Transaction> getAll() {
		System.out.println("Station get all");
		return transactionrepository.findAll();
	}

	public Optional<Transaction> get(Integer id) {
		System.out.println("Station get");
		return transactionrepository.findById(id);
	}

	public String delete(Integer id) {
		System.out.println("Transaction delete");
		transactionrepository.deleteById(id);
		return "Succesful deletion";
	}

	public String delete(List<Transaction> acc) {
		System.out.println("Transaction delete all");
		transactionrepository.deleteAll(acc);
		return "Multiple deletion successful";
	}
}
