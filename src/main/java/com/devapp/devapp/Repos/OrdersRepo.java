package com.devapp.devapp.Repos;

import com.devapp.devapp.Models.Order;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrdersRepo extends JpaRepository<Order, Integer>{
    Optional<Order> findById(int id);
    List<Order> findByUserId(int id);
}
