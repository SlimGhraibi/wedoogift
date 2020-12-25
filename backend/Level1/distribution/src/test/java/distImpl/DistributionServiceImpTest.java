package distImpl;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.fasterxml.jackson.databind.ObjectMapper;
import entities.Companie;
import entities.Distribution;
import entities.User;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utils.Utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class DistributionServiceImpTest {

    DistributionServiceImp distributionServiceImp;
    User user;
    Companie companie;
    Distribution distribution;

    @BeforeEach
    public void init() throws IOException {
        user = new User(1L, 100);
        companie = new Companie(1L, 1000, "Wedoogift");
        distributionServiceImp = new DistributionServiceImp();
    }

    @Test
    void distributeGiftCards() {
        distribution = distributionServiceImp.distributeGiftCards(companie, user, 50);
        assertThat(distribution.getAmount()).isEqualTo(50);
        assertThat(companie.getBalance()).isEqualTo(950);
    }

    @Test
    void calculateUserBalance() {
        distribution = distributionServiceImp.distributeGiftCards(companie, user, 50);
        List<User> userList = new ArrayList<>();
        List<Distribution> distributions = new ArrayList<>();
        userList.add(user);
        distributions.add(distribution);
        distributionServiceImp.calculateUserBalance(distributions, userList);
        assertThat(userList.get(0).getBalance()).isEqualTo(150);
    }
}
