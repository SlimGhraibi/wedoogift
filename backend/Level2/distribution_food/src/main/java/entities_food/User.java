package entities_food;

import java.util.ArrayList;
import java.util.List;

public class User {
    private Long id;
    private List<Balance> balance = new ArrayList();

    public User(Long id, List<Balance> balance) {
        this.id = id;
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public List<Balance> getBalance() {
        return balance;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBalance(List<Balance> balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", balance=" + balance +
                '}';
    }
}
