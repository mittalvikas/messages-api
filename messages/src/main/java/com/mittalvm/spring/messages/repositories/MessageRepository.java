package com.mittalvm.spring.messages.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mittalvm.spring.messages.domain.Message;
import com.mittalvm.spring.messages.domain.MessageUser;
public interface MessageRepository extends CrudRepository<Message, Long> {
	
	Message findByIdAndCreatedBy(long id,MessageUser createdBy);
	List<Message> findByMessageIgnoreCaseContainingAndCreatedByIn(String message,List<MessageUser> users);

}
