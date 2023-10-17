/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devapp.devapp.Services;

import com.devapp.devapp.Models.User;
import com.devapp.devapp.Repos.UserRepo;
import java.util.List;
import java.util.Optional;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepo userRepo;
    
    public UserService(UserRepo userRepo){
        this.userRepo = userRepo;
    }
    
    public Optional<User> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }
    
    public List<User> findAll(){
        return userRepo.findAll();
    }
    
    public User save(User user){
        String sel = BCrypt.gensalt();
        String encoded = BCrypt.hashpw(user.getPassword(), sel);
        user.setPassword(encoded);
        return userRepo.save(user);
    }
    
    public boolean checkPassword(String password, String encoded){
        return BCrypt.checkpw(password, encoded);
    }
    
    public void deleteUser(int id){
        userRepo.deleteById(id);
    }  
}
