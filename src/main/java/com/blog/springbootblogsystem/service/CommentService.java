package com.blog.springbootblogsystem.service;

import java.util.List;

import com.blog.springbootblogsystem.dto.CommentDTO;

public interface CommentService {
    
    public CommentDTO findById(Long postId, Long commetId);

    // public CommentResponse findAll(Integer page, Integer size, String sortBy, String sortDir);

    public CommentDTO create(Long id, CommentDTO commentDTO);

    public List<CommentDTO> getCommentsByPostId(Long postId);

    public CommentDTO update(Long postId, Long commentId, CommentDTO commentDTO);

    public void delete(Long postId, Long commentId);

}