package com.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Entity.User;
import com.Repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public String add(List<User> acc) {
		System.out.println("in User add");
		for (User a : acc) {
			userRepository.save(a);
		}
		return "save completed";
	}

	public List<User> getAll() {
		System.out.println("User get all");
		return userRepository.findAll();
	}

	public Optional<User> get(Integer id) {
		System.out.println("User get");
		return userRepository.findById(id);
	}

	public String delete(Integer id) {
		System.out.println("User delete");
		Optional<User> user= userRepository.findById(id);
		if(user==null)return "Id does not exist";
		userRepository.deleteById(id);
		return "Succesful deletion";
	}

	public String delete(List<User> acc) {
		System.out.println("Service delete all");
		int count=0;
		int notfound=0;
		for(User user: acc)
			{
			Optional<User> u=userRepository.findById(user.getUserid());
			if(!u.isPresent()) {System.out.println("Entry not found"); notfound++;}
			else count++;
			}
		
		userRepository.deleteAll(acc);
		return "deletion successful for :"+count+" Not succesful for: "+notfound;
	}

	public String login(List<User> details) {
		String username="";
		String password="";
		for(User u: details ) {
		username=u.getUsername();
		password= u.getPassword();
		}
		List<User> us=userRepository.findByUsernameAndPassword(username,password);
		if(us.isEmpty())return "Not Found";
		else return "Login success";

	}

	public String forgot(User user) {
		
		String username = user.getUsername();
		String question=user.getQuestion();
		String answer= user.getPassword();
		
		List<User> us=userRepository.findByUsernameAndQuestionAndAnswer(username,question,answer);
		if(us.isEmpty())return "Wrong entry";
		else return us.toString();
	}
}
