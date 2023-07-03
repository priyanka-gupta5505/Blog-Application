package com.springboot.blog.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.blog.Entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	
	List<Comment> findByPostId(long postId);

}
