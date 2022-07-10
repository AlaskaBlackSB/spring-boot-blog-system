package com.blog.springbootblogsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.springbootblogsystem.entity.Post;

public interface PostRepository extends JpaRepository<Post, Long> {
    
}