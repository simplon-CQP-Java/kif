package co.simplon.kif.core.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.kif.core.model.Message;
import co.simplon.kif.core.repository.MessageRepository;

@Service
public class MessageService extends GenericService<Message, MessageRepository> {
	@Autowired
	public MessageRepository messageRepository;

	public List<Message> getAllMessages() {
    	return messageRepository.getAllMessages();
    }
}
