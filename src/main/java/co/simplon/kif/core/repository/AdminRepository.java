package co.simplon.kif.core.repository;

import javax.annotation.Resource;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.kif.core.model.Admin;


@Resource
public interface AdminRepository extends JpaRepository<Admin, Integer> {
	Admin findOneByUsername(String username);
}
