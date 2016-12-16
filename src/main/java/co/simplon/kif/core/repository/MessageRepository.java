package co.simplon.kif.core.repository;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import co.simplon.kif.core.model.Message;

@Resource
public interface MessageRepository extends JpaRepository<Message, Integer> {
	// Get all Messages and order by id descendant
	@Query("select r from Message r ORDER BY r.id DESC")
	public List<Message> getAllMessages();
}
