import Exceptions.*;
import tasks.Task;

import java.util.ArrayList;

/**
 * The {@code Parser} class is responsible for processing and interpreting user input commands in a task management application.
 * <p>
 * This class contains methods for parsing various commands, such as adding tasks, marking tasks as done or undone,
 * deleting tasks, and finding tasks that match a keyword. It handles different task types, including to-do tasks, event tasks,
 * and deadline tasks, by extracting relevant information from the user input. The methods in this class also ensure that the
 * provided input is valid and raise appropriate exceptions if the input format is incorrect or if actions are not possible.
 * </p>
 * <p>
 * The class provides functionality to carry out user commands, which are then executed by updating the task list and interacting
 * with storage and UI components.
 * </p>
 */
public class Parser {

    /**
     * Processes the user's input and determines the appropriate action to be carried out in {@code carryOut}.
     * <p>
     * This method evaluates the input string and returns a corresponding message based on recognized commands.
     * Supported commands include:
     * <ul>
     *     <li>"bye" - Terminates the chat.</li>
     *     <li>"list" - Prints the list of tasks.</li>
     *     <li>"mark" - Changes the completion status of a task.</li>
     *     <li>"todo" - Adds a to-do task.</li>
     *     <li>"event" - Adds an event task.</li>
     *     <li>"deadline" - Adds a task with a deadline.</li>
     *     <li>"delete" - Deletes a task.</li>
     *     <li>"find" - Finds tasks containing a keyword.</li>
     * </ul>
     * If the input does not match any known command, an {@link UnknownCommandException} is thrown.
     * </p>
     *
     * @param input The user's input string to be processed.
     * @return A string indicating the action corresponding to the input.
     * @throws UnknownCommandException if the input does not match any known command.
     */
    public static String processInput(String input) throws UnknownCommandException {
        if (input.equals("bye")) {
            return "Terminate chat";
        } else if (input.equals("list")) {
            return "Print list";
        } else if (input.contains("mark")) {
            return "Change isDone";
        } else if (input.contains("todo")) {
            return "Add todo";
        } else if (input.contains("event")) {
            return "Add event";
        } else if (input.contains("deadline")) {
            return "Add deadline";
        } else if (input.contains("delete")) {
            return "Delete task";
        } else if (input.contains("find")) {
            return "Find keyword";
        } else {
            throw new UnknownCommandException();
        }
    }

    /**
     * Marks a task as done or undone based on user input.
     * <p>
     * This method processes the user's input string to determine which task to mark as done or undone
     * in the provided list of tasks. It checks if the task exists and whether the task is already in the desired state
     * (done or undone). If the task index is invalid, or the task's current status conflicts with the requested change,
     * relevant exceptions are thrown.
     * </p>
     *
     * @param input The user input containing the command to mark or unmark a task.
     *              The format is expected to be "mark {taskNumber}" or "unmark {taskNumber}".
     * @param isMarked A boolean value indicating whether the task should be marked as done (true) or undone (false).
     * @param tasks The list of {@link Task} objects to update based on the user's input.
     * @throws MissingTaskException if the task number provided does not correspond to any task in the list.
     * @throws AlreadyDoneException if the task is already marked as done and the user attempts to mark it again.
     * @throws AlreadyUndoneException if the task is already marked as undone and the user attempts to unmark it again.
     */
    public static void splitMark(String input, boolean isMarked, ArrayList<Task> tasks) throws MissingTaskException, AlreadyDoneException, AlreadyUndoneException {
        String[] splitMark = input.split(" ");
        int markIndex = Integer.parseInt(splitMark[1]) - 1;
        if (markIndex < 0 || markIndex >= tasks.size()) {
            throw new MissingTaskException();
        }
        if (isMarked) {
            if (tasks.get(markIndex).isDone) {
                throw new AlreadyDoneException();
            }
            System.out.println("Nice! I've marked this task as done:");
            tasks.get(markIndex).setDone(true);
            tasks.get(markIndex).printTask();
        } else {
            if (!tasks.get(markIndex).isDone) {
                throw new AlreadyUndoneException();
            }
            System.out.println("OK, I've marked this task as not done yet:");
            tasks.get(markIndex).setDone(false);
            tasks.get(markIndex).printTask();
        }
    }

