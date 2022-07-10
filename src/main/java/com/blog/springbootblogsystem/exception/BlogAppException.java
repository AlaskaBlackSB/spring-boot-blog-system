package com.blog.springbootblogsystem.exception;

import org.springframework.http.HttpStatus;

public class BlogAppException extends RuntimeException {
    
    private static final Long serialVersionUID = 1L;

    private HttpStatus status;
    private String message;

    public BlogAppException(HttpStatus status, String message){
        this.message = message;
        this.status = status;
    }

    public BlogAppException(HttpStatus status, String message, String message1){
        this.message = message;
        this.message = message1;
        this.status = status;
    }

    /**
     * @return HttpStatus return the status
     */
    public HttpStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    /**
     * @return String return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

}