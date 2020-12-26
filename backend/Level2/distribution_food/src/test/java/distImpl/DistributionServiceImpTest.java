package distImpl;

import entities_food.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DistributionServiceImpTest {
    DistributionServiceFoodImp distributionServiceImp;
    User user;
    Balance balance;
    Companie companie;
    Wallet wallet;
    Distribution distribution;

    @BeforeEach
    void setUp() {
        List<Balance> balances = new ArrayList<>();
        balance = new Balance(1L, 100);
        balances.add(balance);
        user = new User(1L, balances);
        companie = new Companie(1L, 1000, "Wedoogift");
        wallet = new Wallet(1L, "gift cards", "GIFT");
        distributionServiceImp = new DistributionServiceFoodImp();
    }

    @Test
    void distributefoodCards() {
        distribution = distributionServiceImp.distributefoodCards(companie, user, wallet, 50);
        assertThat(distribution.getAmount()).isEqualTo(50);
        assertThat(companie.getBalance()).isEqualTo(950);
    }

    @Test
    void calculateUserBalance() {
        distribution = distributionServiceImp.distributefoodCards(companie, user, wallet, 50);
        List<User> userList = new ArrayList<>();
        List<Distribution> distributions = new ArrayList<>();
        userList.add(user);
        distributions.add(distribution);
        distributionServiceImp.calculateUserBalance(distributions, userList);
        assertThat(userList.get(0).getBalance().get(0).getAmount()).isEqualTo(150);
    }
}
