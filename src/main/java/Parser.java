import Exceptions.*;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.todo;
import java.util.ArrayList;

public class Parser {

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

    public static String splitToDo(String input) throws MissingDescriptionException {
        int stringLength = input.length();
        if (stringLength < 6) {
            throw new MissingDescriptionException();
        }
        return input.substring(5, stringLength);
    }

    public static void printTaskDeletionMessages(ArrayList<Task> tasks, int deleteIndex) {
        tasks.get(deleteIndex).printDeleteAcknowledgement();
        tasks.get(deleteIndex).printTask();
        tasks.get(deleteIndex).printTaskCount(tasks.size()-1);
    }

    public static int getDeleteIndex(String input, ArrayList<Task> tasks) throws MissingTaskException, AlreadyDoneException, AlreadyUndoneException {
        String[] splitDelete = input.split(" ");
        int deleteIndex = Integer.parseInt(splitDelete[1]) - 1;
        if (deleteIndex < 0 || deleteIndex >= tasks.size()) {
            throw new MissingTaskException();
        }
        return deleteIndex;
    }

    public static void printTaskAdditionMessages(ArrayList<Task> tasks) {
        tasks.get(tasks.size()-1).printAcknowledgement();
        tasks.get(tasks.size()-1).printTask();
        tasks.get(tasks.size()-1).printTaskCount(tasks.size());
    }

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
