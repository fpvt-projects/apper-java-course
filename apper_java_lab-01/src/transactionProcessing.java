import java.util.*;
import java.util.logging.*;
import java.text.*;

public class transactionProcessing {
    final private static Logger logger = Logger.getLogger(transactionProcessing.class.getName());
    private static ArrayList<transaction> transactions = new ArrayList<>();

    //Type of Transaction to throw in showTransactionType method
    static transactionTypes moneyTransaction = transactionTypes.moneyTransfer;
    static transactionTypes billsTransaction = transactionTypes.billsPayment;
    static transactionTypes buyLoadTransaction = transactionTypes.buyLoad;
    static transactionTypes addAccountTransaction = transactionTypes.addAccountCredits;
    static transactionTypes addGameTransaction = transactionTypes.addAccountCredits;

    //Main Method* Where all the code runs
    public static void main(String[] args) {
        //Invoke Data
        init();
        //Show All Transaction in ArrayList(transaction)
        showTransaction();
        //Show All Transaction base on given Transaction Type
        showTransactionType(billsTransaction);
        //Checking Duplicate and Unique (In Progress)
        //checkDupAndUnique();
    }

    //Initialization Method/Function
    static void init() {
        final String[] uName = {"John", "Matt", "Jason", "Mascard", "Niceuu"};
        final String[] cName = {"Johnsons", "Willy & Dilly", "Tiger and Poh", "Neptune", "Mars"};
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        Random ran = new Random();
        int index = ran.nextInt(cName.length);
        int nxt = ran.nextInt(8999) + 1000;
        int mt = 0;
        int bill = 0;
        int load = 0;
        int aCredits = 0;
        int gCredits = 0;

        //[5]MONEY-TRANSFER DATA
        while (mt < 5) {
            transactions.add(new moneyTransfer(nxt, nxt, nxt, date, bill, uName[index]));
            mt++;
        }

        //[3]BILLS-PAYMENT DATA
        transactions.add(new bills(0001, 2525, 7777, date, 250.25, "7-Eleven", 10.25));
        transactions.add(new bills(0001, 2525, 7777, date, 250.25, "7-Eleven", 10.25));
        transactions.add(new bills(0001, 3525, 5235, date, 300.25, "DBlitz", 5.25));
//        while( bill < 3){
//            transactions.add(new bills(nxt,nxt,nxt,date,bill,cName[index],nxt));
//            bill++;
//        }
        //[5]BUY-LOAD
        while (load < 5) {
            transactions.add(new buyLoad(nxt, nxt, nxt, date, 250.25, 09175582631f));
            load++;
        }

        //[2]ADD-ACCOUNT-CREDITS
        while (aCredits < 2) {
            transactions.add(new addAccountCredit(nxt, nxt, nxt, date, 355.25, 09175582631f));
            aCredits++;
        }

        //[5]ADD-GAME-CREDITS
        while (gCredits < 5) {
            transactions.add(new addGameCredit(nxt, nxt, nxt, date, 355.25, cName[index]));
            gCredits++;
        }
    }

    //Method to display all Transactions
    static void showTransaction() {
        for (int index = 0; index < transactions.size(); index++) {
            logger.log(Level.INFO, "Entry : " + transactions.get(index));
        }
    }

    //Method to display Transaction base on Transaction Type
    static void showTransactionType(transactionTypes a) {
        switch (a) {
            //Invoked if Transaction Type is Bills Payment
            case billsPayment -> {
                for (transaction transaction : transactions) {
                    if (transaction instanceof bills) {
                        logger.log(Level.INFO, "Entry : " + transaction);
                    }
                }
            }

            //Invoked if Transaction Type is Money Transfer
            case moneyTransfer -> {
                for (transaction transaction : transactions) {
                    if (transaction instanceof moneyTransfer) {
                        logger.log(Level.INFO, "Entry : " + transaction);
                    }
                }
            }

            //Invoked if Transaction Type is Add Account Credit
            case addAccountCredits -> {
                for (transaction transaction : transactions) {
                    if (transaction instanceof addAccountCredit) {
                        logger.log(Level.INFO, "Entry : " + transaction);
                    }
                }
            }

            //Invoked if Transaction Type is Add Game Credit
            case addGameCredits -> {
                for (transaction transaction : transactions) {
                    if (transaction instanceof addGameCredit) {
                        logger.log(Level.INFO, "Entry : " + transaction);
                    }
                }
            }

            //Invoked if Transaction Type is Buy Load
            case buyLoad -> {
                for (transaction transaction : transactions) {
                    if (transaction instanceof buyLoad) {
                        logger.log(Level.INFO, "Entry : " + transaction);
                    }
                }
            }
        }
    }

    static void checkDupAndUnique() {

    }
}