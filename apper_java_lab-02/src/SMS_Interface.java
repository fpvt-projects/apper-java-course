import java.sql.SQLException;

public interface SMS_Interface {
    void insertSMS(SMS_Model data) throws SQLException;
    void retrieveSMS_Date();
    void retrieveSMS_Code();
    void retrieveSMS_MSISDN();
    void retrieveSMS_multipleMSISDN();
}
