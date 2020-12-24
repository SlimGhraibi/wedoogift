package utils;

import entities.Companie;
import entities.User;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static List<User> getUsers(JSONObject object) {
        List<User> userList = new ArrayList();
        object.getJSONArray("users").forEach(user -> {
            userList.add(mapToUser((JSONObject) user));
        });
        return userList;
    }

    public static  List<Companie> getCompany(JSONObject object) {
        List<Companie> companyList = new ArrayList();
        object.getJSONArray("companies").forEach(company -> {
            companyList.add(mapToCompany((JSONObject) company));
        });
        return companyList;
    }


    private static User mapToUser(JSONObject user) {
        User us = new User(user.getLong("id"), user.getFloat("balance"));
        return us;
    }

    private static Companie mapToCompany(JSONObject companie) {
        Companie cp = new Companie(companie.getLong("id"), companie.getFloat("balance"), companie.getString("name"));
        return cp;
    }
}
