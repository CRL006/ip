import java.util.ArrayList;
import java.util.Scanner;
import tasks.*;

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
