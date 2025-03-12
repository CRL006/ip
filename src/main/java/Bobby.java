import java.util.ArrayList;
import exceptions.UnknownCommandException;
import functions.Parser;
import functions.Storage;
import functions.TaskList;
import functions.Ui;
import tasks.Task;


public class Bobby {

    private static Ui ui;
    private static Storage storage;
    private static Parser parser;
    private static TaskList taskList;

    public static void main(String[] args) {
        ui = new Ui();
        storage = new Storage();
        parser = new Parser();
        taskList = new TaskList();
        ui.printWelcomeMessage();
        ArrayList<Task> tasks;
        tasks = storage.loadFromFile();
        storage.setupFile();
        String userInput = ui.readUserInput();
        while (true) {
            try {
                String action = parser.processInput(userInput);
                if (action.equals("Terminate chat")) {
                    ui.printEndMessage();
                    break;
                }
                parser.carryOut(action, userInput, tasks, storage, ui, taskList);
            } catch (UnknownCommandException e) {
                ui.printUnknownCommandError();
            }
            userInput = ui.readUserInput();
        }
    }
}