package distImpl;
import dist.Distribution;
import entities.Companie;
import entities.User;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class DistributionImp implements Distribution {

    @Override
    public List<User> distributeGiftCards(JSONObject object) {
        List<User> userList = getUsers(object);
        List<Companie> companieList = getCompany(object);
        return userList;
    }

    private List<User> getUsers(JSONObject object) {
        List<User> userList = new ArrayList();
        object.getJSONArray("users").forEach(user -> {
            userList.add(mapToUser((JSONObject) user));
        });
        return userList;
    }

    private List<Companie> getCompany(JSONObject object) {
        List<Companie> companyList = new ArrayList();
        object.getJSONArray("companies").forEach(company -> {
            companyList.add(mapToCompany((JSONObject) company));
        });
        return companyList;
    }

    @Override
    public void calculateUserBalance(JSONObject object) {

    }

    private User mapToUser(JSONObject user) {
        User us = new User(user.getLong("id"), user.getFloat("balance"));
        return us;
    }

    private Companie mapToCompany(JSONObject companie) {
        Companie cp = new Companie(companie.getLong("id"), companie.getFloat("balance"), companie.getString("name"));
        return cp;
    }

    private Distribution mapToDistribution(JSONObject distribution) {
        return null;
    }

}
