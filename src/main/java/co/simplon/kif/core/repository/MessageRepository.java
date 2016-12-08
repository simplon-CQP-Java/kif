package co.simplon.kif.core.repository;

import javax.annotation.Resource;

import org.springframework.data.jpa.repository.JpaRepository;

import co.simplon.kif.core.model.Message;

@Resource
public interface MessageRepository extends JpaRepository<Message, Integer> {
}
