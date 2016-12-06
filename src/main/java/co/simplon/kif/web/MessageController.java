package co.simplon.kif.web;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/messages")
public class MessageController {
	@Autowired
	private MessageService messageService;
	@Autowired
	private UserService userService;
	@Autowired
	private ReplyService replyService;

	@RequestMapping
	public ModelAndView getMessageList(ModelMap model) {
		List<Message> messageList = messageService.getAll();
		model.addAttribute("messages", messageList);
		return new ModelAndView("messages/messages", model);
	}

	@RequestMapping("/new")
	public ModelAndView newMessage(ModelMap model) {
		return new ModelAndView("messages/newMessage", model);
	}

	@RequestMapping("/add")
	public ModelAndView addMessage(@RequestParam("title") String title, @RequestParam("content") String content,
			@RequestParam("email") String email) {
		if (title == null || content == null || email == null) {
			return new ModelAndView("redirect:/messages");
		}
		Date createdAt = new Date();
		Message message = new Message(title, content, email, createdAt);
		messageService.addOrUpdate(message);
		return new ModelAndView("redirect:/contact");
	}

	@RequestMapping("/messageById/{id}")
	public ModelAndView messageById(@PathVariable("id") Integer id, ModelMap model) {
		if (id == null) {
			return new ModelAndView("redirect:/message", model);
		}
		Message message = messageService.findById(id);
		if (message != null) {
			List<Reply> replys = replyService.getReplies(id);
			model.addAttribute("message", message);
			model.addAttribute("replys", replys);
		}
		return new ModelAndView("messages/message", model);
	}
	
	@RequestMapping("/{id}/reply/")
	public ModelAndView reply(@RequestParam("id") Integer messageId, @RequestParam("content") String content, ModelMap model) {
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
	public ModelAndView deleteMessage(@RequestParam("id") Integer id, ModelMap model) {
		if (id == null) {
			return new ModelAndView("redirect:/messages");
		}
		messageService.delete(id);
		return new ModelAndView("redirect:/messages");
	}
}
