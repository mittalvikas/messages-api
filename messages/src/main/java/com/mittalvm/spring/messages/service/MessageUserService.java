package com.mittalvm.spring.messages.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mittalvm.spring.messages.domain.MessageUser;
import com.mittalvm.spring.messages.repositories.MessageUserRepository;

@Service
public class MessageUserService implements UserDetailsService{

	@Autowired
	private MessageUserRepository messageUserRepository;
	
	public MessageUser fetchUser(String login){
		return messageUserRepository.findByLogin(login);
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		MessageUser user = messageUserRepository.findByLogin(userName);
        if (user == null){
            throw new UsernameNotFoundException(userName + " was not found");
        }
        return new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPassword(),
                AuthorityUtils.createAuthorityList("USER")
        );
	}
	
	
}
