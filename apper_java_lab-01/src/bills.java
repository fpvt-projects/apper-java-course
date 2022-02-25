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
        String value = this.store_id + " " + this.transaction_id + " " + this.account_id + " " + this.timestamp + " " +
                this.amount + " " + this.company_name + " " + this.bills_charge;
        return value;
    }
}
