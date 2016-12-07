package co.simplon.kif.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import co.simplon.kif.core.model.Message;
import co.simplon.kif.core.model.Reply;
import co.simplon.kif.core.model.User;
import co.simplon.kif.core.service.EmailAPIService;
import co.simplon.kif.core.service.MessageService;
import co.simplon.kif.core.service.ReplyService;
import co.simplon.kif.core.service.UserService;

@Controller
@RequestMapping("/replies")
public class ReplyController {
	@Autowired
	private ReplyService replyService;
	@Autowired
	private UserService userService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private EmailAPIService emailService;

	@RequestMapping("/add")
	public ModelAndView addReply(@RequestParam("reply") String content, @RequestParam("id") Integer messageId) {
		// Check if params are not null
		if (messageId == null || content == null) {
			return new ModelAndView("redirect:/messages");
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null && auth.getName() != "anonymousUser") {
			// Get current user and message
			User user = userService.findOneByUsername(auth.getName());
			Message message = messageService.findById(messageId);
			Date createdAt = new Date();
			// Create Reply in base
			Reply reply = new Reply(message, content, createdAt, user);
			// send mail and if return true set sended to reply
			reply.setSended(sendMail(message, reply));
			replyService.addOrUpdate(reply);
		} else {
			return new ModelAndView("redirect:/login");
		}
		return new ModelAndView("redirect:/messages");
	}
	
	// Get informations and send mail
	public boolean sendMail(Message message, Reply reply) {
		if (message == null || reply == null) {
			return false;
		}
		String toAddress = message.getEmail();
		String fromAddress = "simplon.kif@gmail.com";
		String subject = "RE: " + message.getTitle();
		String body = "Un administrateur a répondu à votre demande : " + message.getTitle() + ".\n" + reply.getContent();
		emailService.sendEmail(toAddress, fromAddress, subject, body);
		return true;
	}
}

