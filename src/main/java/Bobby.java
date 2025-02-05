import java.util.Scanner;  // Import the Scanner class
public class Bobby {
    public static void main(String[] args) {
        int tasksCounter = 0;
        int markIndex;
        String greeting = "Hello! I'm Bobby! \nWhat can I do for you? \n";
        System.out.println(greeting);
        String[] tasks = new String[100];
        Boolean[] isDone = new Boolean[100];
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        String userInput = scanner.nextLine();
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < tasksCounter; i++) {
                    System.out.print((i+1) + ". ");
                    if (isDone[i]) {
                        System.out.print("[X] ");
                    }
                    else {
                        System.out.print("[ ] ");
                    }
                    System.out.println(tasks[i]);
                }
            } else if (userInput.contains("unmark")) {
                String[] splitMark = userInput.split(" ");
                markIndex = Integer.parseInt(splitMark[1]);
                isDone[markIndex-1] = false;
                System.out.println("OK, I've marked this task as done: \n  [ ] " + tasks[markIndex - 1]);
            } else if (userInput.contains("mark")) {
                String[] splitMark = userInput.split(" ");
                markIndex = Integer.parseInt(splitMark[1]);
                isDone[markIndex-1] = true;
                System.out.println("Nice! I've marked this task as done: \n  [X] " + tasks[markIndex -1]);
            } else {
                System.out.println("added: " + userInput);
                tasks[tasksCounter] = userInput;
                isDone[tasksCounter] = false;
                tasksCounter++;
            }
            userInput = scanner.nextLine();
        }
        String endChat = "Bye. Hope to see you again soon!";
        System.out.println(endChat);
    }
}