package com.blog.springbootblogsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.springbootblogsystem.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{
    
    public List<Comment> findByPostId(long postId);

}