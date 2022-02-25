import java.time.LocalDateTime;

public class SMS_Model {
    protected String msisdn;
    protected String promo;
    protected String recipient;
    protected String sender;
    protected int short_code;
    protected String status;
    protected LocalDateTime timestamp;
    //protected String transaction_id; //-----------AUTO GENERATED IN DB

    public SMS_Model (String msisdn, String promo, String recipient, String sender, int short_code, String status,LocalDateTime timestamp) {
        this.msisdn = msisdn;
        this.promo = promo;
        this.recipient = recipient;
        this.sender = sender;
        this.short_code = short_code;
        this.status = status;
        //this.transaction_id = transaction_id; //-----------AUTO GENERATED IN DB
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsisdn() {
        return msisdn;
    }

    public void setMsisdn(String msisdn) {
        this.msisdn = msisdn;
    }

    public String getPromo() {
        return promo;
    }

    public void setPromo(String promo) {
        this.promo = promo;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public int getShort_code() {
        return short_code;
    }

    public void setShort_code(int short_code) {
        this.short_code = short_code;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}
