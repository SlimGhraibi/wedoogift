package com.example.demo.restController;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/food")
@CrossOrigin
public class FoodRestService {
    @GetMapping
    public Map<String, Object> getFood(){
        return null;
    }

    public void init() {

    }
}
