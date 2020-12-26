package com.example.demo.restController;

import com.example.demo.services.GiftService;
import distImpl.DistributionServiceImp;
import entities.Companie;
import entities.Distribution;
import entities.User;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import utils.Utils;


import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/gift")
@CrossOrigin
public class GiftRestService {
    List<User> userList;
    List<Companie> companieList;
    List<Distribution> distributions;
    String output;

    GiftService giftService;

    @GetMapping
    public Map<String, Object> getFood() {
        init();
        // Création des distributions d'aprés l'input
        userList.forEach(user -> {
            Distribution dist;
            if (user.getId() == 1) {
                dist = giftService.distributeGiftCards(companieList.get(0), userList.get(0), 50);
                distributions.add(dist);
            }
            if (user.getId() == 2) {
                dist = giftService.distributeGiftCards(companieList.get(0), userList.get(1), 100);
                distributions.add(dist);
            }
            if (user.getId() == 3) {
                dist = giftService.distributeGiftCards(companieList.get(1), userList.get(2), 1000);
                distributions.add(dist);
            }
        });
        userList = giftService.calculateUserBalance(distributions, userList);
        Map<String, Object> map = new HashMap<>();
        map.put("distributions", distributions);
        map.put("companies", companieList);
        map.put("users", userList);
        return map;
    }

    public void init() {
            String input = "data/inputGift.json";
            output = "outputGift.json";
            InputStream is = GiftRestService.class.getResourceAsStream(input);
            if (is == null) {
                throw new NullPointerException("Cannot find resource file " + input);
            }
            JSONTokener tokener = new JSONTokener(is);
            JSONObject object = new JSONObject(tokener);
            userList = Utils.getUsers(object);
            companieList = Utils.getCompany(object);
    }
}
