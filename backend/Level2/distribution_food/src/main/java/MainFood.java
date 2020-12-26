
import distImpl.DistributionServiceImp;
import entities.Companie;
import entities.Distribution;
import entities.User;
import entities.Wallet;
import org.json.JSONObject;
import org.json.JSONTokener;
import utils.Utils;

import java.io.IOException;
import java.io.InputStream;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class MainFood {

    public static void main(String[] args) throws IOException, URISyntaxException {
        String input = "data/inputFood.json";
        String output = "output.json";
        InputStream is = MainFood.class.getResourceAsStream(input);

        if (is == null) {
            throw new NullPointerException("Cannot find resource file " + input);
        }

        JSONTokener tokener = new JSONTokener(is);
        JSONObject object = new JSONObject(tokener);
        DistributionServiceImp distributionImp = new DistributionServiceImp();

        List<User> userList = Utils.getUsers(object);
        List<Companie> companieList = Utils.getCompanies(object);
        List<Wallet> walletList = Utils.getWallets(object);
        List<Distribution> distributions = new ArrayList<>();

    }
}
