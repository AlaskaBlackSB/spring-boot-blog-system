package com.blog.springbootblogsystem.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.springbootblogsystem.dto.CommentDTO;
import com.blog.springbootblogsystem.service.CommentService;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;


@RestController()
@RequestMapping("/api/")
public class CommentController {
    
    @Autowired
    private CommentService commentService;

    @GetMapping(value="/posts/{postId}/comments")
    public List<CommentDTO> getCommentsByPostId(@PathVariable(value = "postId") Long postId) {
        return commentService.getCommentsByPostId(postId);
    }

    @GetMapping(value="/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> getCommentById(
        @PathVariable(value = "postId") Long postId,
        @PathVariable(value = "commentId") Long commentId
        ) {
        CommentDTO commentDTO = commentService.findById(postId, commentId);
        return new ResponseEntity<>(commentDTO, HttpStatus.OK);
    }
    
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDTO> create(@PathVariable(name = "postId") Long postId, @Valid @RequestBody CommentDTO commentDTO) {
        
        return new ResponseEntity<>(commentService.create(postId, commentDTO), HttpStatus.OK);
    }

    @PutMapping(value="/posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDTO> update(
        @PathVariable(value = "postId") Long postId,
        @PathVariable(value = "commentId") Long commentId, 
        @Valid @RequestBody CommentDTO commentDTO
        ) {
        
        CommentDTO commentUpdated = commentService.update(postId, commentId, commentDTO);

        return new ResponseEntity<>(
            commentUpdated, 
            HttpStatus.OK
        );
    }

    @DeleteMapping(value="/posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> delete(
        @PathVariable(value = "postId") Long postId,
        @PathVariable(value = "commentId") Long commentId
        ) {
        
        commentService.delete(postId, commentId);

        return new ResponseEntity<>(
            "Comment deleted successfully.",
            HttpStatus.OK
        );
    }

}