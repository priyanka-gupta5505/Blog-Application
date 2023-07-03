package com.springboot.blog.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.blog.Entity.Role;

public interface RoleRepository  extends JpaRepository<Role, Long>{

	Optional<Role> findByName(String name);
}
