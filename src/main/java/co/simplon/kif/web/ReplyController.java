package co.simplon.kif.web;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import co.simplon.kif.core.model.Message;
import co.simplon.kif.core.model.Reply;
import co.simplon.kif.core.model.User;
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
			replyService.addOrUpdate(reply);
		}
		return new ModelAndView("redirect:/messages");
	}

	@RequestMapping("/delete")
	public ModelAndView deleteReply(@RequestParam("id") Integer id, ModelMap model) {
		if (id == null) {
			return new ModelAndView("redirect:/messages");
		}
		replyService.delete(id);
		return new ModelAndView("redirect:/messages");
	}
}

