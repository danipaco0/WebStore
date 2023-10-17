/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devapp.devapp.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name="Orders")
public class Order {
    @Id //PK
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private int userId;
    private String sessionId;
    private List<Integer> shoesId;
    private List<String> shoesName;

    public List<String> getShoesName() {
        return shoesName;
    }

    public void setShoesName(List<String> shoesName) {
        this.shoesName = shoesName;
    }
    private double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<Integer> getShoesId() {
        return shoesId;
    }

    public void setShoesId(List<Integer> shoesId) {
        this.shoesId = shoesId;
    }


    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
