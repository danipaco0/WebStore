package com.devapp.devapp.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name="itemCart")
public class ItemCart {
    @Id //Pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    private int userId;
    private List<Integer> shoesId;
    private String sessionId;

    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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
}
