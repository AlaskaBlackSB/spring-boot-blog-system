package com.blog.springbootblogsystem.service;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.blog.springbootblogsystem.dto.PostDTO;
import com.blog.springbootblogsystem.dto.PostResponse;
import com.blog.springbootblogsystem.entity.Post;
import com.blog.springbootblogsystem.exception.ResourceNotFoundException;
import com.blog.springbootblogsystem.repository.PostRepository;

@Service
public class PostServiceImpl implements PostService  {

    @Autowired
    private ModelMapper modelMapper;
    
    @Autowired
    private PostRepository postRepository;

    @Override
    public PostDTO create(PostDTO postDTO) {
        
        // Convert DOT to Entity
        Post post = mapDTOToEntity(postDTO);

        // Save Entity (Create Post in DB)
        Post newPost = postRepository.save(post);

        // Object DTO to return
        PostDTO responsePostDTO = mapEntityToDTO(newPost);

        return responsePostDTO;
    }

    @Override
    public PostResponse findAll(Integer page, Integer size, String sortBy, String sortDir) {
        
        Sort sort = 
            sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) 
            ? Sort.by(sortBy).ascending()
            : Sort.by(sortBy).descending();

        // Create a object Pageable with page number, size per page and sortBy
        Pageable pageable = PageRequest.of(page, size, sort);

        // Search for all posts with the specified page and size
        Page<Post> posts = postRepository.findAll(pageable);

        // Get for all posts with paging
        List<Post> listPosts = posts.getContent();

        // Convert each post Entity to DTO
        List<PostDTO> content = listPosts.stream().map( post -> mapEntityToDTO(post)).collect(Collectors.toList());

        PostResponse response = new PostResponse();

        // Set content (List<PostDTO>)
        response.setContent(content);
        // Set page number
        response.setPageNum(posts.getNumber());
        // Set page size
        response.setPageSize(posts.getSize());
        // Set total items
        response.setTotalItems(posts.getTotalElements());
        // Set total pages
        response.setTotalPages(posts.getTotalPages());
        // Set is last
        response.setLast(posts.isLast());

        return response;
    }

    @Override
    public PostDTO findById(Long id) {
        
        // Search Post by ID, if not exist, return Exception
        Post post = postRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Post", "Id", id) );

        return mapEntityToDTO(post);
    }

    
    @Override
    public PostDTO update(PostDTO postDTO, Long id) {
        
        // Search Post by ID, if not exist, return Exception
        Post post = postRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Post", "Id", id) );

        // Update fields
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setContent(postDTO.getContent());

        // Update post in the DB
        Post postUpdated = postRepository.save(post);

        // Return postDTO updated
        return mapEntityToDTO(postUpdated);
    }

    @Override
    public void delete(Long id) {
        
        // Search Post by ID, if not exist, return Exception
        Post post = postRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Post", "Id", id) );

        // Delete post if exist
        postRepository.delete(post);
    }
    
    
    /**
     * Method to convert Entity to DTO
     * @param post To convert to DTO
     * @return PostDTO
     */
    private PostDTO mapEntityToDTO(Post post) {

        // Convert Entity to DTO
        PostDTO postDTO = modelMapper.map(post, PostDTO.class);

        return postDTO;
    }
    
    /**
     * Method to convert DTO to Entity
     * @param post To convert to Entity
     * @return Post (Entity)
     */
    private Post mapDTOToEntity(PostDTO postDTO) {

        // Convert DTO to Entity
        Post post = modelMapper.map(postDTO, Post.class);

        return post;
    }
        
}