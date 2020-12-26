package distImpl;
import dist.DistributionService;
import entities.*;
import utils.Utils;
import java.util.ArrayList;
import java.util.List;

public class DistributionServiceImp implements DistributionService {

    @Override
    public Distribution distributefoodCards(Companie companie, User user, Wallet wallet, float amount) {
        if (Utils.checkCompanieBalance(companie, amount)) {
            companie.setBalance(companie.getBalance() - amount);
            return Utils.getDistribution(user, companie, wallet, amount);
        }
        return null;
    }

    @Override
    public void calculateUserBalance(List<Distribution> distList, List<User> userList) {
        User user;
        for (Distribution dist : distList) {
            user = Utils.getUserByID(userList, dist.getUser_id());
            if (Utils.checkWalletId(user.getBalance(), dist.getWallet_id())) {
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
    }
}
