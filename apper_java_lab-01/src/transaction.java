import java.time.LocalDateTime;
import java.util.*;

public class transaction {
    protected int store_id, transaction_id, account_id;
    protected Date timestamp;
    protected double amount;

    transaction(int store_id, int transaction_id, int account_id, Date timestamp, double amount){
        this.store_id = store_id;
        this.transaction_id = transaction_id;
        this.account_id = account_id;
        this.timestamp = timestamp;
        this.amount = amount;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public int getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(int transaction_id) {
        this.transaction_id = transaction_id;
    }

    public int getAccount_id() {
        return account_id;
    }

    public void setAccount_id(int account_id) {
        this.account_id = account_id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
