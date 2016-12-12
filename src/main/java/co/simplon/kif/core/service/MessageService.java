package co.simplon.kif.core.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.kif.core.model.Message;
import co.simplon.kif.core.repository.MessageRepository;

@Service
public class MessageService extends GenericService<Message, MessageRepository> {
	@Autowired
	public MessageRepository messageRepository;
}
