package dist;


import entities.Companie;
import entities.Distribution;
import entities.User;

import java.util.List;

public interface DistributionService {
    public Distribution distributeGiftCards(Companie companie, User user, float amount);

    public List<User> calculateUserBalance(List<Distribution> distList, List<User> userList);
}
