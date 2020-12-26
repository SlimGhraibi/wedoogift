
import distImpl.DistributionServiceImp;
import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.InputStream;

import java.net.URISyntaxException;

public class MainFood {

    public static void main(String[] args) throws IOException, URISyntaxException {
        String input = "data/input.json";
        String output = "output.json";
        InputStream is = MainFood.class.getResourceAsStream(input);

        if (is == null) {
            throw new NullPointerException("Cannot find resource file " + input);
        }

        JSONTokener tokener = new JSONTokener(is);
        JSONObject object = new JSONObject(tokener);
        DistributionServiceImp distributionImp = new DistributionServiceImp();
    }
}