    /**
     * Splits the given deadline input into a description and a date/time string.
     *
     * <p>The input string should contain a description part followed by a slash ("/") and a date/time part.
     * If the input does not conform to this format, appropriate exceptions are thrown.</p>
     *
     * @param input The input string representing a deadline, where the description and date/time are separated by a slash ("/").
     * @return A String array with two elements:
     *         - details[0] contains the description before the slash.
     *         - details[1] contains the date/time after the slash.
     * @throws MissingDescriptionException If the description part is missing or the input is too short.
     * @throws MissingDateTimeException If the date/time part is missing or the slash is absent.
     */
    public static String[] splitDeadline(String input) throws MissingDescriptionException, MissingDateTimeException {
        String[] details = new String[2];
        int stringLength = input.length();
        if (stringLength < 10) {
            throw new MissingDescriptionException();
        }
        int indexOfSlash = input.indexOf("/");
        if (indexOfSlash == -1) {
            throw new MissingDateTimeException();
        }
        details[0] = input.substring(9, indexOfSlash - 1);
        details[1] = input.substring(indexOfSlash + 4, stringLength);
        return details;
    }

    /**
     * Splits the input string into three parts: description, date, and time.
     *
     * <p>The input string is expected to follow a specific format, where the description starts
     * after the 6th character and ends before the first slash ('/'). The date is placed between
     * the first and last slashes, and the time is placed after the last slash.</p>
     *
     * @param input The input string to be split, expected to contain a description, date, and time
     * @return An array of strings containing three elements: description, date, and time
     * @throws MissingDescriptionException if the input string is too short to contain a description
     * @throws MissingDateTimeException if the input string is missing slashes or doesn't have a valid date-time format
     */
    public static String[] splitEvent(String input) throws MissingDescriptionException, MissingDateTimeException {
        String[] details = new String[3];
        int stringLength = input.length();
        if (stringLength < 7) {
            throw new MissingDescriptionException();
        }
        int indexOfFirstSlash = input.indexOf("/");
        int indexOfLastSlash = input.lastIndexOf("/");
        if (indexOfFirstSlash == -1 || indexOfLastSlash == -1) {
            throw new MissingDateTimeException();
        }
        details[0] = input.substring(6, indexOfFirstSlash - 1);
        details[1] = input.substring(indexOfFirstSlash + 6, indexOfLastSlash - 1);
        details[2] = input.substring(indexOfLastSlash + 4, stringLength);
        return details;
    }

    /**
     * Extracts the description of a to-do task from the user input.
     * <p>
     * This method processes the user's input, which is expected to follow the format
     * "todo {taskDescription}". It checks if the description is provided and extracts
     * the task description starting from the 6th character. If no description is provided,
     * it throws a {@link MissingDescriptionException}.
     * </p>
     *
     * @param input The user input string that contains the "todo" command and the task description.
     * @return A string representing the task description extracted from the input.
     * @throws MissingDescriptionException if the input does not include a description for the to-do task.
     */
    public static String splitToDo(String input) throws MissingDescriptionException {
        int stringLength = input.length();
        if (stringLength < 6) {
            throw new MissingDescriptionException();
        }
        return input.substring(5, stringLength);
    }

    /**
     * Prints messages related to the deletion of a task from the list.
     *
     * <p>This method retrieves and prints the acknowledgement message, details of the task to be deleted,
     * and updates the task count after the deletion.</p>
     *
     * @param tasks The list of tasks from which one will be deleted.
     * @param deleteIndex The index of the task to be deleted in the `tasks` list.
     */
    public static void printTaskDeletionMessages(ArrayList<Task> tasks, int deleteIndex) {
        tasks.get(deleteIndex).printDeleteAcknowledgement();
        tasks.get(deleteIndex).printTask();
        tasks.get(deleteIndex).printTaskCount(tasks.size()-1);
    }

    /**
     * Retrieves the index of a task to be deleted from the user input.
     * <p>
     * This method processes the user's input to extract the task number,
     * converts it to an index (zero-based), and checks if the task exists in the list.
     * If the task index is invalid (out of bounds), a {@link MissingTaskException} is thrown.
     * </p>
     *
     * @param input The user input string containing the "delete {taskNumber}" command.
     * @param tasks The list of {@link Task} objects from which the task will be deleted.
     * @return The zero-based index of the task to be deleted.
     * @throws MissingTaskException if the task number provided does not correspond to any task in the list.
     */
    public static int getDeleteIndex(String input, ArrayList<Task> tasks) throws MissingTaskException, AlreadyDoneException, AlreadyUndoneException {
        String[] splitDelete = input.split(" ");
        int deleteIndex = Integer.parseInt(splitDelete[1]) - 1;
        if (deleteIndex < 0 || deleteIndex >= tasks.size()) {
            throw new MissingTaskException();
        }
        return deleteIndex;
    }

