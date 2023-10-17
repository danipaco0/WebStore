/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.devapp.devapp.Controllers;

import com.devapp.devapp.Models.Shoes;
import com.devapp.devapp.Services.ShoesService;
import jakarta.servlet.http.HttpSession;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class ShoesController {

    @Value("${upload.images.directory}")
    String uploadDir;

    private final ShoesService shoesService;

    @Autowired
    public ShoesController(ShoesService shoesService) {
        this.shoesService = shoesService;
    }

    @GetMapping("/addShoes")
    public String showAddShoes() {
        return "addShoes";
    }

    @GetMapping("/editShoes")
    public String showEditShoes(Model model, HttpSession session) {
        Optional<Shoes> optShoes = shoesService.findById((int) session.getAttribute("shoesId"));
        Shoes shoes = optShoes.get();
        model.addAttribute("shoes", shoes);
        return "editShoes";
    }

    @PostMapping("/editShoes")
    public String editShoes(@RequestParam("name") String name, @RequestParam("price") double price,
            HttpSession session) {
        Optional<Shoes> optShoes = shoesService.findById((int)session.getAttribute("shoesId"));
        if (optShoes.isPresent()) {
            Shoes shoes = optShoes.get();
            shoes.setName(name);
            shoes.setPrice(price);
            shoesService.save(shoes);
        }
        return "redirect:/adminHome";
    }

    @PostMapping("/addShoes")
    public String addShoes(@RequestParam("name") String name, @RequestParam("price") double price,
            @RequestParam("image") MultipartFile image, Model model) {
        Optional<Shoes> optShoes = shoesService.findByName(name);
        if (!optShoes.isPresent()) {
            String imageName = image.getOriginalFilename();

            Shoes shoes = new Shoes();
            shoes.setName(name);
            shoes.setPrice(price);
            shoes.setImage(imageName);
            shoesService.save(shoes);

            try ( OutputStream outputStream = new FileOutputStream(new File(ResourceUtils.getURL(uploadDir + imageName).getPath()))) {
                outputStream.write(image.getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }

            return "redirect:/adminHome";
        } else {
            model.addAttribute("error", "Shoes already exist !");
            return "addShoes";
        }
    }

}
