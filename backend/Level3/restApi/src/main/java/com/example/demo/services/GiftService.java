package com.example.demo.services;

import entities_gift.*;
import java.util.List;


public interface GiftService {
    public Distribution distributeGiftCards(Companie companie, User user, float amount);
    public List<User> calculateUserBalance(List<Distribution> distList, List<User> userList);
}
