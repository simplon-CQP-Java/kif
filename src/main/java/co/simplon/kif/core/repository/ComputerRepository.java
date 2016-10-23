package co.simplon.kif.core.repository;

import javax.annotation.Resource;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.kif.core.model.Computer;
@Resource
public interface ComputerRepository extends JpaRepository<Computer, Integer> {

}
