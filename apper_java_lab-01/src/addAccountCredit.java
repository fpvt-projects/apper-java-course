import java.util.Date;

public class addAccountCredit extends transaction {
    protected float msisdn;

    addAccountCredit(int store_id, int transaction_id, int account_id, Date timestamp, double amount, float msisdn) {
        super(store_id, transaction_id, account_id, timestamp, amount);
        this.msisdn = msisdn;
    }

    public String toString() {
        String value = "STORE_ID: " + this.store_id +
                "\n" + "TRANSACTION_ID: " + this.transaction_id +
                "\n" + "ACCOUNT_ID: " + this.account_id +
                "\n" + "TIMESTAMP: " + this.timestamp +
                "\n" + "AMOUNT: " + this.amount +
                "\n" + "MSISDN: " +  this.msisdn;
        return value;
    }
}
