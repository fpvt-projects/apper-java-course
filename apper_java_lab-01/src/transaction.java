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
}
