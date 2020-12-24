import distImpl.DistributionImp;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.InputStream;

public class Main {

    public static void main(String[] args) {
        String input = "data/input.json";
        InputStream is = Main.class.getResourceAsStream(input);
        if (is == null) {
            throw new NullPointerException("Cannot find resource file " + input);
        }
        JSONTokener tokener = new JSONTokener(is);
        JSONObject object = new JSONObject(tokener);
        DistributionImp distributionImp = new DistributionImp();
        distributionImp.distributeGiftCards(object);
    }
}
