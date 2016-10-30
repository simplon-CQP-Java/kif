package co.simplon.kif.core.service.authentication;

import java.util.ArrayList;
import java.util.List;

import co.simplon.kif.core.model.User;
import co.simplon.kif.core.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomLoginService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepository.findOneByUsername(username);
        if(user==null) {
            throw new UsernameNotFoundException("User name not found");
        }
        System.out.println("User : " + user.getUsername());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), true, true, true, false, getGrantedAuthorities(user));
    }

    private List<GrantedAuthority> getGrantedAuthorities(User user){
        List<GrantedAuthority> authorities = new ArrayList<>();
        String role = user.getRole().toString();
        System.out.println("Role : " + role);
        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }
}
