import com.fasterxml.jackson.databind.ObjectMapper;
import distImpl.DistributionServiceImp;
import entities.Companie;
import entities.Distribution;
import entities.User;
import org.json.JSONObject;
import org.json.JSONTokener;
import utils.Utils;

import java.io.InputStream;

import java.nio.file.Paths;
import java.util.*;

public class MainGift {
    List<User> userList;
    List<Companie> companieList;
    List<Distribution> distributions;
    DistributionServiceImp distributionImp;
    String output;

    public void init() {
        String input = "data/inputGift.json";
        output = "outputGift.json";
        InputStream is = MainGift.class.getResourceAsStream(input);
        if (is == null) {
            throw new NullPointerException("Cannot find resource file " + input);
        }
        JSONTokener tokener = new JSONTokener(is);
        JSONObject object = new JSONObject(tokener);
        userList = Utils.getUsers(object);
        companieList = Utils.getCompany(object);
        distributions = new ArrayList<>();
    }

    public void createDistribution() {
        distributionImp = new DistributionServiceImp();
        // Création des distributions d'aprés l'input
        userList.forEach(user -> {
            Distribution dist;
            if (user.getId() == 1) {
                dist = distributionImp.distributeGiftCards(companieList.get(0), userList.get(0), 50);
                distributions.add(dist);
            }
            if (user.getId() == 2) {
                dist = distributionImp.distributeGiftCards(companieList.get(0), userList.get(1), 100);
                distributions.add(dist);
            }
            if (user.getId() == 3) {
                dist = distributionImp.distributeGiftCards(companieList.get(1), userList.get(2), 1000);
                distributions.add(dist);
            }
        });
    }

    public void calculateUserBalance() {
        userList = distributionImp.calculateUserBalance(distributions, userList);
    }

    public Map<String, Object> outputResult() {
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
        MainGift mainGift = new MainGift();
        mainGift.outputResult();
    }
}
