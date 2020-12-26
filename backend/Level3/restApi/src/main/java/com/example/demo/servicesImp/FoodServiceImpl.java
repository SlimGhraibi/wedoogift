package com.example.demo.servicesImp;

import com.example.demo.services.FoodService;
import dist.DistributionServiceFood;
import entities.Companie;
import entities.Distribution;
import entities.User;
import entities.Wallet;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class FoodServiceImpl implements FoodService {

    @Autowired
    DistributionServiceFood distributionServiceFood;

    @Override
    public Distribution distributefoodCards(Companie companie, User user, Wallet wallet, float amount) {
        return distributionServiceFood.distributefoodCards(companie, user, wallet, amount);
    }

    @Override
    public List<User> calculateUserBalance(List<Distribution> distList, List<User> userList) {
        return distributionServiceFood.calculateUserBalance(distList, userList);
    }
}
