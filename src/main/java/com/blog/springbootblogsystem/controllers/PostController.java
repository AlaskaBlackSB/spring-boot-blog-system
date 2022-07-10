package com.blog.springbootblogsystem.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.blog.springbootblogsystem.dto.PostDTO;
import com.blog.springbootblogsystem.dto.PostResponse;
import com.blog.springbootblogsystem.service.PostService;
import com.blog.springbootblogsystem.util.AppConstants;

import javax.validation.Valid;

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
@RequestMapping("/api/posts")
public class PostController {
    
    @Autowired
    private PostService postService;

    /**
     * Method to get all the post with pagination (can be optional)
     * @param page Number de actual page
     * @param size Number of items for page
     * @param sortBy Order post by field name
     * @param sortDir type of sort direction
     * @return List of posts with pagination (can be optional)
     */
    @GetMapping()
    public PostResponse findAll(
        @RequestParam(value = "page", defaultValue = AppConstants.NUMBER_PAGE_DEFAULT, required = false) Integer page, 
        @RequestParam(value = "size", defaultValue = AppConstants.SIZE_PER_PAGE_DEFAULT, required = false) Integer size, 
        @RequestParam(value = "sortBy", defaultValue = AppConstants.ORDER_BY_DEFAULT, required = false) String sortBy, 
        @RequestParam(value = "sortDir", defaultValue = AppConstants.ORDER_DIR_DEFAULT, required = false) String sortDir
        ){
        return postService.findAll(page, size, sortBy, sortDir);
    }

    /**
     * Method to get one post by Id
     * @return post
     */
    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> findById(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok(postService.findById(id));
    }
    
    /**
     * Method to create new post
     * 
     * @param postDTO post to be created
     * @return ResponseEntity<postDTO, HttpStatus>
     */
    @PostMapping()
    public ResponseEntity<PostDTO> store(@Valid @RequestBody PostDTO postDTO) {
        
        return new ResponseEntity<>(
            postService.create(postDTO), 
            HttpStatus.CREATED
        );
        
    }
    
    /**
     * Method to update post
     * 
     * @param postDTO post to be updated
     * @return ResponseEntity<postDTO, HttpStatus>
     */
    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> update(@Valid @RequestBody PostDTO postDTO, @PathVariable(name = "id") Long id) {
        
        // Send postDTO and Id to be updated
        PostDTO responsepost = postService.update(postDTO, id);

        // Return post updated and status ok
        return new ResponseEntity<>(
            responsepost, 
            HttpStatus.OK
        );
        
    }
    
    /**
     * Method to delete one post by id
     * 
     * @param postDTO post to be deleted
     * @return ResponseEntity<String, HttpStatus>
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable(name = "id") Long id) {
        
        // Send post id to delete
        postService.delete(id);

        // Return message and status ok
        return new ResponseEntity<>(
            "post deleted successfully", 
            HttpStatus.OK
        );
        
    }
    
    

}