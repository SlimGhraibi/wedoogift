package dist;

import entities.Companie;
import entities.Distribution;
import entities.User;
import entities.Wallet;

import java.util.List;

public interface DistributionService {
    public Distribution distributefoodCards(Companie companie, User user, Wallet wallet, float amount);
    public void calculateUserBalance(List<Distribution> distList, List<User> userList);
}

