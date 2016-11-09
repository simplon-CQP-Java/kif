package co.simplon.kif.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
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
  public ICustomLoginService customLoginService;

  @RequestMapping
  public ModelAndView getList(ModelMap model) {
    List<User> userList = userService.getAll();
    model.addAttribute("users", userList);
    return new ModelAndView("users/users", model);
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

  @RequestMapping("/delete")
  public ModelAndView deleteUser(@RequestParam("id") Integer id, ModelMap model) {
    if (id != null) {
      userService.delete(id);
    }
    return new ModelAndView("redirect:/users");
  }
}
