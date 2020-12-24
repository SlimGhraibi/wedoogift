package distImpl;
import dist.Distribution;
import org.json.JSONArray;
import org.json.JSONObject;


public class DistributionImp implements Distribution {

    @Override
    public void distributeGiftCards(JSONObject object) {
        JSONArray companies = object.getJSONArray("companies");
        object.getJSONArray("users").forEach(el -> {
        });
    }

    @Override
    public void calculateUserBalance(JSONObject object) {

    }
}
