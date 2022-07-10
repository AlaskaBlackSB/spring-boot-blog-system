package com.blog.springbootblogsystem.service;

import java.util.List;

import com.blog.springbootblogsystem.dto.PublicationDTO;

public interface PublicationService {
    
    public PublicationDTO findById(Long id);

    public List<PublicationDTO> findAll();

    public PublicationDTO create(PublicationDTO publicationDTO);

    public PublicationDTO update(PublicationDTO publicationDTO, Long id);

    public void delete(Long id);

}