package com.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Bus;
import com.Repository.BusRepository;

@Service
public class BusService {

	@Autowired
	BusRepository busrepository;

	public String add(List<Bus> acc) {
		// TODO Auto-generated method stub
		System.out.println("in bus add");
		for (Bus a : acc) {
			busrepository.save(a);
		}
		return "save completed";
	}

	public List<Bus> getAll() {
		// TODO Auto-generated method stub
		System.out.println("Account get all");
		return busrepository.findAll();
	}

	public Optional<Bus> get(Integer id) {
		// TODO Auto-generated method stub
		System.out.println("Account get");
		return busrepository.findById(id);
	}

	public String delete(Integer id) {
		// TODO Auto-generated method stub
		System.out.println("Account delete");
		busrepository.deleteById(id);
		return "Succesful deletion";
	}

	public String delete(List<Bus> acc) {
		// TODO Auto-generated method stub
		System.out.println("Account delete all");
		busrepository.deleteAll(acc);
		return "Multiple deletion successful";
	}

//	public List<Bus> getinbetween(int source, int destination) {
//		// TODO Auto-generated method stub
////		List<Bus> lst=new LinkedList<>();
////		for(Bus ls:Bus) {
////			
////		}
//		return busrepository.findAllByInbetweenContaining(source,destination);
//	
//	}
}
