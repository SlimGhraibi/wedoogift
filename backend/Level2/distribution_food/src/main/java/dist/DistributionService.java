package dist;
import entities.Companie;
import entities.Distribution;
import entities.User;
import entities.Wallet;

public interface DistributionService {
    public Distribution distributefoodCards(Companie companie, User user, Wallet wallet, float amount);
}
