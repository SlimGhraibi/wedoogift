package com.example.demo.services;

import entities.Companie;
import entities.Distribution;
import entities.User;
import entities.Wallet;

import java.util.List;

public interface FoodService {
    public Distribution distributefoodCards(Companie companie, User user, Wallet wallet, float amount);
    public List<User> calculateUserBalance(List<Distribution> distList, List<User> userList);
}
