package com.blog.springbootblogsystem.dto;

import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.blog.springbootblogsystem.entity.Comment;

public class PostDTO {
    
    private Long id;

    @NotBlank(message = "The title must not be empty.")
    @Size(min = 5, message = "The title of the post should have at least 5 characters.")
    private String title;

    @NotBlank(message = "The description must not be empty.")
    @Size(min = 10, message = "The description of the post should have at least 10 characters.")
    private String description;

    @NotBlank(message = "The content must not be empty.")
    @Size(min = 10, message = "The content of the post should have at least 10 characters.")
    private String content;

    private Set<Comment> comments;

    public PostDTO(){

    }

    public PostDTO(Long id, String title, String description, String content) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.content = content;
    }

    /**
     * @return Long return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return String return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return String return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return String return the content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }
    
    /**
     * @return Set<Comment> return the comments
     */
    public Set<Comment> getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }

}