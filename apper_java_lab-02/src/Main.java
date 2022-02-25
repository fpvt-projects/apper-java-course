import database.DBConnect;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    final private static Logger logger = Logger.getLogger(Main.class.getName());
    static LocalDateTime now = LocalDateTime.now();
    static DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    static String formatDateTime = now.format(format);
    static LocalDateTime dateTime = LocalDateTime.parse(formatDateTime, format);
    static Scanner scanner = new Scanner (System.in);
    static String query =  "SELECT * FROM sms_db.promo";

    //MAIN-METHOD
    public static void main(String[] args) throws SQLException {
        showMenu();
    }

    //MENU-Method:
    static void showMenu() throws SQLException {
        System.out.println("=========================================");
        System.out.println("[1] - EXIT");
        System.out.println("[2] - SEND SMS");
        System.out.println("[3] - RETRIEVE SMS (START & END DATE)");
        System.out.println("[4] - RETRIEVE SMS (PROMO REGISTERED)");
        System.out.println("[5] - RETRIEVE SMS (SINGLE MSISDN)");
        System.out.println("[6] - RETRIEVE SMS (MULTIPLE MSISDN)");
        System.out.println("[7] - AVAILABLE PROMOS");
        System.out.println("[8] - ADD PROMO");
        System.out.println("=========================================");

        getUserInput();
    }

    static void getUserInput() throws SQLException {
        int input = scanner.nextInt();

        switch (input) {
            case 1 -> logger.info("TERMINATE-PROGRAM");
            case 2 -> sendSMS();
            case 3 -> System.out.println("Switch_3");
            case 4 -> System.out.println("Switch_4");
            case 5 -> System.out.println("Switch_5");
            case 6 -> System.out.println("Switch_6");
            case 7 -> retrievePromo();
            case 8 -> insertPromo();
        }
    }

    static void sendSMS() throws SQLException {
        DBConnect.connect();
        System.out.print("ENTER-MSISDN: ");
        String inputMSISDN = scanner.next();
        System.out.print("ENTER-MESSAGE: ");
        String inputMESSAGE = scanner.next();
        System.out.print("ENTER-SHORTCODE: ");
        String inputSHORTCODE = scanner.next();

        dateValidation(inputMESSAGE);

        //MAPS-INPUT
        if(checkSMS(getInputSMS(inputMSISDN, inputMESSAGE,inputSHORTCODE))){
            System.out.print("\nTO-PROCEED-PLEASE-TEXT-(REGISTER): ");
            String registerMsg = scanner.next();
            if(registerMsg.equals("REGISTER")){
                System.out.print("ENTER-FIRST-NAME: ");
                String fname = scanner.next();
                System.out.print("ENTER-LAST-NAME: ");
                System.out.print("SHORTCODE: \n");

                int shortToString = Integer.parseInt(inputSHORTCODE);
                SMS_Model sms = new SMS_Model(inputMSISDN, inputMESSAGE, "SOME-RECIPIENT", fname, shortToString, dateValidation(inputMESSAGE), dateTime);
                SMS data = new SMS();
                data.insertSMS(sms);

                logger.info("[SMS-SENT] " + dateValidation(inputMESSAGE));
            } else {
                logger.warning("[INVALID-KEY-WORD]");
            }
        }
        //checkSMS;

    }

    //MAPS-INPUT-BY-THE-USER
    static Map<String, String> getInputSMS(String inputMSISDN, String inputMESSAGE, String inputSHORTCODE){
        Map<String, String> infoMap = new HashMap<>();
        infoMap.put("msisdn", inputMSISDN);
        infoMap.put("message", inputMESSAGE);
        infoMap.put("shortcode", inputSHORTCODE);
        return infoMap;
    }

    //VALIDATE-PROMO-DATE
    static String dateValidation(String givenMsg) throws SQLException {
        String query = "SELECT * FROM sms_db.promo WHERE promo_code = '" +givenMsg+ "'";
        Statement st = DBConnect.conn.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            String start_date = rs.getString("start_date");
            String end_date = rs.getString("end_date");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime givenStartDate =  LocalDateTime.parse(start_date, formatter);
            LocalDateTime givenEndDate =  LocalDateTime.parse(end_date, formatter);
            if (dateTime.isAfter(givenStartDate) && dateTime.isBefore(givenEndDate)) {
               return "SUCCESS";
            }
        }
        return "FAILED";
    }

    //VALIDATE-SENT-MESSAGE
    static boolean checkSMS(Map<String, String> inputSMS) throws SQLException {
        inputSMS.get("msisdn");
        inputSMS.get("message");
        inputSMS.get("shortcode");

        //RUNS-MSISDN-VALIDATION
        if(checkMSISDN(inputSMS.get("msisdn"))){
            //RUNS-PROMO-&-SHORTCODE-VALIDATION
            if(checkPromo_ShortCode(inputSMS.get("message"))){
                logger.info("[VALID-PROMO]\n");
                return true;
            } else {
                DBConnect.disconnect();
                logger.warning("[PROMO-NOT-VALID]\n");
                return false;
            }
        } else {
            DBConnect.disconnect();
            logger.warning("[MSISDN-NOT-VALID]\n");
            return false;
        }
    }

    //CHECKS-VALID-MSISDN
    static boolean checkMSISDN(String msisdn){
        return msisdn.length() == 13;
    }

    //CHECK-VALIDITY-OF-PROMO-AND-SHORT-CODE
    static boolean checkPromo_ShortCode(String promo) throws SQLException {
        Statement st = DBConnect.conn.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            String promo_code = rs.getString("promo_code");
            if (promo.equals(promo_code)) {
                return true;
            }
        }
        return false;
    }

    //RETRIEVES-PROMO-DATA-FROM-DB
    static void retrievePromo() throws SQLException {
        DBConnect.connect();
        Statement st  = DBConnect.conn.createStatement();
        ResultSet rs = st.executeQuery(query);

        while(rs.next())
        {
            String promo_code =  rs.getString("promo_code");
            String details = rs.getString("details");
            int short1code = rs.getInt("shortcode");
            String start_date = rs.getString("start_date");
            String end_date = rs.getString("end_date");

            logger.info("[PROMO]" + "\n" +
                    "PROMO-CODE: " + promo_code + "\n" +
                    "DETAILS: " + details + "\n" +
                    "SHORTCODE: " + short1code + "\n" +
                    "START-DATE: " + start_date + "\n" +
                    "END-DATE: " + end_date + "\n");
        }
        DBConnect.disconnect();
    }

    //ADD-ADDITIONAL-PROMO
    static void insertPromo(){

    }
}
