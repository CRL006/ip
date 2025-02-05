import java.util.Scanner;  // Import the Scanner class
public class Bobby {
    public static void main(String[] args) {
        int tasksCounter = 0;
        int markIndex;
        String greeting = "Hello! I'm Bobby! \nWhat can I do for you? \n";
        System.out.println(greeting);
        String[] tasks = new String[100];
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        String userInput = scanner.nextLine();
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < tasksCounter; i++) {
                    System.out.print((i+1) + ". ");
                    System.out.println(tasks[i]);
                }
            } else {
                System.out.println("added: " + userInput);
                tasks[tasksCounter] = userInput;
                tasksCounter++;
            }
            userInput = scanner.nextLine();
        }
        String endChat = "Bye. Hope to see you again soon!";
        System.out.println(endChat);
    }
}