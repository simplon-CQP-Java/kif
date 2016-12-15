package co.simplon.kif.core.service.authentication;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import co.simplon.kif.core.model.User;
import co.simplon.kif.core.service.UserService;


@Service
public class CustomLoginService implements UserDetailsService, ICustomLoginService {
	@Autowired
	@Qualifier("daoAuthenticationProvider")
	private AuthenticationProvider authenticationProvider;

    @Autowired
    public UserService userService;

    @Override
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userService.findOneByUsername(username);
        if(user==null) {
            throw new UsernameNotFoundException("User name not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                user.isEnabled(), true, true, true, getGrantedAuthorities(user));
    }

    /* (non-Javadoc)
	 * @see co.simplon.kif.core.service.authentication.ICustomLoginService#autoLogin(co.simplon.kif.core.model.User)
	 */
    @Override
	public User autoLogin(User user) {
  	  try {
          // Must be called from request filtered by Spring Security, otherwise SecurityContextHolder is not updated
          UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword());
          //token.setDetails(new WebAuthenticationDetails(request));
          Authentication authentication = this.authenticationProvider.authenticate(token);
          SecurityContextHolder.getContext().setAuthentication(authentication);
  	  } catch (Exception e) {
          SecurityContextHolder.getContext().setAuthentication(null);
  	  }
  	  Authentication auth = new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), getGrantedAuthorities(user));
  	  SecurityContextHolder.getContext().setAuthentication(auth);
  	  return user;
    }

    private List<GrantedAuthority> getGrantedAuthorities(User user){
        List<GrantedAuthority> authorities = new ArrayList<>();
        String role = user.getRole().toString();
        authorities.add(new SimpleGrantedAuthority(role));
        return authorities;
    }
}