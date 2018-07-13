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

import com.entity.Account;
import com.service.AccountService;

@RestController
@RequestMapping("/accounts")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@PostMapping
	@ResponseBody
	public String newacc(@RequestBody List<Account> acc) {
		return accountService.add(acc);
	}

	@GetMapping(value = "/{id}")
	public Optional<Account> getid(@PathVariable(value = "id") Integer id) {
		return accountService.get(id);
	}

	@GetMapping()
	public List<Account> getAll() {
		return accountService.getAll();
	}

	@DeleteMapping(value = "/{id}")
	@ResponseBody
	public String deleteById(@PathVariable(value = "id") Integer id) {
		return accountService.delete(id);
	}

	@DeleteMapping()
	@ResponseBody
	public String deleteByBody(@RequestBody List<Account> acc) {
		return accountService.delete(acc);
	}

}
