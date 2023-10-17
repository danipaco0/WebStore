package com.devapp.devapp.Services;

import com.devapp.devapp.Models.Order;
import com.devapp.devapp.Repos.OrdersRepo;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class OrdersService {
    private final OrdersRepo ordersRepo;
    
    public OrdersService(OrdersRepo ordersRepo){
        this.ordersRepo = ordersRepo;
    }
    
    public Optional<Order> findById(int id) {
        return ordersRepo.findById(id);
    }
    
    public List<Order> findAll(){
        return ordersRepo.findAll();
    }
    
    public List<Order> findByUserId(int id){
        return ordersRepo.findByUserId(id);
    }
    
    public Order save(Order orders){
        return ordersRepo.save(orders);
    }
    
    public void deleteById(int id){
        ordersRepo.deleteById(id);
    } 
}
