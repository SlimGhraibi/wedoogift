package distImpl;

import dist.DistributionService;
import entities.Companie;
import entities.Distribution;
import entities.User;
import entities.Wallet;
import utils.Utils;

public class DistributionServiceImp implements DistributionService {

    @Override
    public Distribution distributefoodCards(Companie companie, User user, Wallet wallet, float amount) {
        if (Utils.checkCompanieBalance(companie, amount)) {
            companie.setBalance(companie.getBalance() - amount);
            return Utils.getDistribution(user, companie, wallet, amount);
        }
        return null;
    }
}
