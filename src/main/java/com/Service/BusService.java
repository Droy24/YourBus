package com.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Entity.Bus;
import com.Repository.BusRepository;
import com.Wrapper.BusDTO;

@Service
public class BusService {

	@Autowired
	private BusRepository busrepository;
	
	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	public String add(List<BusDTO> dto) {
		logger.info("To save and update Bus.");
		System.out.println("in bus add");
		for (BusDTO busdto : dto) {
			busrepository.save(populateBus(busdto));
		}
		return "save completed";
	}

	private Bus populateBus(BusDTO busdto) {
		Bus bus = new Bus(busdto);
		return bus;
	}

	public List<BusDTO> getAll() {
		System.out.println("Account get all");
		List<Bus> bs=busrepository.findAll();
		List<BusDTO> busdtolist= bs.stream().map(s->new BusDTO(s)).collect(Collectors.toList());
		return busdtolist;
	}

	public BusDTO get(Long id) {
		System.out.println("Account get");
		Optional<Bus> bus = busrepository.findById(id);
		if (bus.isPresent()) {
			return new BusDTO(bus.get());
		}
		return null;
	}

	public String delete(Long id) {
		System.out.println("Account delete");
		busrepository.deleteById(id);
		return "Succesful deletion";
	}

	public String delete(List<BusDTO> acc) {
		System.out.println("Account delete all");
		List<Bus> bus = acc.stream().map(s->new Bus(s)).collect(Collectors.toList());
		busrepository.deleteAll(bus);
		return "Multiple deletion successful";
	}

//	 public List<Bus> getinbetween(int source, int destination) {
//	 // TODO Auto-generated method stub
//	// List<Bus> lst=new LinkedList<>();
//	// for(Bus ls:Bus) {
//	//
//	// }
//	 return busrepository.findAllByInbetweenContaining(source,destination);
//	
//	 }
}
