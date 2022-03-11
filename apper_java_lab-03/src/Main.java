import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    final private static Logger logger = Logger.getLogger(Main.class.getName());
    static Scanner scan = new Scanner(System.in);
    static Originator originator = new Originator();
    static CareTaker careTaker = new CareTaker();

    public static void main(String[] args) {
        initialData();
    }

    public static void init() {
        System.out.println("===========================");
        System.out.println("[1] - Input");
        System.out.println("[2] - Undo");
        System.out.println("[3] - States");
        System.out.println("[4] - Exit");
        System.out.println("===========================");
        System.out.print("Response: ");
        int input = scan.nextInt();
        scan.nextLine();

        switch (input) {
            case 1 -> InputData();
            case 2 -> retrieveState();
            case 3 -> retrieveAll();
            case 4 -> logger.info("Program Terminated");
        }
    }

    static void initialData(){
        System.out.print("Input: ");
        String firstInput = scan.nextLine();
        saveInput(firstInput);
    }

    static void InputData(){
        System.out.print("Input: " + originator.getState() + " ");
        String firstInput = scan.nextLine();
        String givenState = originator.getState().toString();
        String finalInput = givenState + " " + firstInput;
        saveInput(finalInput);
    }

    public static void saveInput(String input){
        originator.setState(input);
        careTaker.add(originator.saveStateToMemento());
        logger.info("State Saved: " + originator.getState());
        System.out.println("\n");
        init();
    }

    public static void retrieveState(){
        originator.getStateFromMemento(careTaker.get());
        System.out.print("Input: " + originator.getState() + " ");
        String firstInput = scan.nextLine();
        String givenState = originator.getState().toString();
        String finalInput = givenState + " " + firstInput;
        saveInput(finalInput);
    }

    public static void retrieveAll(){
        for(int counter = 0; counter < careTaker.stateCount(); counter++  ){
            originator.getStateFromMemento(careTaker.specificGet(counter));
            logger.info("State" + " " + counter + ": " + originator.getState());
        }
    }
}
