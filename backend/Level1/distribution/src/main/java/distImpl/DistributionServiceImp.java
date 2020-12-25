package distImpl;

import dist.DistributionService;
import entities.Companie;
import entities.Distribution;
import entities.User;
import utils.Utils;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class DistributionServiceImp implements DistributionService {

    static Long idDistribution = 1L;

    @Override
    public Distribution distributeGiftCards(Companie companie, User user, float amount) {
        if (checkCompanieBalance(companie, amount)) {
            companie.setBalance(companie.getBalance() - amount);
            return getDistribution(user, companie, amount);
        }
        return null;
    }

    @Override
    public void calculateUserBalance(List<Distribution> distList, List<User> userList) {
        User user;
        for (Distribution dist : distList) {
            user = Utils.getUserByID(userList, dist.getUser_id());
            user.setBalance(dist.getAmount() + user.getBalance());
        }
    }

    private Distribution getDistribution(User user, Companie companie, float amount) {
        Distribution distribution = null;
        Date today = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.add(Calendar.DAY_OF_MONTH, 364);
        distribution = new Distribution(idDistribution++, amount, today, c.getTime(), companie.getId(), user.getId());
        return distribution;
    }

    private Boolean checkCompanieBalance(Companie company, float amount) {
        return company.getBalance() >= amount;
    }

}
