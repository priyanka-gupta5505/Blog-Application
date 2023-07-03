package com.springboot.blog.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.blog.Entity.Category;
import com.springboot.blog.Entity.Post;

@Repository

public interface PostRepository extends JpaRepository<Post, Long> {
	
	 List<Post> findByCategoryId(Long categoryId);

}
