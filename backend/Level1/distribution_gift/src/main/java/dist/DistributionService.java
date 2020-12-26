package dist;


import entities_gift.Companie;
import entities_gift.Distribution;
import entities_gift.User;

import java.util.List;

public interface DistributionService {
    Distribution distributeGiftCards(Companie companie, User user, float amount);

    List<User> calculateUserBalance(List<Distribution> distList, List<User> userList);
}
