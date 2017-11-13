package com.mittalvm.spring.messages.resources;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mittalvm.spring.messages.domain.Message;
import com.mittalvm.spring.messages.domain.MessageUser;
import com.mittalvm.spring.messages.service.MessageService;
import com.mittalvm.spring.messages.service.MessageUserService;

@RestController
@RequestMapping("/messages")
public class MessagesResource {
	
	@Autowired
	MessageService messageService;
	@Autowired
	MessageUserService messageUserService;
	
	private static int MAX_LIMIT=25;
	private static int START_PAGE=0;
	private static int DEFAULT_LIMIT=10;
	
	
	private MessageUser getLoggedInUser(){
		  User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	      String name = user.getUsername(); //get logged in username
	      MessageUser currentUser=messageUserService.fetchUser(name);
	      return currentUser;
	}
	
	@RequestMapping(value="/message",method=RequestMethod.POST )
    public ResponseEntity createMessage(String message){
		MessageUser user=getLoggedInUser();
		
		Message msg=null;
		if(message!=null && message.length()>0){
			msg=new Message();
			msg.setCreatedBy(user);
			msg.setMessageDateTime(LocalDateTime.now());
			msg.setMessage(message);
			messageService.saveOrUpdateMessage(msg);
		}
		
		return new ResponseEntity<Message>(msg,HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value="/message/{messageId}", method=RequestMethod.GET )
    public ResponseEntity fetchMessage(@PathVariable("messageId") long messageId){
		
		MessageUser user=getLoggedInUser();
		Message msg=messageService.fetchMessage(messageId, user);
		return new ResponseEntity<Message>(msg,HttpStatus.OK);
	}
	
	@RequestMapping( method=RequestMethod.GET)
    public ResponseEntity fetchAllMessage(@RequestParam(value="page",required=false) Integer page,
    		@RequestParam(value="limit",required=false)Integer limit,@RequestParam(value="search",required=false) String search){
		MessageUser user=getLoggedInUser();
		if(search==null || search.trim().length()==0)search="%";
		List<Message> list=messageService.searchMessages(search,user);
		
		return new ResponseEntity<List<Message>>(list,HttpStatus.OK);
	}
	
	
	private PageRequest gotoPage(Integer page,Integer limit)
	{
		if(limit==null)limit=DEFAULT_LIMIT;
		if(page==null)page=START_PAGE;
		if(limit>MAX_LIMIT || limit<=0){
			limit=MAX_LIMIT;
		}
		if(page<START_PAGE){
			page=START_PAGE;
		}
	    PageRequest request = new PageRequest(page,limit);
	    return request;
	}
	

}
