package com.blog.springbootblogsystem.controllers;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blog.springbootblogsystem.dto.LoginDTO;
import com.blog.springbootblogsystem.dto.SignUpDTO;
import com.blog.springbootblogsystem.entity.Role;
import com.blog.springbootblogsystem.entity.User;
import com.blog.springbootblogsystem.repository.RoleRepository;
import com.blog.springbootblogsystem.repository.UserRepository;
import com.blog.springbootblogsystem.service.JWTAuthResponseDTO;
import com.blog.springbootblogsystem.service.JwtTokenProvider;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping(value="/login")
    public ResponseEntity<JWTAuthResponseDTO> authenticateUser(@RequestBody LoginDTO loginDTO) {
        
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));

        // Obtnemos el token del JwtTokenProvider
        String token = jwtTokenProvider.generateToken(authentication);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        return ResponseEntity.ok(new JWTAuthResponseDTO(token));
    }

    @PostMapping(value="/signup")
    public ResponseEntity<?> signupUser(@RequestBody SignUpDTO signUpDTO) {
        
        if (userRepository.existsByUsername(signUpDTO.getUsername())) {
            return new ResponseEntity<>("Username has already been registered.", HttpStatus.BAD_REQUEST);
        }

        if (userRepository.existsByEmail(signUpDTO.getEmail())) {
            return new ResponseEntity<>("Email has already been registered.", HttpStatus.BAD_REQUEST);
        }

        // Crea el usuario que se va a registrar
        User user = new User();
        user.setName(signUpDTO.getName());
        user.setUsername(signUpDTO.getUsername());
        user.setEmail(signUpDTO.getEmail());
        user.setPassword(passwordEncoder.encode(signUpDTO.getPassword()));

        // Busca el role de administrador
        Role roles = roleRepository.findByName("ROLE_ADMIN").get();

        // Asigna el rolede administrador al usuario
        user.setRoles(Collections.singleton(roles));

        // Guarda el usuario en la DB
        userRepository.save(user);

        return new ResponseEntity<>("You have successfully registered.", HttpStatus.OK);
    }
    


}