package com.mittalvm.spring.messages.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "user_follow_mapping")
public class UserFollower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToOne
    private MessageUser user;
    @OneToOne
    private MessageUser followingUser;
    
    @Transient
    private int cnt;
    
    
    
	public int getCnt() {
		return cnt;
	}
	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public MessageUser getUser() {
		return user;
	}
	public void setUser(MessageUser user) {
		this.user = user;
	}
	public MessageUser getFollowingUser() {
		return followingUser;
	}
	public void setFollowingUser(MessageUser followingUser) {
		this.followingUser = followingUser;
	}
	@Override
	public String toString() {
		return "UserFollower [user=" + user + ", followingUser=" + followingUser + ", cnt=" + cnt + "]";
	}
	
    

    
}