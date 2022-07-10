package com.blog.springbootblogsystem.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.springbootblogsystem.dto.PublicationDTO;
import com.blog.springbootblogsystem.service.PublicationService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/publications")
public class PublicationController {
    
    @Autowired
    private PublicationService publicationService;

    /**
     * Method to get all the publication
     * @return List of publications
     */
    @GetMapping()
    public List<PublicationDTO> findAll() {
        return publicationService.findAll();
    }

    /**
     * Method to get one publication by Id
     * @return publication
     */
    @GetMapping("/{id}")
    public ResponseEntity<PublicationDTO> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(publicationService.findById(id));
    }
    
    /**
     * Method to create new publication
     * 
     * @param publicationDTO publication to be created
     * @return ResponseEntity<PublicationDTO, HttpStatus>
     */
    @PostMapping()
    public ResponseEntity<PublicationDTO> store(@RequestBody PublicationDTO publicationDTO) {
        
        return new ResponseEntity<>(
            publicationService.create(publicationDTO), 
            HttpStatus.CREATED
        );
        
    }
    
    /**
     * Method to update publication
     * 
     * @param publicationDTO publication to be updated
     * @return ResponseEntity<PublicationDTO, HttpStatus>
     */
    @PutMapping("/{id}")
    public ResponseEntity<PublicationDTO> update(@RequestBody PublicationDTO publicationDTO, @PathVariable(name = "id") Long id) {
        
        // Send PublicationDTO and Id to be updated
        PublicationDTO responsePublication = publicationService.update(publicationDTO, id);

        // Return publication updated and status ok
        return new ResponseEntity<>(
            responsePublication, 
            HttpStatus.OK
        );
        
    }
    
    /**
     * Method to delete one publication by id
     * 
     * @param publicationDTO publication to be deleted
     * @return ResponseEntity<String, HttpStatus>
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") Long id) {
        
        // Send publication id to delete
        publicationService.delete(id);

        // Return message and status ok
        return new ResponseEntity<>(
            "Publication deleted successfully", 
            HttpStatus.OK
        );
        
    }
    
    

}