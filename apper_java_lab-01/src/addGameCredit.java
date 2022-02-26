import java.util.Date;

public class addGameCredit extends transaction{
    protected String company_name;

    addGameCredit(int store_id, int transaction_id, int account_id, Date timestamp, double amount, String company_name) {
        super(store_id, transaction_id, account_id, timestamp, amount);
        this.company_name = company_name;
    }
    public String toString() {
        String value = "STORE_ID: " + this.store_id +
                "\n" + "TRANSACTION_ID: " + this.transaction_id +
                "\n" + "ACCOUNT_ID: " + this.account_id +
                "\n" + "TIMESTAMP: " + this.timestamp +
                "\n" + "AMOUNT: " + this.amount +
                "\n" + "COMPANY_NAME: " + this.company_name;
        return value;
    }
}
