package dist;

import entities_food.Companie;
import entities_food.Distribution;
import entities_food.User;
import entities_food.Wallet;

import java.util.List;

public interface DistributionServiceFood {
    public Distribution distributefoodCards(Companie companie, User user, Wallet wallet, float amount);
    public List<User> calculateUserBalance(List<Distribution> distList, List<User> userList);
}

