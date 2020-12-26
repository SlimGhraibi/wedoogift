package entities_food;

public class Balance {
    private Long wallet_id;
    private float amount;

    public Balance(Long wallet_id, float amount) {
        this.wallet_id = wallet_id;
        this.amount = amount;
    }

    public Long getWallet_id() {
        return wallet_id;
    }

    public float getAmount() {
        return amount;
    }

    public void setWallet_id(Long wallet_id) {
        this.wallet_id = wallet_id;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Balance{" +
                "wallet_id=" + wallet_id +
                ", amount=" + amount +
                '}';
    }
}
