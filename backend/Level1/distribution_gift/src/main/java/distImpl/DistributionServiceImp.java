package distImpl;

import dist.DistributionService;
import entities.Companie;
import entities.Distribution;
import entities.User;
import utils.Utils;
import java.util.List;


public class DistributionServiceImp implements DistributionService {

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
