package co.simplon.kif.core.repository;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.simplon.kif.core.model.Reply;
@Resource
public interface ReplyRepository extends JpaRepository<Reply, Integer> {
	// Return list of replies for given messageId
	@Query("select r from Reply r where r.message.id = ?1")
    public List<Reply> getReplies(Integer messageId);
}
