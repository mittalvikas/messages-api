package com.mittalvm.spring.messages.domain;

public class UserFollowerBO {

    private String id;
    private String userId;
    private String followerUserId;
    private String cnt;
	
    
    
    
    public UserFollowerBO(String id, String userId, String followerUserId, String cnt) {
		super();
		this.id = id;
		this.userId = userId;
		this.followerUserId = followerUserId;
		this.cnt = cnt;
	}




	public String getId() {
		return id;
	}




	public void setId(String id) {
		this.id = id;
	}




	public String getUserId() {
		return userId;
	}




	public void setUserId(String userId) {
		this.userId = userId;
	}




	public String getFollowerUserId() {
		return followerUserId;
	}




	public void setFollowerUserId(String followerUserId) {
		this.followerUserId = followerUserId;
	}




	public String getCnt() {
		return cnt;
	}




	public void setCnt(String cnt) {
		this.cnt = cnt;
	}




	@Override
	public String toString() {
		return "UserFollowerBO [id=" + id + ", userId=" + userId + ", followerUserId=" + followerUserId + ", cnt=" + cnt
				+ "]";
	}
    
   	
    

    
}