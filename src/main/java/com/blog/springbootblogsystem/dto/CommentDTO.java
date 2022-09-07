package com.blog.springbootblogsystem.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CommentDTO {
    
    private Long id;

    @NotBlank(message = "The name must not be empty.")
    @Size(min = 5, message = "The name of the post should have at least 5 characters.")
    private String name;

    @NotBlank(message = "The email must not be empty.")
    @Email(message = "Must be a properly formatted email address.")
    private String email;

    @NotBlank(message = "The content must not be empty.")
    @Size(min = 10, message = "The content of the post should have at least 10 characters.")
    private String content;
    
    public CommentDTO() {
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
     * @return String return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return String return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
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

}