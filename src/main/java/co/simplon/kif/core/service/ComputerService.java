package co.simplon.kif.core.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.kif.core.model.Computer;
import co.simplon.kif.core.repository.ComputerRepository;

@Service
public class ComputerService extends GenericService<Computer, ComputerRepository> {
	@Autowired
	public ComputerRepository computerRepository;
}