package com.blog.springbootblogsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.springbootblogsystem.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    public Optional<User> findByEmail(String email);

    public Optional<User> findByUsernameOrEmail(String username, String email);

    public Optional<User> findByUsername(String username);

    public boolean existsByUsername(String username);

    public Boolean existsByEmail(String email);

}