import database.DBConnect;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.Random;

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
        System.out.println("[1] - EXIT"); //WORKING
        System.out.println("[2] - SEND SMS"); //WORKING
        System.out.println("[3] - RETRIEVE SMS (START & END DATE) -- NOT WORKING");
        System.out.println("[4] - RETRIEVE SMS (PROMO REGISTERED)"); //WORKING
        System.out.println("[5] - RETRIEVE SMS (SINGLE MSISDN)"); //WORKING
        System.out.println("[6] - RETRIEVE SMS (MULTIPLE MSISDN) -- NOT WORKING");
        System.out.println("[7] - AVAILABLE PROMOS"); //WORKING
        System.out.println("[8] - POPULATE PROMO -- IN PROGRESS");
        System.out.println("[9] - POPULATE SMS -- DID NOT UNDER-GO PROPER VALIDATION"); //WORKING
        System.out.println("[10] - FAILED SMS"); //WORKING
        System.out.println("[11] - SUCCESS SMS"); //WORKING
        System.out.println("=========================================");

        getUserInput();
    }

    static void getUserInput() throws SQLException {
        int input = scanner.nextInt();

        switch (input) {
            case 1 -> logger.info("TERMINATE-PROGRAM");
            case 2 -> sendSMS();
            case 3 -> System.out.println("Switch_3");
            case 4 -> {
                SMS newAction_promo = new SMS();
                newAction_promo.retrieveSMS_Code();
            }
            case 5 -> {
                SMS newAction_msisdn = new SMS();
                newAction_msisdn.retrieveSMS_MSISDN();
            }
            case 6 -> System.out.println("NOT-YET-WORKING");
            case 7 -> retrievePromo();
            case 8 -> insertPromo();
            case 9 -> insertAdditionalSMS();
            case 10 -> retrieveFailed_SMS();
            case 11 -> retrieveSuccess_SMS();
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
                String lname = scanner.next();
                System.out.print("SHORTCODE: ");
                String fcode = scanner.next();

                int shortToString = Integer.parseInt(inputSHORTCODE);
                SMS_Model sms = new SMS_Model(inputMSISDN, inputMESSAGE, "SOME-RECIPIENT",
                        fname, shortToString, dateValidation(inputMESSAGE), dateTime);
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
            if(checkPromo_ShortCode(inputSMS.get("message"), inputSMS.get("shortcode"))){
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
    static boolean checkPromo_ShortCode(String promo, String code) throws SQLException {
        String query = "SELECT * FROM sms_db.promo WHERE promo_code = '" +promo+ "' AND shortcode = '" +code+ "'" ;
        Statement st = DBConnect.conn.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            String promo_code = rs.getString("promo_code");
            String short_code = rs.getString("shortcode");
            if (promo.equals(promo_code) && code.equals(short_code)) {
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

    //ADDITIONAL-SMS-USERS
    static void insertAdditionalSMS() throws SQLException {
        DBConnect.connect();
        Random random = new Random();
        int counter = 0;
        while(counter < 30) {
            int randomizer = 5;
            int int_randomizer = random.nextInt(randomizer);
            String[] randomMSISDN = {"+639175589110", "+639175589111", "+639175589112", "+639175589113", "+639175589115", "+639175589115"};
            String[] randomPromo = {"JUICE-BOX", "FEB-FOOD", "PISO-PIZZA", "DEV-PROMO", "ALIVE-WELL"};
            int[] randomShortCode = {1550 , 1235, 1551, 1660, 1420};
            SMS_Model sms = new SMS_Model(randomMSISDN[int_randomizer], randomPromo[int_randomizer],
                    "SOME-RECIPIENT", "SOME-SENDER", randomShortCode[int_randomizer],
                    dateValidation(randomPromo[int_randomizer]), dateTime);
            SMS data = new SMS();
            data.insertSMS(sms);
            counter++;
        }
        logger.warning("[POPULATED-SMS]\n");
        DBConnect.disconnect();
    }

    //RETRIEVE-SUCCESSFUL-SMS
    static void retrieveSuccess_SMS() throws SQLException {
        DBConnect.connect();
        String query = "SELECT * FROM sms_db.sms WHERE status = 'SUCCESS'";
        Statement st = DBConnect.conn.createStatement();
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

    //RETRIEVE-FAILED-SMS
    static void retrieveFailed_SMS() throws SQLException {
        DBConnect.connect();
        String query = "SELECT * FROM sms_db.sms WHERE status = 'FAILED'";
        Statement st = DBConnect.conn.createStatement();
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
