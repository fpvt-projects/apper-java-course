import java.time.LocalDateTime;
import java.util.Date;

public class moneyTransfer extends transaction{
    protected String recipient;

    moneyTransfer(int store_id, int transaction_id, int account_id, Date timestamp, double amount, String recipient) {
        super(store_id, transaction_id, account_id, timestamp, amount);
        this.recipient = recipient;
    }

    @Override
    public String toString(){
        String value = this.store_id + " " + this.transaction_id + " " + this.account_id + " " + this.timestamp + " " +
                this.amount + " " + this.recipient;
        return value;
    }
}
