package co.simplon.kif.web;

import java.util.Date;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.simplon.kif.core.model.Message;
import co.simplon.kif.core.model.User;
import co.simplon.kif.core.service.EmailAPIService;
import co.simplon.kif.core.service.MessageService;
import co.simplon.kif.core.service.UserService;

@Controller
public class IndexController {
	@Autowired
	private MessageService messageService;
	@Autowired
	private EmailAPIService emailService;
	@Autowired
	private UserService userService;

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
	public ModelAndView profile(ModelMap model, RedirectAttributes redirectAttr) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			User user = userService.findOneByUsername(auth.getName());
			model.addAttribute("user", user);
			return new ModelAndView("users/profile", model);
		}
		redirectAttr.addFlashAttribute("error", "Vous devez vous connecter pour accéder à cette page.");
		return new ModelAndView("redirect:/login");
	}
	@RequestMapping("/contactSubmit")
	public ModelAndView addMessage(@RequestParam("title") String title, @RequestParam("content") String content,
			@RequestParam("email") String email, RedirectAttributes redirectAttr) {
		if (title == null || content == null || email == null) {
			redirectAttr.addFlashAttribute("error", "Tous les champs sont requis.");
			return new ModelAndView("redirect:/contact");
		}
		Date createdAt = new Date();
		Message message = new Message(title, content, email, createdAt);
		// send mail and if return true set sended to reply
		message.setSended(emailService.sendMailToAdmin(message));
		if (message.getSended() == true) {
			emailService.sendConfirmationMail(message);
		}
		messageService.addOrUpdate(message);
		redirectAttr.addFlashAttribute("success", "Votre demande à bien été envoyée.");
		return new ModelAndView("redirect:/contact");
	}
	
	@RequestMapping(value = "/accessDenied")
	public String accessDeniedPage(ModelMap model) {
		return "accessDenied";
	}
}
