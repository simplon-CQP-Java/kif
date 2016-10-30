package co.simplon.kif.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.kif.core.model.User;
import co.simplon.kif.core.repository.UserRepository;

@Service
public class UserService {
  @Autowired
  public UserRepository userRepository;
  
  @Autowired
  @Qualifier("passwordEncoder")
  private PasswordEncoder passwordEncoder;

  @Transactional(readOnly = true)
  public List<User> getAll() {
    return userRepository.findAll();
  }

  public User findById(Integer id) {
    return userRepository.findOne(id);
  }
  
  public User findByUsername(String username) {
	return userRepository.findOneByUsername(username);
  }

  public User addOrUpdate(String username, String password, String email, User.Role role) {
    return userRepository.save(new User(username, passwordEncoder.encode(password), email, role));
  }

  public void delete(Integer id) {
    userRepository.delete(id);
  }
}
