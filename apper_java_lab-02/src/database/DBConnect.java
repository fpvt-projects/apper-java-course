package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnect {
    final private static Logger logger = Logger.getLogger(DBConnect.class.getName());
    public static Connection conn = null;

    public static void main(String[] args) {
        //CODE-GOES-HERE
    }

    //CONNECTING TO DATABASE
    public static void connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/sms_db?useTimezone=true&serverTimezone=UTC", "root", "Adm1n@password"
            );
            logger.info("Connected\n");
        } catch (Exception e){
            logger.log(Level.SEVERE, "Not Connected", e);
        }
    }

    //DISCONNECTING TO DATABASE
    public static void disconnect() {
        try {
            if(conn != null) {
                conn.close();
                logger.info("Disconnected\n");
            }
        } catch (Exception e) {
            logger.log(Level.SEVERE, "Not Connected", e);
        }
    }
}
