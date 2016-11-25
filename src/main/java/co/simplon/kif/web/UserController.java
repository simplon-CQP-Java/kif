package co.simplon.kif.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import co.simplon.kif.core.model.User;
import co.simplon.kif.core.model.User.Role;
import co.simplon.kif.core.service.UserService;
import co.simplon.kif.core.service.authentication.ICustomLoginService;

@Controller
@RequestMapping("/users")
public class UserController {
	@Autowired
	public UserService userService;
  
	@Autowired
	public 	ICustomLoginService customLoginService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
  
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
  
	@RequestMapping
	public ModelAndView getList(ModelMap model) {
		List<User> userList = userService.getAll();
		model.addAttribute("users", userList);
		return new ModelAndView("users/users", model);
	}
	
	@RequestMapping("/userById")
	public ModelAndView userById(@RequestParam("id") Integer id, ModelMap model) {
		System.out.println("id " + id);
		if (id == null) {
			return new ModelAndView("redirect:/users", model);
		}
		User user = userService.findById(id);
		if (user != null) {
			model.addAttribute("user", user);
		}
		return new ModelAndView("users/user", model);
	}

	@RequestMapping("/userByUsername")
	public ModelAndView getById(@RequestParam("username") String username, ModelMap model) {
		User user = userService.findOneByUsername(username);
		if (user == null) {
			model.addAttribute("username", username);
    	}
		model.addAttribute("user", user);
		return new ModelAndView("users/search", model);
	}

	@RequestMapping("/add")
	public ModelAndView addUser(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("confirmPassword") String confirmPassword, Role role) {
		if (username != null && password != null && confirmPassword != null && role != null) {
			if (password.equals(confirmPassword)) {
				userService.addOrUpdate(username, password, role);
			}
		}
		return new ModelAndView("redirect:/users");
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

	@RequestMapping("/edit/username")
	public ModelAndView editUsername(@RequestParam("id") Integer id, @RequestParam("username") String username, ModelMap model,
			HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (username == null) {
			return new ModelAndView("redirect:/users/profil", model);
		}
		User user = new User();
		if (user != null && !(auth instanceof AnonymousAuthenticationToken)) {
			user = userService.findOneByUsername(auth.getName());
	        user.setUsername(username);
			User newUser = userService.addOrUpdate(user);
			customLoginService.autoLogin(newUser);
		}
		return new ModelAndView("redirect:/users/profil", model);
	}
	
	@RequestMapping("/edit/password")
	public ModelAndView editPassword(@RequestParam("id") Integer id, @RequestParam("password") String password,
			@RequestParam("newPassword") String newPassword, @RequestParam("confirmNewPassword") String confirmNewPassword,
			ModelMap model, HttpServletRequest request, HttpServletResponse response) {
		if (password == null || newPassword == null || confirmNewPassword == null || id == null) {
			return new ModelAndView("redirect:/users/profil", model);
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (newPassword.equals(confirmNewPassword) == false) {
			System.out.println("Passwords are not equals");
			return new ModelAndView("redirect:/users/profil", model);
		}
		User user = userService.findById(id);
		if (passwordEncoder.matches(password, user.getPassword()) == false) {
			System.out.println("Invalid password");
			return new ModelAndView("redirect:/users/profil", model);
		}
		if (user != null && !(auth instanceof AnonymousAuthenticationToken)) {
	        user.setPassword(passwordEncoder.encode(newPassword));
			User newUser = userService.addOrUpdate(user);
			customLoginService.autoLogin(newUser);
		}
		//model.addAttribute("user", user);
		return new ModelAndView("redirect:/users/profil", model);
	}

	@RequestMapping("/delete")
	public ModelAndView setDisable(@RequestParam("id") Integer id, ModelMap model) {
		if (id != null) {
			User user = userService.findById(id);
			user.setDisable();
			userService.addOrUpdate(user);
		}
		return new ModelAndView("redirect:/");
	}
	
	@RequestMapping("/active")
	public ModelAndView setEnable(@RequestParam("id") Integer id, ModelMap model) {
		if (id != null) {
			User user = userService.findById(id);
			user.setEnabled(true);
			userService.addOrUpdate(user);
		}
		return new ModelAndView("redirect:/");
	}
	@RequestMapping("/register")
	public ModelAndView registerUser(HttpServletRequest request, @RequestParam("username") String username, @RequestParam("password") String password, 
		  @RequestParam("confirmPassword") String confirmPassword, ModelMap model) {
		Role role = Role.USER;
		if (username != null && password != null && confirmPassword != null && role != null) {
			if (password.equals(confirmPassword)) {
				User findUser = userService.findOneByUsername(username);
				if (findUser != null) {
					return new ModelAndView("redirect:/", model);
				}
				User newUser = userService.addOrUpdate(username, password, role);
				if (customLoginService.autoLogin(newUser) != null) {
					SecurityContextHolder.getContext().getAuthentication().getPrincipal();
					HttpSession session = request.getSession(true);
					session.setAttribute("userDetails", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
				}
				return new ModelAndView("redirect:/", model);
			}
		}
		return new ModelAndView("redirect:/", model);
	}
}
