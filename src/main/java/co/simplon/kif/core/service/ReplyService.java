package co.simplon.kif.core.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.kif.core.model.Reply;
import co.simplon.kif.core.repository.ReplyRepository;

@Service
public class ReplyService {
	@Autowired
	public ReplyRepository replyRepository;

	public List<Reply> getAll() {
		return replyRepository.findAll();
	}

	public Reply findById(Integer id) {
		return replyRepository.findOne(id);
	}

	public Reply addOrUpdate(Reply Reply) {
		return replyRepository.save(Reply);
	}

	public void delete(Integer id) {
		replyRepository.delete(id);
	}

	public List<Reply> getReplies(Integer id) {
		return replyRepository.getReplies(id);
	}
}
