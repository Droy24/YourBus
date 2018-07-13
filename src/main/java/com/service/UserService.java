package com.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entity.User;
import com.exception.UnprocessableEntityException;
import com.repository.UserRepository;
import com.wrapper.UserDTO;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public String add(List<UserDTO> userlist) {
		System.out.println("in User add");
		for (UserDTO user : userlist) {
			validateUser(user);
			userRepository.save(new User(user));
		}
		return "save completed";
	}

	public List<UserDTO> getAll() {
		System.out.println("User get all");
		List<User> user = userRepository.findAll();
		List<UserDTO> userDTO = user.stream().map(s -> new UserDTO(s)).collect(Collectors.toList());
		return userDTO;
	}

	public UserDTO get(Long id) {
		System.out.println("User get");
		return new UserDTO(userRepository.findById(id).get());
	}

	public String delete(Long id) {
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
		List<User> userList = acc.stream().map(u -> new User(u)).collect(Collectors.toList());
		userRepository.deleteAll(userList);
		return "deletion successful for : " + count + " Not succesful for: " + notfound;
	}

	public String login(UserDTO u) {
		String username = "";
		String password = "";
		
			username = u.getUsername();
			password = u.getPassword();
		
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


	public User findByUsername(String username) 
	{
		return userRepository.findByUsername(username);
		
	}

	public String addone(UserDTO user) {
		System.out.println("in User add");
		userRepository.save(new User(user));
		return "save completed";
	}
	public void validateUser(UserDTO user) {
		if (StringUtils.isBlank(user.getPassword())) {
			logger.error("Invalid User password.");
			throw new UnprocessableEntityException("Invalid password.");
		}
		if (StringUtils.isBlank(user.getUsername())) {
			logger.error("Invalid Username.");
			throw new UnprocessableEntityException("Invalid username.");
		}
		if (user.getMobile() == 0) {
			logger.error("Invalid mobile number");
			throw new UnprocessableEntityException("Invalid mobile number");
		}

	}
}
