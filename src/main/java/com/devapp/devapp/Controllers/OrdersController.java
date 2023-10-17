/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devapp.devapp.Controllers;

import com.devapp.devapp.Models.Order;
import com.devapp.devapp.Services.OrdersService;
import com.devapp.devapp.Services.ShoesService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrdersController {
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private ShoesService shoesService;
    
    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }
    
    @GetMapping("/ordersManager")
    public String showOrdersManager(Model model, HttpSession session){
        int userId = (int)session.getAttribute("userId");
        List<Order> orders = ordersService.findByUserId(userId);
        model.addAttribute("orders", orders);
        return "ordersManager";
    }
}
