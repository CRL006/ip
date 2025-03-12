package functions;

import java.util.ArrayList;
import java.util.Scanner;
import tasks.Task;

/**
 * The {@code Ui} class is responsible for processing and interpreting user input commands in a task management application.
 * <p>
 * The `Parser` class contains methods for parsing various commands, such as adding tasks, marking tasks as done or undone,
 * deleting tasks, and finding tasks that match a keyword. It handles different task types, including to-do tasks, event tasks,
 * and deadline tasks, by extracting relevant information from the user input. The methods in this class also ensure that the
 * provided input is valid and raise appropriate exceptions if the input format is incorrect or if actions are not possible.
 * </p>
 * <p>
 * The class provides functionality to carry out user commands, which are then executed by updating the task list and interacting
 * with storage and UI components.
 * </p>
 */
public class Ui {

    private Scanner scanner = new Scanner(System.in);


    public String readUserInput() {
        return scanner.nextLine();
    }

    public void printWelcomeMessage() {
        System.out.println("Hello! I'm Bobby! \nWhat can I do for you?");
    }

    public void printEndMessage() {
        System.out.println("Bye! Hope to see you again soon!");
    }

    /**
     * Prints an error message when an unknown command is entered by the user.
     *
     * <p>This method notifies the user that the entered command is not recognized,
     * and provides examples of valid commands that the user can try.</p>
     *
     * <p>Valid commands include adding tasks like "todo", "event", and "deadline".</p>
     *  */
    public void printUnknownCommandError() {
        System.out.println("I'm sorry I don't have that functionality yet!! Please try something else!");
        System.out.println("Examples of things I can do: \n - add todo\n - add event \n - add deadline");
    }

    public void printTaskList(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list: ");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.print((i + 1) + ". ");
            tasks.get(i).printTask();
        }
    }
}
