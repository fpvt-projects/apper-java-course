import java.util.Date;

public class bills extends transaction{
    protected String company_name;
    protected double bills_charge;

    bills(int store_id, int transaction_id, int account_id, Date timestamp, double amount, String company_name,
          double bills_charge) {
        super(store_id, transaction_id, account_id, timestamp, amount);
        this.company_name = company_name;
        this.bills_charge = bills_charge;
    }

    public String toString() {
        String value = "STORE_ID: " + this.store_id +
                "\n" + "TRANSACTION_ID: " +  this.transaction_id +
                "\n" + "ACCOUNT_ID: " + this.account_id +
                "\n" + "TIMESTAMP_ID: " + this.timestamp +
                "\n" + "AMOUNT_ID: " + this.amount +
                "\n" + "COMPANY_NAME: " + this.company_name +
                "\n" + "BILLS_CHARGE: " + this.bills_charge;
        return value;
    }
}
