
import com.fasterxml.jackson.databind.ObjectMapper;
import distImpl.DistributionServiceFoodImp;
import entities.Companie;
import entities.Distribution;
import entities.User;
import entities.Wallet;
import org.json.JSONObject;
import org.json.JSONTokener;
import utils.UtilsFood;

import java.io.InputStream;

import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainFood {

    List<User> userList;
    List<Companie> companieList;
    List<Distribution> distributions;
    List<Wallet> walletList;
    DistributionServiceFoodImp distributionImp;
    String output;

    public void init() {
        String input = "data/inputFood.json";
        output = "outputFood.json";
        InputStream is = MainFood.class.getResourceAsStream(input);

        if (is == null) {
            throw new NullPointerException("Cannot find resource file " + input);
        }
        JSONTokener tokener = new JSONTokener(is);
        JSONObject object = new JSONObject(tokener);
        userList = UtilsFood.getUsers(object);
        companieList = UtilsFood.getCompanies(object);
        walletList = UtilsFood.getWallets(object);
        distributions = new ArrayList<>();
    }

    public void createDistribution() {
        distributionImp = new DistributionServiceFoodImp();
        // Création des distributions d'aprés l'input
        userList.forEach(user -> {
            Distribution dist;
            if (user.getId() == 1) {
                dist = distributionImp.distributefoodCards(companieList.get(0), userList.get(0), walletList.get(0), 50);
                distributions.add(dist);
                dist = distributionImp.distributefoodCards(companieList.get(0), userList.get(0), walletList.get(1), 250);
                distributions.add(dist);
            }
            if (user.getId() == 2) {
                dist = distributionImp.distributefoodCards(companieList.get(0), userList.get(1), walletList.get(0), 100);
                distributions.add(dist);
            }
            if (user.getId() == 3) {
                dist = distributionImp.distributefoodCards(companieList.get(1), userList.get(2), walletList.get(0), 1000);
                distributions.add(dist);
            }
        });
    }

    public void calculateUserBalance() {
        userList = distributionImp.calculateUserBalance(distributions, userList);
    }

    public Map<String, Object>  outputResult() {
        init();
        createDistribution();
        calculateUserBalance();
        try {
            Map<String, Object> map = new HashMap<>();
            map.put("distributions", distributions);
            map.put("companies", companieList);
            map.put("users", userList);

            ObjectMapper mapper = new ObjectMapper();
            // affichage dans un fichier output
            mapper.writeValue(Paths.get(output).toFile(), map);
            return map;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args) {
        MainFood mainGift = new MainFood();
        mainGift.outputResult();
    }
}
