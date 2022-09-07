package com.blog.springbootblogsystem.service;

import com.blog.springbootblogsystem.dto.PostDTO;
import com.blog.springbootblogsystem.dto.PostResponse;

public interface PostService {
    
    public PostDTO findById(Long id);

    public PostResponse findAll(Integer page, Integer size, String sortBy, String sortDir);

    public PostDTO create(PostDTO postDTO);

    public PostDTO update(PostDTO postDTO, Long id);

    public void delete(Long id);

}