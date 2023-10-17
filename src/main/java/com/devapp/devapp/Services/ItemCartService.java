package com.devapp.devapp.Services;

import com.devapp.devapp.Models.ItemCart;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.devapp.devapp.Repos.ItemCartRepo;
import java.util.List;

@Service
public class ItemCartService {
    private final ItemCartRepo itemCartRepo;
    
    public ItemCartService(ItemCartRepo itemCartRepo){
        this.itemCartRepo = itemCartRepo;
    }
    
    public Optional<ItemCart> findBySessionId(String sessionId) {
        return itemCartRepo.findBySessionId(sessionId);
    }
    
    public ItemCart save(ItemCart cart){
        return itemCartRepo.save(cart);
    }
    
    public void deleteById(int id){
        itemCartRepo.deleteById(id);
    }
    
    public Optional<ItemCart> findById(int id){
        return itemCartRepo.findById(id);
    }
}
