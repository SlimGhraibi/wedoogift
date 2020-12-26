package com.example.demo.servicesImp;

import com.example.demo.services.GiftService;
import dist.DistributionService;
import entities.Companie;
import entities.Distribution;
import entities.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class GiftServiceImpl implements GiftService {

    @Autowired
    private DistributionService distributionService;

    @Override
    public Distribution distributeGiftCards(Companie companie, User user, float amount) {
        return distributionService.distributeGiftCards(companie, user, amount);
    }

    @Override
    public List<User> calculateUserBalance(List<Distribution> distList, List<User> userList) {
        return distributionService.calculateUserBalance(distList, userList);
    }
}
