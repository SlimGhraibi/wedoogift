package com.example.demo.servicesImp;

import com.example.demo.services.FoodService;


import entities_food.*;
import org.springframework.stereotype.Service;
import utils.UtilsFood;

import java.util.ArrayList;
import java.util.List;

@Service
public class FoodServiceImpl implements FoodService {


    @Override
    public entities_food.Distribution distributefoodCards(entities_food.Companie companie, entities_food.User user, Wallet wallet, float amount) {
        if (UtilsFood.checkCompanieBalance(companie, amount)) {
            companie.setBalance(companie.getBalance() - amount);
            return UtilsFood.getDistribution(user, companie, wallet, amount);
        }
        return null;
    }

    @Override
    public List<entities_food.User> calculateUserBalance(List<entities_food.Distribution> distList, List<entities_food.User> userList) {
        User user;
        for (entities_food.Distribution dist : distList) {
            user = UtilsFood.getUserByID(userList, dist.getUser_id());
            if (UtilsFood.checkWalletId(user.getBalance(), dist.getWallet_id())) {
                user.getBalance().forEach(balance -> {
                    if (balance.getWallet_id() == dist.getWallet_id()) {
                        balance.setAmount(balance.getAmount() + dist.getAmount());
                    }
                });
            } else {
                List<Balance> b = new ArrayList<>();
                b.add(new Balance(dist.getWallet_id(), dist.getAmount()));
                user.getBalance().addAll(b);
            }
        }
        return userList;
    }
}
