package com.blog.springbootblogsystem.secutiry;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint{

    /**
     * Permite controlar los errores de que un usuario no está autenticado
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException authException) throws IOException, ServletException {
        // Envia un error de que no esta autorizado
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
        

    }
    
}