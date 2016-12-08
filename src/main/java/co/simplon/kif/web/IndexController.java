package co.simplon.kif.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import co.simplon.kif.core.model.User;
import co.simplon.kif.core.service.UserService;

@Controller
public class IndexController {
	@Autowired
	public UserService userService;
	@RequestMapping(path = "/login")
	public ModelAndView loginForm(ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			return new ModelAndView("redirect:/");
		}
		return new ModelAndView("users/loginForm");
	}
	
	@RequestMapping(path = "/register")
	public ModelAndView registerForm(ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			return new ModelAndView("redirect:/");
		}
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
	
	@RequestMapping("/contact")
	public ModelAndView newMessage(ModelMap model) {
		return new ModelAndView("messages/newMessage", model);
	}

	@RequestMapping("/profil")
	public ModelAndView profile(ModelMap model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			User user = userService.findOneByUsername(auth.getName());
			model.addAttribute("user", user);
			return new ModelAndView("users/profile", model);
		}
		return new ModelAndView("redirect:/login");
	}
	
	@RequestMapping(value = "/accessDenied")
	public String accessDeniedPage(ModelMap model) {
		return "accessDenied";
	}
}
