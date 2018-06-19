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

import com.entity.User;
import com.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

//	@GetMapping(name = "/test")
//	public void test() {
//		System.out.println("checking");
//	}

	@PostMapping
	@ResponseBody
	public String newacc(@RequestBody List<User> acc) {
		System.out.println("in user post");
		return userService.add(acc);

	}

	@PostMapping("/login")
	@ResponseBody
	public String login(@RequestBody List<User> details )
	{
		return userService.login(details);
	}
	
	@GetMapping(value = "/{id}")
	public Optional<User> getid(@PathVariable(value = "id") Integer id) {
		return userService.get(id);
	}

	@PostMapping(value="/forgot")
	@ResponseBody
	public String forgot(@RequestBody User user)
	{
		return userService.forgot(user);
	}
	
	@GetMapping()
	public List<User> getAll() {
		return userService.getAll();
	}

	@DeleteMapping(value = "/{id}")
	@ResponseBody
	public String deleteById(@PathVariable(value = "id") Integer id) {
		return userService.delete(id);
	}

	@DeleteMapping()
	@ResponseBody
	public String deleteByBody(@RequestBody List<User> acc) {
		return userService.delete(acc);
	}

}
