package com.example.demo.restController;

import com.example.demo.services.FoodService;
import entities_food.Companie;
import entities_food.Distribution;
import entities_food.User;
import entities_food.Wallet;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.UtilsFood;

import javax.annotation.security.RolesAllowed;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/food")
@CrossOrigin
public class FoodRestService {
    List<User> userList = new ArrayList<>();
    List<Companie> companieList = new ArrayList<>();
    List<Distribution> distributions = new ArrayList<>();
    List<Wallet> walletList = new ArrayList<>();

    @Autowired
    FoodService foodService;

    @Autowired
    ResourceLoader resourceLoader;

    @RolesAllowed("ADMIN")
    @GetMapping
    public Map<String, Object> getFood() throws IOException {
        init();
        // Création des distributions d'aprés l'input
        userList.forEach(user -> {
            Distribution dist;
            if (user.getId() == 1) {
                dist = foodService.distributefoodCards(companieList.get(0), userList.get(0), walletList.get(0), 50);
                distributions.add(dist);
                dist = foodService.distributefoodCards(companieList.get(0), userList.get(0), walletList.get(1), 250);
                distributions.add(dist);
            }
            if (user.getId() == 2) {
                dist = foodService.distributefoodCards(companieList.get(0), userList.get(1), walletList.get(0), 100);
                distributions.add(dist);
            }
            if (user.getId() == 3) {
                dist = foodService.distributefoodCards(companieList.get(1), userList.get(2), walletList.get(0), 1000);
                distributions.add(dist);
            }
        });
        userList = foodService.calculateUserBalance(distributions, userList);
        Map<String, Object> map = new HashMap<>();
        map.put("distributions", distributions);
        map.put("companies", companieList);
        map.put("users", userList);
        return map;
    }

    public void init() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:data/inputFood.json");
        InputStream is = resource.getInputStream();

        if (is == null) {
            throw new NullPointerException("Cannot find resource file " + resource);
        }
        JSONTokener tokener = new JSONTokener(is);
        JSONObject object = new JSONObject(tokener);
        userList = UtilsFood.getUsers(object);
        companieList = UtilsFood.getCompanies(object);
        walletList = UtilsFood.getWallets(object);
        distributions = new ArrayList<>();
    }
}
