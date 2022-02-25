import database.DBConnect;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;

public class SMS implements SMS_Interface {

    @Override
    public void insertSMS(SMS_Model data) throws SQLException {
        Statement st  = DBConnect.conn.createStatement();
        st.executeUpdate("INSERT INTO sms_db.sms(msisdn, promo, shortcode, status, recipient, sender, timestamp)" +
                "VALUES ('" + data.getMsisdn() +
                "','" + data.getPromo() +
                "','" + data.getShort_code() +
                "','" + data.getStatus() +
                "','" + data.getRecipient() +
                "','" + data.getSender() +
                "','" + data.getTimestamp() + "')");
    }

    @Override
    public void retrieveSMS_Date() {

    }

    @Override
    public void retrieveSMS_Code() {

    }

    @Override
    public void retrieveSMS_MSISDN() {

    }

    @Override
    public void retrieveSMS_multipleMSISDN() {

    }
}
