package dist;


import org.json.JSONObject;

public interface Distribution {
    public void distributeGiftCards(JSONObject object);

    public void calculateUserBalance(JSONObject object);
}
