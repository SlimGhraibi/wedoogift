import distImpl.DistributionServiceImp;
import entities.Companie;
import entities.Distribution;
import entities.User;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.json.simple.JSONArray;
import utils.Utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        String input = "data/input.json";
        String output = "data/output.json";

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

        List<Distribution> distributionList = new ArrayList<>();
        distributionList.add(dist);
        JSONObject o = new JSONObject();
        o.put("distributions", JSONArray.toJSONString(distributionList));
         /*  FileWriter out = new FileWriter(output);
        out.write(o.toString());
        out.flush(); */

        System.out.println(o.toString());
    }
}
