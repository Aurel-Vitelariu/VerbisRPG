import java.util.Scanner;

public class InputHandler {
    public String getCommand(Scanner scanner) {
        System.out.print("Enter a command: ");
        return scanner.nextLine().trim().toLowerCase();
    }
}
