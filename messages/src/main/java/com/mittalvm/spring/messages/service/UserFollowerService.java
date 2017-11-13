package com.mittalvm.spring.messages.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mittalvm.spring.messages.domain.MessageUser;
import com.mittalvm.spring.messages.domain.UserFollower;
import com.mittalvm.spring.messages.domain.UserFollowerBO;
import com.mittalvm.spring.messages.repositories.UserFollowerRepository;

@Service
public class UserFollowerService {

	@Autowired
	private UserFollowerRepository userFollowerRepository;
	
	
	public void savefollowStatus(boolean follow , MessageUser currentUser,MessageUser targetUser){
		
		if(follow){  //create record in table
			UserFollower record=new UserFollower();
			record.setUser(currentUser);
			record.setFollowingUser(targetUser);
			userFollowerRepository.save(record);
			
		}else{  //delete record
			
			UserFollower record=userFollowerRepository.findByUserAndFollowingUser(currentUser, targetUser);
			userFollowerRepository.delete(record);
		}
		
	}
	
	public List<UserFollower> findFollowingByUser(MessageUser user){
		return userFollowerRepository.findByUser(user);
	}

	public List<UserFollower> findUserFollowedByUsers(MessageUser user){
		return userFollowerRepository.findByUser(user);
	}
	
	public List<UserFollowerBO> findPopularFollower(long userId){
		List<Object[]> objectList=userFollowerRepository.findByPopullarFollower(userId);
		List<UserFollowerBO> list=new ArrayList();
		for(Object[] obj:objectList){
			UserFollowerBO bo=new UserFollowerBO(obj[0].toString(),obj[1].toString(),obj[2].toString(),obj[3].toString());
			list.add(bo);
		}
		
		return list; 
	}
	
}
