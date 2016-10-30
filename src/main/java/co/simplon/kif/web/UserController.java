package co.simplon.kif.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import co.simplon.kif.core.model.User;
import co.simplon.kif.core.model.User.Role;
import co.simplon.kif.core.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {
  @Autowired
  public UserService userService;

  @RequestMapping
  public ModelAndView getList(ModelMap model) {
    List<User> userList = userService.getAll();
    model.addAttribute("users", userList);
    return new ModelAndView("users/users", model);
  }

  @RequestMapping("/userByUsername")
  public ModelAndView getById(@RequestParam("username") String username, ModelMap model) {
    User user = userService.findByUsername(username);
    if (user == null) {
      model.addAttribute("username", username);
    }
    model.addAttribute("user", user);
    return new ModelAndView("users/search", model);
  }

  @RequestMapping("/add")
  public ModelAndView addUser(@RequestParam("username") String username, @RequestParam("password") String password, @RequestParam("confirmPassword") String confirmPassword, String email, Role role) {
    if (username != null && password != null && confirmPassword != null && email != null && role != null) {
      if (password.equals(confirmPassword)) {
    	  userService.addOrUpdate(username, password, email, role);
      }
    }
    return new ModelAndView("redirect:/users");
  }

  @RequestMapping("/delete")
  public ModelAndView deleteRoom(@RequestParam("id") Integer id, ModelMap model) {
    if (id != null) {
      userService.delete(id);
    }
    return new ModelAndView("redirect:/users");
  }

}
