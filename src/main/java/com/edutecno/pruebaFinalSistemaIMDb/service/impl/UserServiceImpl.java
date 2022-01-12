package com.edutecno.pruebaFinalSistemaIMDb.service.impl;

import java.util.ArrayList;
import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.edutecno.pruebaFinalSistemaIMDb.model.User;
import com.edutecno.pruebaFinalSistemaIMDb.repository.UserRepository;
import com.edutecno.pruebaFinalSistemaIMDb.service.UserService;

@Service
public class UserServiceImpl implements UserService, UserDetailsService{

    @Autowired
    UserRepository userRepository;  

    @Override
    public void update(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            user.setId(user.getId());
            userRepository.save(user);
        }        
    }

    @Override
    public void delete(User user) {
        userRepository.delete(user);       
    }

    @Override
    public User  findUserById(Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public List<User> findALL() {
        return userRepository.findAll();
    }

    
    @Override
    public void signup(User user) {
        if (!userRepository.existsByUsername(user.getUsername())) {
            userRepository.save(user);
        }        
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).get();
        if (user == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");                
        }
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(user.getRoles().toString()));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }

    @Override
    public User findUserByUsername(String username) {
        
        return userRepository.findByUsername(username).get();
    }
   
}
