import java.util.*;

public class TextMenuMain {

    static Scanner userIn = new Scanner(System.in);
    
    public enum option1 {
        RED,
        BLUE,
        GREEN,
        YELLOW,
    }
    public enum option2 {
        ONE,
        TWO,
        THREE,
    }
    public enum option3 {
        HELLO_WORLD,
        GOODBYE,
        SUSIPCIOUS,
        AMONGUGSNGS,
    }

    public static void main(String[] args) {

        String input = "";
        boolean running = true;

        TextMenu menu = new TextMenu();
        menu.add(new Selection(option1.values()))
            .add(new Selection(option2.values()))
            .add(new Selection(option3.values()));

        while (running) {

            clear();
            menu.printToTelemetry();
            input = userIn.nextLine().toLowerCase();

            if (input.matches("quit|q")) {
                running = false;
            } else if (input.matches("[wasdc]")) {
                menu.moveHoverTemp(input);
            }
        }

        System.out.println("hello world!");

    }



    public static void render() {
        
    }

    // yoinked from random stack overflow thread
    public static void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}