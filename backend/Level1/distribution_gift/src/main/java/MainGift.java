import com.fasterxml.jackson.databind.ObjectMapper;
import distImpl.DistributionServiceImp;
import entities.Companie;
import entities.Distribution;
import entities.User;
import org.json.JSONObject;
import org.json.JSONTokener;
import utils.Utils;

import java.io.IOException;
import java.io.InputStream;

import java.net.URISyntaxException;

import java.nio.file.Paths;
import java.util.*;

public class MainGift {

    public static void main(String[] args) throws IOException, URISyntaxException {
        String input = "data/inputGift.json";
        String output = "output.json";
        InputStream is = MainGift.class.getResourceAsStream(input);

        if (is == null) {
            throw new NullPointerException("Cannot find resource file " + input);
        }

        JSONTokener tokener = new JSONTokener(is);
        JSONObject object = new JSONObject(tokener);
        DistributionServiceImp distributionImp = new DistributionServiceImp();

        List<User> userList = Utils.getUsers(object);
        List<Companie> companieList = Utils.getCompany(object);
        List<Distribution> distributions = new ArrayList<>();

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

        distributionImp.calculateUserBalance(distributions, userList);

        try {
            Map<String, Object> map = new HashMap<>();
            map.put("distributions", distributions);
            map.put("companies", companieList);
            map.put("users", userList);

            ObjectMapper mapper = new ObjectMapper();

            // affichage dans un fichier output
            mapper.writeValue(Paths.get(output).toFile(), map);


        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
