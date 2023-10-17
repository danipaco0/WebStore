package com.devapp.devapp.Controllers;

import com.devapp.devapp.Models.ItemCart;
import com.devapp.devapp.Models.Order;
import com.devapp.devapp.Models.Shoes;
import com.devapp.devapp.Services.ItemCartService;
import com.devapp.devapp.Services.OrdersService;
import com.devapp.devapp.Services.ShoesService;
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
public class CartController {

    @Autowired
    private ItemCartService itemCartService;
    @Autowired
    private ShoesService shoesService;
    @Autowired
    private OrdersService ordersService;

    public CartController(ItemCartService itemCartService) {
        this.itemCartService = itemCartService;
    }

    @GetMapping("/cartManager")
    public String showCartManager(Model model, HttpSession session) {
        Optional<ItemCart> optCart = itemCartService.findBySessionId(session.getId());
        List<Shoes> shoes = new ArrayList<>();
        double totalPrice = 0;
        if (optCart.isPresent()) {
            List<Integer> shoesId = optCart.get().getShoesId();
            for (int id : shoesId) {
                Optional<Shoes> shoe = shoesService.findById(id);
                if (shoe.isPresent()) {
                    shoes.add(shoe.get());
                    totalPrice += shoe.get().getPrice();
                }
            }
            model.addAttribute("totalPrice", totalPrice);
            model.addAttribute("cartId", optCart.get().getId());
        }
        model.addAttribute("shoesMatch", shoes);
        return "cartManager";
    }

    @PostMapping("/cartManager/deleteItem")
    public String deleteItem(@RequestParam int id, @RequestParam int cart) {
        Optional<ItemCart> optCart = itemCartService.findById(cart);
        ItemCart itemCart = optCart.get();
        List<Integer> shoesId = itemCart.getShoesId();
        System.out.println(shoesId.size());
        shoesId.remove(shoesId.indexOf(id));
        if(shoesId.isEmpty()){
            itemCartService.deleteById(cart);
        }
        else{
            itemCart.setShoesId(shoesId);
            itemCartService.save(itemCart);
        }
        return "redirect:/cartManager";
    }
    
    @PostMapping("/cartManager/placeOrder")
    public String placeOrder(@RequestParam int cart, @RequestParam double price){
        Optional<ItemCart> optCart = itemCartService.findById(cart);
        List<String> shoesName = new ArrayList<>();
        for(int s : optCart.get().getShoesId()){
            Optional<Shoes> shoe = shoesService.findById(s);
            shoesName.add(shoe.get().getName());
        }
        Order order = new Order();
        order.setSessionId(optCart.get().getSessionId());
        order.setShoesId(optCart.get().getShoesId());
        order.setUserId(optCart.get().getUserId());
        order.setPrice(price);
        order.setShoesName(shoesName);
        ordersService.save(order);
        
        itemCartService.deleteById(cart);
        
        return "redirect:/cartManager";
    }

    @PostMapping("/clientHome")
    public String showClientHome() {
        return "redirect:/clientHome";
    }
}
