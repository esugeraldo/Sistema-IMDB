package com.edutecno.pruebaFinalSistemaIMDb.service;

import java.util.List;

import com.edutecno.pruebaFinalSistemaIMDb.model.User;


public interface UserService {

    public void update(User entity);
    public void delete(User entity);
    public User findUserById(Long id);
    public User findUserByUsername(String username);
    public List<User> findALL();
    public void signup(User user);
    
}
