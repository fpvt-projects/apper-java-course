import java.sql.SQLException;

public interface SMS_Interface {
    void insertSMS(SMS_Model data) throws SQLException;
    void retrieveSMS_Date() throws SQLException;
    void retrieveSMS_Code() throws SQLException;
    void retrieveSMS_MSISDN() throws SQLException;
    void retrieveSMS_multipleMSISDN();
}
