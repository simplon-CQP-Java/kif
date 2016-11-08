package co.simplon.kif.core.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.kif.core.model.Admin;
import co.simplon.kif.core.model.User;
import co.simplon.kif.core.repository.AdminRepository;
/*
 * @author Iris 
 */
@Service
public class AdminService {

	@Autowired 
	public AdminRepository adminRepo;
	
	@Autowired
	@Qualifier("passwordEncoder")
	  private PasswordEncoder passwordEncoder;

	@Transactional(readOnly = true)
	  public List<Admin> getAll() {
	    return adminRepo.findAll();
	  }

	  public Admin findById(Integer id) {
	    return adminRepo.findOne(id);
	  }
	  
	  public Admin findByUsername(String username) {
		return adminRepo.findOneByUsername(username);
	  }

	  //impossible de changer le role d'un admin
	  public Admin addOrUpdate(String username, String password, String email) {
	    return adminRepo.save(new Admin(username, passwordEncoder.encode(password), email));
	  }

	  public void delete(Integer id) {
	    adminRepo.delete(id);
	  }
	
}
