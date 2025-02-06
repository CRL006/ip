import java.util.Scanner;  // Import the Scanner class
public class Bobby {
    public static void main(String[] args) {
        int tasksCounter = 0;
        int markIndex;
        int stringLength;
        String greeting = "Hello! I'm Bobby! \nWhat can I do for you? \n";
        System.out.println(greeting);
        String[] tasks = new String[100];
        Boolean[] isDone = new Boolean[100];
        int[] taskType = new int[100];
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        String userInput = scanner.nextLine();
        while (!userInput.equals("bye")) {
            if (userInput.equals("list")) {
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < tasksCounter; i++) {
                    System.out.print((i + 1) + ". ");
                    if (taskType[i] == 0) {
                        System.out.print("[T]");
                    } else if (taskType[i] == 1){
                        System.out.print("[D]");
                    } else {
                        System.out.print("[E]");
                    }
                    if (isDone[i]) {
                        System.out.print("[X] ");
                    } else {
                        System.out.print("[ ] ");
                    }
                    System.out.println(tasks[i]);
                }
            } else if (userInput.contains("todo")){
                stringLength = userInput.length();
                System.out.println("Got it. I've added this task: \n  [T][ ] " + userInput.substring(5, stringLength) + "\nNow you have " + (tasksCounter + 1) + " tasks in the list.");
                tasks[tasksCounter] = userInput.substring(5, stringLength);
                isDone[tasksCounter] = false;
                taskType[tasksCounter] = 0;
                tasksCounter++;
            } else if (userInput.contains("deadline")){
                stringLength = userInput.length();
                int indexOfSlash = userInput.indexOf("/");
                tasks[tasksCounter] = userInput.substring(9, indexOfSlash-1) + " (by: " + userInput.substring(indexOfSlash+4, stringLength) + ")";
                isDone[tasksCounter] = false;
                taskType[tasksCounter] = 1;
                System.out.println("Got it. I've added this task: \n  [D][ ] " + tasks[tasksCounter] + "\nNow you have " + (tasksCounter + 1) + " tasks in the list.");
                tasksCounter++;
            } else if (userInput.contains("event")){
                stringLength = userInput.length();
                int indexOfFirstSlash = userInput.indexOf("/");
                int indexOfLastSlash = userInput.lastIndexOf("/");
                tasks[tasksCounter] = userInput.substring(6, indexOfFirstSlash-1) + " (from: " + userInput.substring(indexOfFirstSlash+6, indexOfLastSlash-1) + " to: " + userInput.substring(indexOfLastSlash + 4, stringLength) + ")";
                System.out.println("Got it. I've added this task: \n  [E][ ] " + tasks[tasksCounter] + "\nNow you have " + (tasksCounter + 1) + " tasks in the list.");
                isDone[tasksCounter] = false;
                taskType[tasksCounter] = 2;
                tasksCounter++;
            } else if (userInput.contains("unmark")) {
                String[] splitMark = userInput.split(" ");
                markIndex = Integer.parseInt(splitMark[1]) - 1;
                isDone[markIndex] = false;
                System.out.println("OK, I've marked this task as not done yet:");
                if (taskType[markIndex] == 0) {
                    System.out.print("[T]");
                } else if (taskType[markIndex] == 1){
                    System.out.print("[D]");
                } else {
                    System.out.print("[E]");
                }
                System.out.println("[ ] " + tasks[markIndex]);
            } else if (userInput.contains("mark")) {
                String[] splitMark = userInput.split(" ");
                markIndex = Integer.parseInt(splitMark[1]) - 1;
                isDone[markIndex] = true;
                System.out.println("Nice! I've marked this task as done:");
                if (taskType[markIndex] == 0) {
                    System.out.print("[T]");
                } else if (taskType[markIndex] == 1){
                    System.out.print("[D]");
                } else {
                    System.out.print("[E]");
                }
                System.out.println("[X] " + tasks[markIndex]);
            }
            userInput = scanner.nextLine();
        }
        String endChat = "Bye. Hope to see you again soon!";
        System.out.println(endChat);
    }
}