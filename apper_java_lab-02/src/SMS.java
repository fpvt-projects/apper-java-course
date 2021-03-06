import database.DBConnect;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.logging.Logger;

public class SMS implements SMS_Interface {
    final private static Logger logger = Logger.getLogger(Main.class.getName());
    Scanner scanner = new Scanner(System.in);

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
    public void retrieveSMS_Date() throws SQLException {
        DBConnect.connect();
        String query = "SELECT * FROM sms_db.sms WHERE timestamp BETWEEN '2022-02-26 03:42:02' AND '2022-02-26 04:13:56';";
        Statement st  = DBConnect.conn.createStatement();
        ResultSet rs = st.executeQuery(query);

        while(rs.next()){
            String promo_code =  rs.getString("msisdn");
            String details = rs.getString("promo");
            int short1code = rs.getInt("shortcode");
            String status = rs.getString("status");
            String recipient = rs.getString("recipient");
            String sender = rs.getString("sender");
            String date = rs.getString("timestamp");

            logger.info("[SMS]" + "\n" +
                    "MSISDN: " + promo_code + "\n" +
                    "PROMO: " + details + "\n" +
                    "SHORTCODE: " + short1code + "\n" +
                    "STATUS: " + status + "\n" +
                    "RECIPIENT " + recipient + "\n" +
                    "SENDER: " + sender + "\n" +
                    "DATE: " + date + "\n");
        }
        DBConnect.disconnect();
    }

    @Override
    public void retrieveSMS_Code() throws SQLException {
        System.out.print("ENTER-PROMO: ");
        String promoInput = scanner.next();

        DBConnect.connect();
        String query =  "SELECT * FROM sms_db.sms WHERE promo = '" +promoInput+ "'";
        Statement st  = DBConnect.conn.createStatement();
        ResultSet rs = st.executeQuery(query);

        while(rs.next()){
            String promo_code =  rs.getString("msisdn");
            String details = rs.getString("promo");
            int short1code = rs.getInt("shortcode");
            String status = rs.getString("status");
            String recipient = rs.getString("recipient");
            String sender = rs.getString("sender");
            String date = rs.getString("timestamp");

            logger.info("[SMS]" + "\n" +
                    "MSISDN: " + promo_code + "\n" +
                    "PROMO: " + details + "\n" +
                    "SHORTCODE: " + short1code + "\n" +
                    "STATUS: " + status + "\n" +
                    "RECIPIENT " + recipient + "\n" +
                    "SENDER: " + sender + "\n" +
                    "DATE: " + date + "\n");
        }
        DBConnect.disconnect();

    }

    @Override
    public void retrieveSMS_MSISDN() throws SQLException {
        System.out.print("ENTER-MSISDN: ");
        String msisdnInput = scanner.next();

        DBConnect.connect();
        String query =  "SELECT * FROM sms_db.sms WHERE msisdn = '" +msisdnInput+ "'";
        Statement st  = DBConnect.conn.createStatement();
        ResultSet rs = st.executeQuery(query);

        while(rs.next()){
            String promo_code =  rs.getString("msisdn");
            String details = rs.getString("promo");
            int short1code = rs.getInt("shortcode");
            String status = rs.getString("status");
            String recipient = rs.getString("recipient");
            String sender = rs.getString("sender");
            String date = rs.getString("timestamp");

            logger.info("[SMS]" + "\n" +
                    "MSISDN: " + promo_code + "\n" +
                    "PROMO: " + details + "\n" +
                    "SHORTCODE: " + short1code + "\n" +
                    "STATUS: " + status + "\n" +
                    "RECIPIENT " + recipient + "\n" +
                    "SENDER: " + sender + "\n" +
                    "DATE: " + date + "\n");
        }
        DBConnect.disconnect();
    }

    @Override
    public void retrieveSMS_multipleMSISDN() throws SQLException {
        DBConnect.connect();
        String query =  "SELECT * FROM sms_db.sms WHERE msisdn ='+639175587870' OR msisdn='+639175589115'";
        Statement st  = DBConnect.conn.createStatement();
        ResultSet rs = st.executeQuery(query);

        while(rs.next()){
            String promo_code =  rs.getString("msisdn");
            String details = rs.getString("promo");
            int short1code = rs.getInt("shortcode");
            String status = rs.getString("status");
            String recipient = rs.getString("recipient");
            String sender = rs.getString("sender");
            String date = rs.getString("timestamp");

            logger.info("[SMS]" + "\n" +
                    "MSISDN: " + promo_code + "\n" +
                    "PROMO: " + details + "\n" +
                    "SHORTCODE: " + short1code + "\n" +
                    "STATUS: " + status + "\n" +
                    "RECIPIENT " + recipient + "\n" +
                    "SENDER: " + sender + "\n" +
                    "DATE: " + date + "\n");
        }
        DBConnect.disconnect();
    }
}
