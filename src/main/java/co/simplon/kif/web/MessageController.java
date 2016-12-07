package co.simplon.kif.web;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import co.simplon.kif.core.model.Message;
import co.simplon.kif.core.model.Reply;
import co.simplon.kif.core.service.EmailAPIService;
import co.simplon.kif.core.service.MessageService;
import co.simplon.kif.core.service.ReplyService;

@Controller
@RequestMapping("/messages")
public class MessageController {
	@Autowired
	private MessageService messageService;
	@Autowired
	private ReplyService replyService;
	@Autowired
	private EmailAPIService emailService;

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
		// send mail and if return true set sended to reply
		message.setSended(emailService.sendMailToAdmin(message));
		if (message.getSended() == true) {
			emailService.sendConfirmationMail(message);
		}
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
			List<Reply> replies = replyService.getReplies(id);
			model.addAttribute("message", message);
			model.addAttribute("replies", replies);
		}
		return new ModelAndView("messages/message", model);
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
