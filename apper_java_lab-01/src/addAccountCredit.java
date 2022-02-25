import java.util.Date;

public class addAccountCredit extends transaction {
    protected float msisdn;

    addAccountCredit(int store_id, int transaction_id, int account_id, Date timestamp, double amount, float msisdn) {
        super(store_id, transaction_id, account_id, timestamp, amount);
        this.msisdn = msisdn;
    }

    public String toString() {
        String value = this.store_id + " " + this.transaction_id + " " + this.account_id + " " + this.timestamp + " " +
                this.amount + " " + this.msisdn;
        return value;
    }
}
