package com.devapp.devapp.Services;

import com.devapp.devapp.Models.Shoes;
import com.devapp.devapp.Repos.ShoesRepo;
import java.io.File;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;


@Service
public class ShoesService {
    private final ShoesRepo shoesRepo;
    
    public ShoesService(ShoesRepo shoesRepo){
        this.shoesRepo = shoesRepo;
    }
    
    public Optional<Shoes> findByName(String name) {
        return shoesRepo.findByName(name);
    }
    
    public Optional<Shoes> findById(int id){
        return shoesRepo.findById(id);
    }
    
    public List<Shoes> findAll(){
        return shoesRepo.findAll();
    }
    
    public Shoes save(Shoes shoes){
        return shoesRepo.save(shoes);
    }
    
    public void deleteShoes(int id){
        Optional<Shoes> optShoes = shoesRepo.findById(id);
        Shoes shoes = optShoes.get();
        String path = "src/main/resources/static/images"+shoes.getImage();
        
        File imageFile = new File(path);
        imageFile.delete();
        shoesRepo.deleteById(id);
    }  
}
