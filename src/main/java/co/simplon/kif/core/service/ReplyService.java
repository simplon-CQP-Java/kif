package co.simplon.kif.core.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.simplon.kif.core.model.Reply;
import co.simplon.kif.core.repository.ReplyRepository;

@Service
public class ReplyService extends GenericService<Reply, ReplyRepository> {
	@Autowired
	public ReplyRepository replyRepository;

	public List<Reply> getReplies(Integer id) {
		return replyRepository.getReplies(id);
	}
}
