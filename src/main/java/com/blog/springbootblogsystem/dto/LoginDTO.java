package com.blog.springbootblogsystem.dto;

public class LoginDTO {
    
    private String usernameOrEmail;
    private String password;

    /**
     * @return String return the usernameOrEmail
     */
    public String getUsernameOrEmail() {
        return usernameOrEmail;
    }

    /**
     * @param usernameOrEmail the usernameOrEmail to set
     */
    public void setUsernameOrEmail(String usernameOrEmail) {
        this.usernameOrEmail = usernameOrEmail;
    }

    /**
     * @return String return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

}