package co.simplon.kif.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import co.simplon.kif.core.model.Message;
import co.simplon.kif.core.model.Reply;
import co.simplon.kif.core.service.MessageService;
import co.simplon.kif.core.service.ReplyService;

@Controller
@RequestMapping("/messages")
public class MessageController {
	@Autowired
	private MessageService messageService;
	@Autowired
	private ReplyService replyService;
	@RequestMapping
	public ModelAndView getMessageList(ModelMap model) {
		List<Message> messageList = messageService.getAllMessages();
		model.addAttribute("messages", messageList);
		return new ModelAndView("messages/messages", model);
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
	public ModelAndView deleteMessage(@RequestParam("id") Integer id, ModelMap model, RedirectAttributes redirectAttr) {
		if (id == null) {
			return new ModelAndView("redirect:/messages");
		}
		try {
			messageService.delete(id);
			redirectAttr.addFlashAttribute("error", "Le message à bien été supprimé.");
		} catch(Exception e) {
			redirectAttr.addFlashAttribute("error", "Une erreur est survenue lors de la suppression du message.");
		}
		return new ModelAndView("redirect:/messages");
	}
}
