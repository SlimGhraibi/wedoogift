package entities_gift;

public class Companie {
    private Long id;
    private float balance;
    private String name;

    public Companie(Long id, float balance, String name) {
        this.id = id;
        this.balance = balance;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public float getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Companie{" +
                "id=" + id +
                ", balance=" + balance +
                ", name='" + name + '\'' +
                '}';
    }
}
