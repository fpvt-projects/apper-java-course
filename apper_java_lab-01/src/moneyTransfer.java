import java.time.LocalDateTime;
import java.util.Date;

public class moneyTransfer extends transaction{
    protected String recipient;

    moneyTransfer(int store_id, int transaction_id, int account_id, Date timestamp, double amount, String recipient) {
        super(store_id, transaction_id, account_id, timestamp, amount);
        this.recipient = recipient;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    @Override
    public String toString(){
        String value = "STORE_ID: " + this.store_id +
                "\n" + "TRANSACTION_ID: " + this.transaction_id +
                "\n" + "ACCOUNT_ID: " + this.account_id +
                "\n" + "TIMESTAMP: " + this.timestamp +
                "\n" + "AMOUNT: " + this.amount +
                "\n" + "RECIPIENT: " + this.recipient;
        return value;
    }
}
