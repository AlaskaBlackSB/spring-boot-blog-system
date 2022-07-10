package com.blog.springbootblogsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blog.springbootblogsystem.entity.Publication;

public interface PublicationRepository extends JpaRepository<Publication, Long> {
    
}