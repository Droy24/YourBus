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
import com.wrapper.UserDTO;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	// @GetMapping(name = "/test")
	// public void test() {
	// System.out.println("checking");
	// }

	@PostMapping
	@ResponseBody
	public String newacc(@RequestBody List<UserDTO> acc) 
	{
		System.out.println("in user post");
		return userService.add(acc);
	}

	@PostMapping("/login")
	@ResponseBody
	public String login(@RequestBody List<UserDTO> details) {
		return userService.login(details);
	}

	@GetMapping(value = "/{id}")
	public UserDTO getid(@PathVariable(value = "id") Integer id) {
		return userService.get(id);
	}

	@PostMapping(value = "/forgot")
	@ResponseBody
	public String forgot(@RequestBody UserDTO user) {
		return userService.forgot(user);
	}

	@GetMapping()
	public List<UserDTO> getAll() {
		return userService.getAll();
	}

	@DeleteMapping(value = "/{id}")
	@ResponseBody
	public String deleteById(@PathVariable(value = "id") Integer id) {
		return userService.delete(id);
	}

	@DeleteMapping()
	@ResponseBody
	public String deleteByBody(@RequestBody List<UserDTO> acc) {
		return userService.delete(acc);
	}

}
