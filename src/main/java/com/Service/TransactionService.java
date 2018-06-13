package com.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Transaction;
import com.Repository.TransactionRepository;

@Service
public class TransactionService {

	@Autowired
	TransactionRepository transactionrepository;

	public String add(List<Transaction> acc) {
		// TODO Auto-generated method stub
		System.out.println("in Station add");
		for (Transaction a : acc) {
			transactionrepository.save(a);
		}
		return "save completed";
	}

	public List<Transaction> getAll() {
		// TODO Auto-generated method stub
		System.out.println("Station get all");
		return transactionrepository.findAll();
	}

	public Optional<Transaction> get(Integer id) {
		// TODO Auto-generated method stub
		System.out.println("Station get");
		return transactionrepository.findById(id);
	}

	public String delete(Integer id) {
		// TODO Auto-generated method stub
		System.out.println("Transaction delete");
		transactionrepository.deleteById(id);
		return "Succesful deletion";
	}

	public String delete(List<Transaction> acc) {
		// TODO Auto-generated method stub
		System.out.println("Transaction delete all");
		transactionrepository.deleteAll(acc);
		return "Multiple deletion successful";
	}
}
