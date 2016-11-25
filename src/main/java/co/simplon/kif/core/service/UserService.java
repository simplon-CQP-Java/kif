package co.simplon.kif.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.kif.core.model.User;
import co.simplon.kif.core.repository.UserRepository;
import co.simplon.kif.core.service.authentication.ICustomLoginService;

@Service
public class UserService {
	@Autowired
	@Qualifier("daoAuthenticationProvider")
	private AuthenticationProvider authenticationProvider;
	
	@Autowired
	public ICustomLoginService customLoginService;
	
	@Autowired
	public UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Transactional(readOnly = true)
	public List<User> getAll() {
		return userRepository.findAll();
	}

	public User findById(Integer id) {
		return userRepository.findOne(id);
	}

	public User findOneByUsername(String username) {
		Example<User> userExample = Example.of(new User(username, null, null, null));
		return userRepository.findOne(userExample);
	}

	public User addOrUpdate(String username, String password, User.Role role) {
		return userRepository.save(new User(username, passwordEncoder.encode(password), role, true));
	}
	
	public User setDisable(String username) {
		//return userRepository.save(new User(username, passwordEncoder.encode(password), true));
		return new User(username, null, null, null);
	}

	public User updateUsername(String username) {
		//return userRepository.save(new User(username, passwordEncoder.encode(password), true));
		return new User(username, null, null, null);
	}

	public User updatePassword(String username, String password) {
		//return userRepository.save(new User(username, passwordEncoder.encode(password), true));
		return new User(username, passwordEncoder.encode(password), null, null);
	}

	public void delete(Integer id) {
		userRepository.delete(id);
	}

	public User addOrUpdate(User user) {
		return userRepository.save(user);	
	}
}
