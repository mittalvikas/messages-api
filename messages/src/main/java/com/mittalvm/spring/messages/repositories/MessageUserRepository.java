package com.mittalvm.spring.messages.repositories;

import org.springframework.data.repository.CrudRepository;

import com.mittalvm.spring.messages.domain.MessageUser;
public interface MessageUserRepository extends CrudRepository<MessageUser, Long> {
	
	MessageUser findByLogin(String login);

}
