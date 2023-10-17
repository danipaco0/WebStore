/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devapp.devapp.Controllers;

import com.devapp.devapp.Models.Order;
import com.devapp.devapp.Models.Shoes;
import com.devapp.devapp.Models.User;
import com.devapp.devapp.Services.OrdersService;
import com.devapp.devapp.Services.ShoesService;
import com.devapp.devapp.Services.UserService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private ShoesService shoesService;
    @Autowired
    private OrdersService ordersService;

    @GetMapping("/adminHome")
    public String showAdminHome(Model model) {
        List<User> users = userService.findAll();
        List<Shoes> shoes = shoesService.findAll();
        List<Order> orders = ordersService.findAll();
        model.addAttribute("users", users);
        model.addAttribute("shoes", shoes);
        model.addAttribute("orders", orders);
        return "adminHome";
    }

    @PostMapping("/adminHome/deleteUser")
    public String deleteUser(@RequestParam int id) {
        userService.deleteUser(id);
        return "redirect:/adminHome";
    }

    @PostMapping("/adminHome/deleteShoes")
    public String deleteShoes(@RequestParam int id) {
        shoesService.deleteShoes(id);
        return "redirect:/adminHome";
    }

    @PostMapping("/adminHome/editShoes")
    public String editShoes(@RequestParam int id, HttpSession session) {
        session.setAttribute("shoesId", id);
        return "redirect:/editShoes";
    }

    @PostMapping("/adminHome/addShoes")
    public String addShoes() {
        return "redirect:/addShoes";
    }
    
    @PostMapping("/adminHome/deleteOrder")
    public String deleteOrder(@RequestParam int id){
        ordersService.deleteById(id);
        return "redirect:/adminHome";
    }
}
