package com.devapp.devapp.Controllers;

import com.devapp.devapp.Models.User;
import com.devapp.devapp.Services.UserService;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username, @RequestParam("password") String password, 
                        @RequestParam("action") String action, Model model, @ModelAttribute User user, HttpSession session) {
        Optional<User> optUser = userService.findByUsername(username);
        if(action.equals("login")) {
            if (optUser.isPresent() && userService.checkPassword(password, optUser.get().getPassword())) {
                session.setAttribute("username", username);
                session.setAttribute("userId", optUser.get().getId());
                if (!optUser.get().getIsAdmin()) {
                    return "redirect:/clientHome";
                } else {
                    return "redirect:/adminHome";
                }
            } else {
                model.addAttribute("error", "Incorrect credentials!");
                return "login";
            }
        }
        else
        {
            if(!optUser.isPresent()){
                session.setAttribute("username", username);
                userService.save(user);
                return "redirect:/clientHome";
            }
            else{
                model.addAttribute("error", "User already exists !");
                return "login";
            }
        }
    }
}
