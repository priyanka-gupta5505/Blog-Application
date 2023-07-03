package com.springboot.blog.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.blog.Entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

}
