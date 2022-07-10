package com.blog.springbootblogsystem.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.blog.springbootblogsystem.dto.CommentDTO;
import com.blog.springbootblogsystem.entity.Comment;
import com.blog.springbootblogsystem.entity.Post;
import com.blog.springbootblogsystem.exception.BlogAppException;
import com.blog.springbootblogsystem.exception.ResourceNotFoundException;
import com.blog.springbootblogsystem.repository.CommentRepository;
import com.blog.springbootblogsystem.repository.PostRepository;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Override
    public CommentDTO create(Long postId, CommentDTO commentDTO) {
        
        // Search Post by ID, if not exist, return Exception
        Post post = postRepository.findById(postId).orElseThrow( () -> new ResourceNotFoundException("Post", "id", postId) );

        // Convert the commentDTO to the comment entity look it up in the db
        Comment comment = mapDTOToEntity(commentDTO);

        // Assign the post to the comment
        comment.setpost(post);

        // Create the comment entity in the database
        Comment newComment = commentRepository.save(comment);

        // return CommentDTO
        return mapEntityToDTO(newComment);
    }

    @Override
    public List<CommentDTO> getCommentsByPostId(Long postId) {
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments.stream().map( comment -> mapEntityToDTO(comment) ).collect(Collectors.toList());
    }

    @Override
    public CommentDTO findById(Long postId, Long commentId) {
        
        // Search Post by ID, if not exist, return Exception
        Post post = postRepository.findById(postId).orElseThrow( () -> new ResourceNotFoundException("Post", "Id", postId) );

        // Search Comment by ID, if not exist, return Exception
        Comment comment = commentRepository.findById(commentId).orElseThrow( () -> new ResourceNotFoundException("Comment", "dd", commentId) );   

        // Check if the Comment belong to the Post
        if (!comment.getpost().getId().equals(post.getId())) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "The comment does not belong to the post.");
        }

        return mapEntityToDTO(comment);
    }

    @Override
    public CommentDTO update(Long postId, Long commentId, CommentDTO commentDTO) {
        // Search Post by ID, if not exist, return Exception
        Post post = postRepository.findById(postId).orElseThrow( () -> new ResourceNotFoundException("Post", "id", postId) );

        // Search Comment by ID, if not exist, return Exception
        Comment comment = commentRepository.findById(commentId).orElseThrow( () -> new ResourceNotFoundException("Comment", "id", commentId) ); 

        // Check if the Comment belong to the Post
        if (!comment.getpost().getId().equals(post.getId())) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "The comment does not belong to the post.");
        }
        
        // Set atributes
        comment.setName(commentDTO.getName());
        comment.setEmail(commentDTO.getEmail());
        comment.setContent(commentDTO.getContent());

        // Update the comment in DB
        Comment commentUpdated = commentRepository.save(comment);

        //Return CommentDTO
        return mapEntityToDTO(commentUpdated);
    }

    @Override
    public void delete(Long postId, Long commentId) {
        // Search Post by ID, if not exist, return Exception
        Post post = postRepository.findById(postId).orElseThrow( () -> new ResourceNotFoundException("Post", "id", postId) );

        // Search Comment by ID, if not exist, return Exception
        Comment comment = commentRepository.findById(commentId).orElseThrow( () -> new ResourceNotFoundException("Comment", "id", commentId) ); 

        // Check if the Comment belong to the Post
        if (!comment.getpost().getId().equals(post.getId())) {
            throw new BlogAppException(HttpStatus.BAD_REQUEST, "The comment does not belong to the post.");
        }

        commentRepository.deleteById(commentId);
    }

    /**
     * Method to convert Entity to DTO
     * @param comment To convert to DTO
     * @return CommentDTO
     */
    private CommentDTO mapEntityToDTO(Comment comment){
        // Create CommentDTO
        CommentDTO commentDTO = modelMapper.map(comment, CommentDTO.class);

        return commentDTO;
    }

    /**
     * Method to convert DTO to Entity
     * @param comment To convert to Entity
     * @return Comment (Entity)
     */
    private Comment mapDTOToEntity(CommentDTO commentDTO) {
        // Convert Comment Entity
        Comment comment = modelMapper.map(commentDTO, Comment.class);

        return comment;
    }
    
}