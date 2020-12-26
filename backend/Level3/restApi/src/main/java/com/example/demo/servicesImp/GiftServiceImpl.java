package com.example.demo.servicesImp;

import com.example.demo.services.GiftService;
import entities_gift.*;
import org.springframework.stereotype.Service;
import utils.Utils;

import java.util.List;

@Service
public class GiftServiceImpl implements GiftService {

    @Override
    public Distribution distributeGiftCards(Companie companie, User user, float amount) {
        if (Utils.checkCompanieBalance(companie, amount)) {
            companie.setBalance(companie.getBalance() - amount);
            return Utils.getDistribution(user, companie, amount);
        }
        return null;
    }

    @Override
    public List<User> calculateUserBalance(List<Distribution> distList, List<User> userList) {
        User user;
        for (Distribution dist : distList) {
            user = Utils.getUserByID(userList, dist.getUser_id());
            user.setBalance(dist.getAmount() + user.getBalance());
        }
        return userList;
    }
}
