package com.blog.springbootblogsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.springbootblogsystem.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
   
    public Optional<Role> findByName(String name);

}