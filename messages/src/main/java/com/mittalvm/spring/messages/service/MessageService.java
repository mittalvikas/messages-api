package com.mittalvm.spring.messages.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mittalvm.spring.messages.domain.Message;
import com.mittalvm.spring.messages.domain.MessageUser;
import com.mittalvm.spring.messages.domain.UserFollower;
import com.mittalvm.spring.messages.repositories.MessageRepository;

@Service
public class MessageService {

	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private UserFollowerService userFollowerService; 
		
	public Message saveOrUpdateMessage(Message message){
		return messageRepository.save(message);
	}
	
	
	public Message fetchMessage(long messageId,MessageUser createdBy){
		return messageRepository.findByIdAndCreatedBy(messageId, createdBy);
	}
	
	
	public List<Message> searchMessages(String search,MessageUser createdBy){
		
		List<MessageUser> users=new ArrayList();
		users.add(createdBy);
		users.addAll(
				userFollowerService.findFollowingByUser(createdBy).stream().
				map(UserFollower::getFollowingUser).
				collect(Collectors.
						toCollection(ArrayList::new)));
		return messageRepository.findByMessageIgnoreCaseContainingAndCreatedByIn(search,users);
		
	}
	
}
