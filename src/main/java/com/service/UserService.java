package com.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.User;
import com.repository.UserRepository;
import com.wrapper.UserDTO;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public String add(List<UserDTO> acc) {
		System.out.println("in User add");
		for (UserDTO a : acc) {
			userRepository.save(new User(a));
		}
		return "save completed";
	}

	public List<UserDTO> getAll() {
		System.out.println("User get all");
		List<User> user= userRepository.findAll();
		List<UserDTO> userDTO=user.stream().map(s->new UserDTO(s)).collect(Collectors.toList());
		return userDTO;
	}

	public UserDTO get(Integer id) {
		System.out.println("User get");
		return new UserDTO(userRepository.findById(id).get());
	}

	public String delete(Integer id) {
		System.out.println("User delete");
		Optional<User> user = userRepository.findById(id);
		if (user == null)
			return "Id does not exist";
		userRepository.deleteById(id);
		return "Succesful deletion";
	}

	public String delete(List<UserDTO> acc) {
		System.out.println("Service delete all");
		int count = 0;
		int notfound = 0;
		for (UserDTO user : acc) {
			Optional<User> u = userRepository.findById(user.getUserid());
			if (!u.isPresent()) {
				System.out.println("Entry not found");
				notfound++;
			} else
				count++;
		}
		List<User> userList=acc.stream().map(u->new User(u)).collect(Collectors.toList());
		userRepository.deleteAll(userList);
		return "deletion successful for : "+count+" Not succesful for: " + notfound;
	}

	public String login(List<UserDTO> details) {
		String username = "";
		String password = "";
		for (UserDTO u : details) {
			username = u.getUsername();
			password = u.getPassword();
		}
		List<User> us = userRepository.findByUsernameAndPassword(username, password);
		if (us.isEmpty())
			return "Not Found";
		else
			return "Login success";

	}

	public String forgot(UserDTO user) {

		String username = user.getUsername();
		String question = user.getQuestion();
		String answer = user.getPassword();

		List<User> us = userRepository.findByUsernameAndQuestionAndAnswer(username, question, answer);
		if (us.isEmpty())
			return "Wrong entry";
		else
			return us.toString();
	}
}
