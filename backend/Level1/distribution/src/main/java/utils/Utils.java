package utils;

import entities.Companie;
import entities.Distribution;
import entities.User;
import org.json.JSONObject;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Utils {
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

    public static List<Distribution> getDistribution(JSONObject object) {
        List<Distribution> distributionsList = new ArrayList();
        object.getJSONArray("distributions").forEach(dist -> {
            try {
                distributionsList.add(mapToDist((JSONObject) dist));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        });
        return distributionsList;
    }


    private static User mapToUser(JSONObject user) {
        User us = new User(user.getLong("id"), user.getFloat("balance"));
        return us;
    }

    private static Companie mapToCompany(JSONObject companie) {
        Companie cp = new Companie(companie.getLong("id"), companie.getFloat("balance"), companie.getString("name"));
        return cp;
    }

    private static Distribution mapToDist(JSONObject distribution) throws ParseException {
        Date sDate = new SimpleDateFormat("yy-MM-dd").parse(distribution.getString("start_date"));
        Date eDate = new SimpleDateFormat("yy-MM-dd").parse(distribution.getString("end_date"));
        Distribution ds =
                new Distribution(distribution.getLong("id"), distribution.getFloat("amount"), sDate, eDate, distribution.getLong("company_id"), distribution.getLong("user_id"));
        return ds;
    }

    public static User getUserByID(List<User> list, Long id) {
        return list.stream().filter(user -> user.getId() == id).findFirst().get();
    }
}
