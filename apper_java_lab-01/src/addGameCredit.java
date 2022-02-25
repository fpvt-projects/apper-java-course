import java.util.Date;

public class addGameCredit extends transaction{
    protected String company_name;

    addGameCredit(int store_id, int transaction_id, int account_id, Date timestamp, double amount, String company_name) {
        super(store_id, transaction_id, account_id, timestamp, amount);
        this.company_name = company_name;
    }
    public String toString() {
        String value = this.store_id + " " + this.transaction_id + " " + this.account_id + " " + this.timestamp + " " +
                this.amount + " " + this.company_name;
        return value;
    }
}
