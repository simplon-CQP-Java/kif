package co.simplon.kif.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
		model.addAttribute("users", userService.getAll());
		return new ModelAndView("users/users", model);
	}

	@RequestMapping("/userById")
	public ModelAndView userById(@RequestParam("id") Integer id, ModelMap model,
			RedirectAttributes redirectAttr) {
		if (id == null) {
			redirectAttr.addFlashAttribute("error", "Aucun utilisateur trouvé.");
			return new ModelAndView("redirect:/users", model);
		}
		model.addAttribute("user", userService.findById(id));
		return new ModelAndView("users/user", model);
	}

	@RequestMapping("/userByUsername")
	public ModelAndView getById(@RequestParam("username") String username, ModelMap model,
			RedirectAttributes redirectAttr) {
		User user = userService.findOneByUsername(username);
		if (user == null) {
			redirectAttr.addFlashAttribute("error", "Aucun utilisateur trouvé.");
			model.addAttribute("username", username);
    	}
		model.addAttribute("user", user);
		return new ModelAndView("users/search", model);
	}

	@RequestMapping("/add")
	public ModelAndView addUser(@RequestParam("username") String username,
			@RequestParam("password") String password, @RequestParam("confirmPassword") String confirmPassword,
			Role role, RedirectAttributes redirectAttr) {
		if (username != null && password != null && confirmPassword != null && role != null) {
			if (password.length() < 6) {
				redirectAttr.addFlashAttribute("error", "Le mot de passe doit contenir au moins 6 caractères.");
				return new ModelAndView("redirect:/users");
			}
			if (password.equals(confirmPassword)) {
				try {
					userService.addOrUpdate(username, password, role);
					redirectAttr.addFlashAttribute("success", "L'utilisateur à bien été ajouté.");
				} catch(Exception e) {
					redirectAttr.addFlashAttribute("error", "Le nom d'utilisateur n'est pas disponible.");
				}
			} else {
				redirectAttr.addFlashAttribute("error", "Les mots de passe ne correspondent pas.");
			}
		} else {
			redirectAttr.addFlashAttribute("error", "Tous les champs sont requis.");
		}
		return new ModelAndView("redirect:/users");
	}

	@RequestMapping("/edit")
	public ModelAndView edit(@RequestParam("id") Integer id, @RequestParam("username") String username, ModelMap model,
			@RequestParam("password") String password, @RequestParam("newPassword") String newPassword,
			@RequestParam("confirmNewPassword") String confirmNewPassword, @RequestParam("role") Role role,
			HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttr) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (username == null || role == null) {
			if (id == null) return new ModelAndView("redirect:/users", model);
			redirectAttr.addFlashAttribute("error", "Tous les champs sont requis.");
			return new ModelAndView("redirect:/users/userById?id=" + id, model);
		}
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			User user = userService.findById(id);
	        user.setUsername(username);
	        user.setRole(role);
	        // Check if password, newPassword, confirmNewPassword are filled then changePassword
	        if (password != null && newPassword != null && confirmNewPassword != null) {
	        	if (newPassword.length() >= 6) {
	        		if (newPassword.equals(confirmNewPassword)) {
	        			user = changePassword(user, password, newPassword, confirmNewPassword);
	        		} else {
	        			redirectAttr.addFlashAttribute("error", "Les mots de passe ne correspondent pas.");
	        		}
	        	} else {
	        		redirectAttr.addFlashAttribute("error", "Le mot de passe doit contenir au moins 6 caractères.");
	        	}
	        } else {
	        }
			userService.updateUser(user);
			redirectAttr.addFlashAttribute("success", "L'utilisateur à bien été modifié.");
		}
		return new ModelAndView("redirect:/users/userById?id=" + id, model);
	}

	@RequestMapping("/edit/username")
	public ModelAndView editUsername(@RequestParam("id") Integer id, @RequestParam("username") String username, ModelMap model,
			HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttr) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (StringUtils.isBlank(username)) {
			redirectAttr.addFlashAttribute("error", "Tous les champs sont requis.");
			return new ModelAndView("redirect:/profil", model);
		}
		User user = new User();
		if (user != null && !(auth instanceof AnonymousAuthenticationToken)) {
			user = userService.findOneByUsername(auth.getName());
	        user.setUsername(username);
	        try {
	        	customLoginService.autoLogin(userService.addOrUpdate(user));
	        } catch(Exception e) {
	        	redirectAttr.addFlashAttribute("error", "Une erreur est survenue lors de la modification du nom d'utilisateur. Il est surement déjà utilisé.");
	        	return new ModelAndView("redirect:/profil", model);
	        }
	        redirectAttr.addFlashAttribute("success", "Votre nom d'utilisateur a bien été modifié.");
		}
		return new ModelAndView("redirect:/profil", model);
	}

	@RequestMapping("/edit/password")
	public ModelAndView editPassword(@RequestParam("id") Integer id, @RequestParam("password") String password,
			@RequestParam("newPassword") String newPassword, @RequestParam("confirmNewPassword") String confirmNewPassword,
			ModelMap model, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttr) {
		if (password == null || newPassword == null || confirmNewPassword == null || id == null
				|| password == "" || newPassword == "" || confirmNewPassword == "") {
			redirectAttr.addFlashAttribute("error", "Tous les champs sont requis.");
			return new ModelAndView("redirect:/profil", model);
		}
		if (newPassword.length() < 6) {
			redirectAttr.addFlashAttribute("error", "Le mot de passe doit contenir au moins 6 caractères.");
			return new ModelAndView("redirect:/profil");
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findById(id);
		if (user != null && !passwordEncoder.matches(password, user.getPassword())) {
			redirectAttr.addFlashAttribute("error", "Le mot de passe est incorrect.");
			return new ModelAndView("redirect:/profil", model);
		}
		if (newPassword.equals(confirmNewPassword) == false) {
			redirectAttr.addFlashAttribute("error", "Les mots de passe ne correspondent pas.");
			return new ModelAndView("redirect:/profil", model);
		}
		if (user != null && !(auth instanceof AnonymousAuthenticationToken)) {
			user = changePassword(user, password, newPassword, confirmNewPassword);
			User newUser = userService.updateUser(user);
			customLoginService.autoLogin(newUser);
			redirectAttr.addFlashAttribute("success", "Votre mot de passe à bien été modifié.");
		}
		//model.addAttribute("user", user);
		return new ModelAndView("redirect:/profil", model);
	}

	@RequestMapping(value={"/delete", "/edit/delete"})
	public ModelAndView setDisable(@RequestParam("id") Integer id, ModelMap model, HttpServletRequest request,
			HttpServletResponse response, RedirectAttributes redirectAttr) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User currentUser = userService.findOneByUsername(auth.getName());
		Role admin = Role.ADMIN;
		// Check if currentUser is Admin or if id is equals to currentUser id
		if (id != null && (currentUser.getRole() == admin || id.equals(currentUser.getId()))) {
			User user = userService.findById(id);
			user.setDisable();
			try {
				userService.addOrUpdate(user);
				redirectAttr.addFlashAttribute("success", "L'utilisateur à bien été supprimé.");
			} catch(Exception e) {
				redirectAttr.addFlashAttribute("error", "Une erreur est survenue lors de la suppression de l'utilisateur.");
				return new ModelAndView("redirect:/users", model);
			}
			if (id.equals(currentUser.getId())) new SecurityContextLogoutHandler().logout(request, response, auth);
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
		return new ModelAndView("redirect:/users");
	}

	@RequestMapping("/register")
	public ModelAndView registerUser(HttpServletRequest request, @RequestParam("username") String username, @RequestParam("password") String password, 
		  @RequestParam("confirmPassword") String confirmPassword, ModelMap model, RedirectAttributes redirectAttr) {
		Role role = Role.USER;
		if (username == null || password == null || confirmPassword == null || role == null || username == "") {
			redirectAttr.addFlashAttribute("error", "Tous les champs sont requis.");
			return new ModelAndView("redirect:/register", model);
		}
		if (password.length() < 6) {
			redirectAttr.addFlashAttribute("error", "Le mot de passe doit contenir au moins 6 caractères.");
			return new ModelAndView("redirect:/register");
		}
		if (password.equals(confirmPassword)) {
			User findUser = userService.findOneByUsername(username);
			if (findUser != null) {
				redirectAttr.addFlashAttribute("error", "Le nom d'utilisateur n'est pas disponible.");
				return new ModelAndView("redirect:/register", model);
			}
			try {
				User newUser = userService.addOrUpdate(username, password, role);
				if (customLoginService.autoLogin(newUser) != null) {
					SecurityContextHolder.getContext().getAuthentication().getPrincipal();
					HttpSession session = request.getSession(true);
					session.setAttribute("userDetails", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
				}
			} catch(Exception e) {
				redirectAttr.addFlashAttribute("error", "Une erreur est survenue lors de l'inscription.");
				return new ModelAndView("redirect:/register", model);
			}
			return new ModelAndView("redirect:/", model);
		} else {
			redirectAttr.addFlashAttribute("error", "Les mots de passe ne correspondent pas.");
			return new ModelAndView("redirect:/register", model);
		}
	}

	// Change the user password
	public User changePassword(User user, String password, String newPassword, String confirmNewPassword) {
		if (user == null || password == null || newPassword == null || confirmNewPassword == null) {
			if (user != null) {
				return user;
			}
			return new User();
		}
		if (newPassword.equals(confirmNewPassword) == false) {
			System.out.println("Passwords are not equals");
			return user;
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (passwordEncoder.matches(password, user.getPassword()) == false) {
			System.out.println("Invalid password");
			return user;
		}
		if (user != null && !(auth instanceof AnonymousAuthenticationToken)) {
	        user.setPassword(passwordEncoder.encode(newPassword));
		}
		return user;
	}
}
