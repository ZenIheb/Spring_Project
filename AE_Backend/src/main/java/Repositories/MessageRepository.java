package Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import Models.Message;

public interface MessageRepository extends JpaRepository<Message,Long> {
	 @Query("SELECT m FROM Message m WHERE m.user.id = :userId AND m.discussion = :discussionId")
	    List<Message> findMessagesByUserIdAndDiscussionId(@Param("userId") Long userId, @Param("discussionId") Long discussionId);
	 }
