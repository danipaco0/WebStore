/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devapp.devapp.Controllers;

import com.devapp.devapp.Models.ItemCart;
import com.devapp.devapp.Models.Shoes;
import com.devapp.devapp.Models.User;
import com.devapp.devapp.Services.ItemCartService;
import com.devapp.devapp.Services.ShoesService;
import com.devapp.devapp.Services.UserService;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ClientController {

    @Autowired
    private ShoesService shoesService;
    @Autowired
    private UserService userService;
    @Autowired
    private ItemCartService itemCartService;

    @GetMapping("/clientHome")
    public String showClientHome(Model model, HttpSession session) {
        List<Shoes> shoes = shoesService.findAll();
        model.addAttribute("shoes", shoes);

        return "clientHome";
    }

    @PostMapping("/addToCart")
    public String addToCart(@RequestParam("id") int shoesId, HttpSession session) {
        List<Integer> shoes = new ArrayList<>();
        String username = (String) session.getAttribute("username");
        Optional<User> optUser = userService.findByUsername(username);
        User user = optUser.get();
        ItemCart itemCart;
        Optional<ItemCart> optCart = itemCartService.findBySessionId(session.getId());
        if(optCart.isPresent()){
            itemCart = optCart.get();
            shoes = optCart.get().getShoesId();
        }
        else{
            itemCart = new ItemCart();
            itemCart.setUserId(user.getId());
            itemCart.setSessionId(session.getId());
        }
        shoes.add(shoesId);
        itemCart.setShoesId(shoes);

        itemCartService.save(itemCart);

        return "redirect:/clientHome";
    }

    @PostMapping("/cartManager")
    public String cartManager() {
        return "redirect:/cartManager";
    }

    @PostMapping("/ordersManager")
    public String ordersManager() {
        return "redirect:/ordersManager";
    }
    
    @PostMapping("/logout")
    public String logout(HttpSession session){
        Optional<ItemCart> optCart = itemCartService.findBySessionId(session.getId());
        if(optCart.isPresent()){
            itemCartService.deleteById(optCart.get().getId());
        }
        return "redirect:/login";
    }
}
