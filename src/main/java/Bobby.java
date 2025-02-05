import java.util.Scanner;  // Import the Scanner class

public class Bobby {
    public static void main(String[] args) {
        String greeting = "Hello! I'm Bobby! \nWhat can I do for you? \n";
        System.out.println(greeting);
        Scanner myObj = new Scanner(System.in);  // Create a Scanner object
        String userInput = myObj.nextLine();
        while (!userInput.equals("bye")) {
            System.out.println(userInput);
            userInput = myObj.nextLine();
        }
        String endChat = "Bye. Hope to see you again soon!";
        System.out.println(endChat);
    }
}