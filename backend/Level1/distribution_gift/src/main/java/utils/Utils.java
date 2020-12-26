package utils;

import entities.Companie;
import entities.Distribution;
import entities.User;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Utils {

    static Long idDistribution = 1L;

    public static List<User> getUsers(JSONObject object) {
        List<User> userList = new ArrayList();
        object.getJSONArray("users").forEach(user -> {
            userList.add(mapToUser((JSONObject) user));
        });
        return userList;
    }

    public static List<Companie> getCompany(JSONObject object) {
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

    public static User getUserByID(List<User> list, Long id) {
        return list.stream().filter(user -> user.getId() == id).findFirst().get();
    }

    public static Distribution getDistribution(User user, Companie companie, float amount) {
        Distribution distribution = null;
        Date today = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(today);
        c.add(Calendar.DAY_OF_MONTH, 364);
        distribution = new Distribution(idDistribution++, amount, today, c.getTime(), companie.getId(), user.getId());
        return distribution;
    }

    public static Boolean checkCompanieBalance(Companie company, float amount) {
        return company.getBalance() >= amount;
    }
}
