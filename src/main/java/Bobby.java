import java.util.Scanner;  // Import the Scanner class
public class Bobby {
    public static void main(String[] args) {
        String greeting = "Hello! I'm Bobby! \nWhat can I do for you? \n";
        System.out.println(greeting);
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        String userInput = scanner.nextLine();
        while (!userInput.equals("bye")) {
            System.out.println(userInput);
            userInput = scanner.nextLine();
        }
        String endChat = "Bye. Hope to see you again soon!";
        System.out.println(endChat);
    }
}