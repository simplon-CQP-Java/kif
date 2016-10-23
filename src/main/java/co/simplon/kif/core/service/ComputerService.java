package co.simplon.kif.core.service;
import co.simplon.kif.core.model.Computer;
import co.simplon.kif.core.repository.ComputerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ComputerService {

	@Autowired
	public ComputerRepository computerRepository;

	public List<Computer> getAll() {
		return computerRepository.findAll();
	}

	public Computer findById(Integer id) {
		return computerRepository.findOne(id);
	}

	public Computer addOrUpdate(Computer computer) {
		return computerRepository.save(computer);
	}

	public void delete(Integer id) {
		computerRepository.delete(id);
	}

}