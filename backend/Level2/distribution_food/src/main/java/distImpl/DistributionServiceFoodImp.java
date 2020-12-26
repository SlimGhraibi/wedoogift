package distImpl;
import dist.DistributionServiceFood;
import entities.*;
import utils.UtilsFood;
import java.util.ArrayList;
import java.util.List;

public class DistributionServiceFoodImp implements DistributionServiceFood {

    @Override
    public Distribution distributefoodCards(Companie companie, User user, Wallet wallet, float amount) {
        if (UtilsFood.checkCompanieBalance(companie, amount)) {
            companie.setBalance(companie.getBalance() - amount);
            return UtilsFood.getDistribution(user, companie, wallet, amount);
        }
        return null;
    }

    @Override
    public List<User> calculateUserBalance(List<Distribution> distList, List<User> userList) {
        User user;
        for (Distribution dist : distList) {
            user = UtilsFood.getUserByID(userList, dist.getUser_id());
            if (UtilsFood.checkWalletId(user.getBalance(), dist.getWallet_id())) {
                user.getBalance().forEach(balance -> {
                    if (balance.getWallet_id() == dist.getWallet_id()) {
                        balance.setAmount(balance.getAmount() + dist.getAmount());
                    }
                });
            } else {
                List<Balance> b = new ArrayList<>();
                b.add(new Balance(dist.getWallet_id(), dist.getAmount()));
                user.getBalance().addAll(b);
            }
        }
        return userList;
    }
}
