package com.mittalvm.spring.messages.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.mittalvm.spring.messages.domain.MessageUser;
import com.mittalvm.spring.messages.domain.UserFollower;
import com.mittalvm.spring.messages.domain.UserFollowerBO;
public interface UserFollowerRepository extends CrudRepository<UserFollower, Long> {
	
	UserFollower findByUserAndFollowingUser(MessageUser user,MessageUser followingUser);
	List<UserFollower> findByUser(MessageUser user);
	List<UserFollower> findByFollowingUser(MessageUser user);
	@Query( value = "SELECT t1.ID,t1.USER_ID,t1.FOLLOWING_USER_ID,t2.cnt FROM USER_FOLLOW_MAPPING AS t1  JOIN ( SELECT FOLLOWING_USER_ID, COUNT(*) AS cnt FROM USER_FOLLOW_MAPPING GROUP BY FOLLOWING_USER_ID) AS t2  ON t1.FOLLOWING_USER_ID = t2.FOLLOWING_USER_ID WHERE t1.USER_ID=:userId ORDER BY CNT DESC"
	 		,nativeQuery=true)
	List<Object[]> findByPopullarFollower(@Param("userId") long userId);

}