    /**
     * Prints the acknowledgment, task details, and the current task count for the last task in the list.
     *
     * <p>This method accesses the last task in the provided list of tasks, and then calls three methods
     * on that task: one to print an acknowledgment message, one to print the task details,
     * and one to print the updated task count.</p>
     *
     * @param tasks The list of tasks, which should contain at least one task.
     *              The last task in the list will be used to print the task details and messages.
     * @throws IndexOutOfBoundsException If the list of tasks is empty.
     */
    public static void printTaskAdditionMessages(ArrayList<Task> tasks) {
        tasks.get(tasks.size()-1).printAcknowledgement();
        tasks.get(tasks.size()-1).printTask();
        tasks.get(tasks.size()-1).printTaskCount(tasks.size());
    }

    /**
     * Executes the specified action on the given input, interacting with the task list, storage, UI, and task manager.
     *
     * <p>This method takes an action and performs a corresponding operation such as printing the task list,
     * changing the status of a task, adding new tasks (ToDo, Event, Deadline), deleting tasks, or finding tasks
     * based on a keyword. It also updates the storage after each action that modifies the task list.</p>
     *
     * @param action The action to be performed, such as "Print list", "Change isDone", "Add todo", "Add event",
     *               "Add deadline", "Delete task", or "Find keyword".
     * @param input The input provided by the user to execute the action. The format depends on the action type.
     * @param tasks The list of tasks that will be modified or referenced by the action.
     * @param storage The storage object used to save the updated task list to a file.
     * @param ui The UI object responsible for displaying task-related messages to the user.
     * @param tasklist The TaskList object that handles the management of tasks, including adding and deleting tasks.
     *
     * @throws MissingTaskException If the user tries to mark or unmark a task that does not exist in the task list.
     * @throws AlreadyDoneException If the user attempts to mark a task as done that is already marked as done.
     * @throws AlreadyUndoneException If the user attempts to unmark a task that has not been marked as done.
     * @throws MissingDescriptionException If the user fails to provide a description when adding a task.
     * @throws MissingDateTimeException If the user fails to provide the required date/time information for event or deadline tasks.
     */
    public static void carryOut(String action, String input, ArrayList<Task> tasks, Storage storage, Ui ui, TaskList tasklist) {
        try {
            switch (action) {
            case "Print list" -> {
                ui.printTaskList(tasks);
            }
            case "Change isDone" -> {
                splitMark(input, !input.contains("unmark"), tasks);
                storage.saveToFile(tasks);
            }
            case "Add todo" -> {
                String item = splitToDo(input);
                tasklist.addTodo(item, tasks);
                printTaskAdditionMessages(tasks);
                storage.saveToFile(tasks);
            }
            case "Add event" -> {
                String[] eventDetails = splitEvent(input);
                tasklist.addEvent(eventDetails[0], eventDetails[1], eventDetails[2], tasks);
                printTaskAdditionMessages(tasks);
                storage.saveToFile(tasks);
            }
            case "Add deadline" -> {
                String[] deadlineDetails = splitDeadline(input);
                tasklist.addDeadline(deadlineDetails[0], deadlineDetails[1], tasks);
                printTaskAdditionMessages(tasks);
                storage.saveToFile(tasks);
            }
            case "Delete task" -> {
                int deleteIndex = getDeleteIndex(input, tasks);
                printTaskDeletionMessages(tasks, deleteIndex);
                tasklist.deleteItem(deleteIndex, tasks);
                storage.saveToFile(tasks);
            }
            case "Find keyword" -> {
                int matchingTasks = 0;
                String keyword = input.substring(5, input.length());
                for (int i = 0; i < tasks.size(); i++) {
                    if (tasks.get(i).getTask().contains(keyword)) {
                        if (matchingTasks == 0) {
                            System.out.println("Here are the matching tasks in your list:");
                        }
                        System.out.print((matchingTasks + 1) + ". ");
                        tasks.get(i).printTask();
                        matchingTasks++;
                    }
                }
                if (matchingTasks == 0) {
                    System.out.println("There were no matching tasks in your list!");
                }
            }
            }
        } catch (MissingTaskException e) {
            System.out.println("Sorry, you only have " + (tasks.size()) + " tasks in the system!");
        } catch (AlreadyDoneException e) {
            System.out.println("That task has already been done!");
        } catch (AlreadyUndoneException e) {
            System.out.println("That task has not been done yet!");
        } catch (MissingDescriptionException e) {
            System.out.println("Please enter a task to add!");
            System.out.println("Examples: \n - todo read book \n - event meeting /from 3pm /to 5pm \n - deadline return book /by Friday");
        } catch (MissingDateTimeException e) {
            System.out.println("Please enter a date/time!");
            System.out.println("Examples: \n - event meeting /from 3pm /to 5pm \n - deadline return book /by Friday");
        }
    }
}
