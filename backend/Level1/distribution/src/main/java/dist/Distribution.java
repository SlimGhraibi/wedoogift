package dist;


import entities.User;
import org.json.JSONObject;

import java.util.List;

public interface Distribution {
    public List<User> distributeGiftCards(JSONObject object);

    public void calculateUserBalance(JSONObject object);
}
