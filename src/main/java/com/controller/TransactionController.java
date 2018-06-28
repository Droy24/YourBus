package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Transaction;
import com.service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

	@Autowired
	private TransactionService transactionService;

	// @GetMapping(name = "/test")
	// public void test() {
	// System.out.println("checking");
	// }

	@PostMapping
	@ResponseBody
	public String newacc(@RequestBody List<Transaction> acc) 
	{
		System.out.println("in transaction post");
		return transactionService.add(acc);
	}

	@GetMapping(value = "/{id}")
	public Optional<Transaction> getid(@PathVariable(value = "id") Integer id) 
	{
		return transactionService.get(id);
	}

	@GetMapping()
	public List<Transaction> getAll() 
	{
		return transactionService.getAll();
	}

	@DeleteMapping(value = "/{id}")
	@ResponseBody
	public String deleteById(@PathVariable(value = "id") Integer id) 
	{
		return transactionService.delete(id);
	}

	@DeleteMapping()
	@ResponseBody
	public String deleteByBody(@RequestBody List<Transaction> acc) 
	{
		return transactionService.delete(acc);
	}

}
