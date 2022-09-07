package com.blog.springbootblogsystem.secutiry;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blog.springbootblogsystem.entity.Role;
import com.blog.springbootblogsystem.repository.UserRepository;

@Service
public class CustomeUserDetailsService implements UserDetailsService{

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        // Utiliza el import completo porque la clase User de UserDetails se llama igual
        com.blog.springbootblogsystem.entity.User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail).orElseThrow(()-> new UsernameNotFoundException("User not found with this username or email: ".concat("usernameOrEmail") ));

        // Retorna el usuario de UserDetails
        return new User(user.getUsername(), user.getPassword(), mapRoles(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRoles(Set<Role> roles){
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
    }
    
}