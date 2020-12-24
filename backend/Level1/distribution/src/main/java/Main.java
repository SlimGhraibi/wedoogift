import distImpl.DistributionServiceImp;
import entities.Companie;
import entities.Distribution;
import entities.User;
import org.json.JSONObject;
import org.json.JSONTokener;
import utils.Utils;

import java.io.InputStream;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String input = "data/input.json";
        InputStream is = Main.class.getResourceAsStream(input);
        if (is == null) {
            throw new NullPointerException("Cannot find resource file " + input);
        }
        JSONTokener tokener = new JSONTokener(is);
        JSONObject object = new JSONObject(tokener);
        DistributionServiceImp distributionImp = new DistributionServiceImp();
        List<User> userList = Utils.getUsers(object);
        List<Companie> companieList = Utils.getCompany(object);
        Distribution dist = distributionImp.distributeGiftCards(companieList.get(0), userList.get(0), 50);
    }
}
