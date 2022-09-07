package com.blog.springbootblogsystem.service;

public class JWTAuthResponseDTO {
    
    private String accessToken;
    private String typeToken = "Bearer";

    public JWTAuthResponseDTO(String accessToken) {
        this.accessToken = accessToken;
    }

    public JWTAuthResponseDTO(String accessToken, String typeToken) {
        this.accessToken = accessToken;
        this.typeToken = typeToken;
    }

    /**
     * @return String return the accessToken
     */
    public String getAccessToken() {
        return accessToken;
    }

    /**
     * @param accessToken the accessToken to set
     */
    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    /**
     * @return String return the typeToken
     */
    public String getTypeToken() {
        return typeToken;
    }

    /**
     * @param typeToken the typeToken to set
     */
    public void setTypeToken(String typeToken) {
        this.typeToken = typeToken;
    }

}