package co.simplon.kif.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {
	@RequestMapping(path = "/login")
	public ModelAndView loginForm(ModelMap model) {
	  return new ModelAndView("users/loginForm");
	}
	
	@RequestMapping(path = "/register")
	public ModelAndView registerForm(ModelMap model) {
	  return new ModelAndView("users/registerForm");
	}
	
	@RequestMapping(value = "/logout")
    public ModelAndView logoutPage(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return new ModelAndView("redirect:/");
    }
	
	@RequestMapping(value = "/accessDenied")
	public String accessDeniedPage(ModelMap model) {
	  return "accessDenied";
	}
}
