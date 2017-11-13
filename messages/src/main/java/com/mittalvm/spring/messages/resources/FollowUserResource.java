package com.mittalvm.spring.messages.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mittalvm.spring.messages.domain.MessageUser;
import com.mittalvm.spring.messages.domain.UserFollowerBO;
import com.mittalvm.spring.messages.service.MessageUserService;
import com.mittalvm.spring.messages.service.UserFollowerService;

@RestController
public class FollowUserResource {

	@Autowired
	private UserFollowerService userFollowerService;
	
	@Autowired
	private MessageUserService messageUserService;
	
	
	@RequestMapping(value="/follow/{userName}",method=RequestMethod.POST)
	public ResponseEntity followUser(@PathVariable("userName") String userName){
		  User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	      String name = user.getUsername(); //get logged in username
	      MessageUser currentUser=messageUserService.fetchUser(name);
	      MessageUser targetUser=messageUserService.fetchUser(userName);
	      
	      if(targetUser==null){
	    	  return new ResponseEntity(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE);
	      }
	      userFollowerService.savefollowStatus(true, currentUser, targetUser);
	      
	      return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(value="/unfollow/{userName}",method=RequestMethod.POST)
	public ResponseEntity unfollowUser(@PathVariable("userName") String userName){
		  User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	      String name = user.getUsername(); //get logged in username
	      MessageUser currentUser=messageUserService.fetchUser(name);
	      MessageUser targetUser=messageUserService.fetchUser(userName);
	      
	      if(targetUser==null){
	    	  return new ResponseEntity(HttpStatus.REQUESTED_RANGE_NOT_SATISFIABLE);
	      }
	      userFollowerService.savefollowStatus(false, currentUser, targetUser);
	      return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
	
	
	@RequestMapping(value="/user/follower",method=RequestMethod.GET)
	public ResponseEntity listUserFollower(){
		  User user = (User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	      String name = user.getUsername(); //get logged in username
	      MessageUser currentUser=messageUserService.fetchUser(name);
	      
	      List<UserFollowerBO> list=userFollowerService.findPopularFollower(currentUser.getId());
	      return new ResponseEntity(list,HttpStatus.OK);
	}
	
	
}
