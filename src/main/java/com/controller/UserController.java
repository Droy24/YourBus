package com.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.security.AuthenticationService;
import com.service.UserService;
import com.wrapper.UserDTO;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthenticationService authenticationService;
	
	@PostMapping("/addMany")
	@ResponseBody
	public String manynewacc(@RequestBody List<UserDTO> acc) {
		System.out.println("in user post");
		return userService.add(acc);
	}

	@PostMapping
	@ResponseBody
	public String newacc(@RequestBody UserDTO acc) {
		System.out.println("in user post");
		return userService.addone(acc);
	}
	
	@PostMapping("/login")
	@ResponseBody
	public String login(@RequestBody UserDTO details) {
		return userService.login(details);
	}

	@GetMapping(value = "/{id}")
	public UserDTO getid(@PathVariable(value = "id") Long id) {
		return userService.get(id);
	}

	@GetMapping
	public List<UserDTO> getAllUsers() {
		return userService.getAll();
	}

	@PostMapping(value = "/forgot")
	@ResponseBody
	public String forgot(@RequestBody UserDTO user) {
		return userService.forgot(user);
	}

	@DeleteMapping(value = "/{id}")
	@ResponseBody
	public String deleteById(@PathVariable(value = "id") Long id) {
		return userService.delete(id);
	}

	@DeleteMapping()
	@ResponseBody
	public String deleteByBody(@RequestBody List<UserDTO> acc) {
		return userService.delete(acc);
	}
	
	@PostMapping(value = "/logout")
	void logOut() {
		authenticationService.revokeToken();
	}

}
