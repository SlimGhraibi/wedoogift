package utils;

import entities.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class UtilsFood {
    static Long idDistribution = 1L;
    public static List<Companie> getCompanies(JSONObject object) {
        List<Companie> companyList = new ArrayList();
        object.getJSONArray("companies").forEach(company -> {
            companyList.add(mapToCompany((JSONObject) company));
        });
        return companyList;
    }

    public static List<Wallet> getWallets(JSONObject object) {
        List<Wallet> walletList = new ArrayList();
        object.getJSONArray("wallets").forEach(wallet -> {
            walletList.add(mapToWallet((JSONObject) wallet));
        });
        return walletList;
    }

    public static List<User> getUsers(JSONObject object) {
        List<User> userList = new ArrayList();
        object.getJSONArray("users").forEach(user -> {
            userList.add(mapToUser((JSONObject) user));
        });
        return userList;
    }

    private static Companie mapToCompany(JSONObject company) {
        Companie cp = new Companie(company.getLong("id"), company.getFloat("balance"), company.getString("name"));
        return cp;
    }

    private static Wallet mapToWallet(JSONObject wallet) {
        Wallet wl = new Wallet(wallet.getLong("id"), wallet.getString("name"), wallet.getString("type"));
        return wl;
    }

    private static User mapToUser(JSONObject user) {
        List<Balance> balanceList = new ArrayList<>();
        JSONArray j = user.getJSONArray("balance");
        j.forEach(o -> {
            JSONObject balance = (JSONObject) o;
            Balance b = new Balance(balance.getLong("wallet_id"), balance.getFloat("amount"));
            balanceList.add(b);
        });
        User us = new User(user.getLong("id"), balanceList);
        return us;
    }

    public static User getUserByID(List<User> list, Long id) {
        return list.stream().filter(user -> user.getId() == id).findFirst().get();
    }

    public static Distribution getDistribution(User user, Companie companie, Wallet wallet, float amount) {
        Distribution distribution = null;
        Date today = new Date();
        if (wallet.getType().equals("GIFT")) {
            Calendar c = Calendar.getInstance();
            c.setTime(today);
            c.add(Calendar.DAY_OF_MONTH, 364);
            distribution = new Distribution(idDistribution++, amount, today, c.getTime(), companie.getId(), user.getId(), wallet.getId());
        } else {
            Calendar c = Calendar.getInstance();
            c.setTime(today);
            c.add(Calendar.MONTH, 2);
            c.add(Calendar.DAY_OF_MONTH, 2);
            distribution = new Distribution(idDistribution++, amount, today, c.getTime(), companie.getId(), user.getId(), wallet.getId());
        }
        return distribution;
    }

    public static Boolean checkCompanieBalance(Companie company, float amount) {
        return company.getBalance() >= amount;
    }

    public static Boolean checkWalletId(List<Balance> balances, Long walletId) {
        return balances.stream()
                .anyMatch(t -> t.getWallet_id().equals(walletId));

    }
}
