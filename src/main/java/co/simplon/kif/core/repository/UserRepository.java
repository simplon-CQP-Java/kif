package co.simplon.kif.core.repository;

import javax.annotation.Resource;

import org.springframework.data.jpa.repository.JpaRepository;
import co.simplon.kif.core.model.User;

@Resource
public interface UserRepository extends JpaRepository<User, Integer> {
	User findOneByUsername(String username);
}
