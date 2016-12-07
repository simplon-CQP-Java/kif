package co.simplon.kif.core.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.kif.core.model.Message;
import co.simplon.kif.core.repository.MessageRepository;

@Service
public class MessageService {
	@Autowired
	public MessageRepository messageRepository;

	public List<Message> getAll() {
		return messageRepository.findAll();
	}

	public Message findById(Integer id) {
		return messageRepository.findOne(id);
	}

	public Message addOrUpdate(Message message) {
		return messageRepository.save(message);
	}

	public void delete(Integer id) {
		messageRepository.delete(id);
	}
}
