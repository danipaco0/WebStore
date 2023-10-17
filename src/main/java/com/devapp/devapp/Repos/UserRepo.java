/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.devapp.devapp.Repos;

import com.devapp.devapp.Models.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepo extends JpaRepository<User, Integer>{
    Optional<User> findByUsername(String username);
}
