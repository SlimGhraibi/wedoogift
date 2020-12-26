package dist;


import entities.Companie;
import entities.Distribution;
import entities.User;
import org.json.JSONObject;

import java.util.List;

public interface DistributionService {
    public Distribution distributefoodCards(Companie companie, User user, float amount);
}
