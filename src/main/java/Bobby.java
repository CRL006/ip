import java.util.*;  // Import the Scanner class
import Exceptions.*;
import tasks.*;
import java.io.*;
import java.nio.file.*;


public class Bobby {
    public static void saveToFile(ArrayList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter("./src/main/java/data/bobby.txt");
            for (Task task : tasks) {
                writer.write(task.toString() + "\n");  // Add each task to the file, followed by a new line
            }
            writer.close();  // Close the writer when done
        } catch (IOException e) {
            System.out.println("Error saving tasks to file.");
        }
    }

    public static ArrayList<Task> loadFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        try (Scanner fileScanner = new Scanner(new File("./src/main/java/data/bobby.txt"))) {
            while (fileScanner.hasNextLine()) {
                String taskLine = fileScanner.nextLine();
                // Parse the task string and create the Task object based on the data
                Task task = Task.fromString(taskLine);  // You would need a fromString() method to parse the task
                System.out.println(task);
                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks from file.");
        }
        return tasks;
    }

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
        tasks.get(deleteIndex).printTaskCount(tasks.size()-1); // +1 because array index starts with 0
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

    public static void carryOut(String action, String input, ArrayList<Task> tasks) {
        try {
            switch (action) {
            case "Print list" -> {
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.print((i + 1) + ". ");
                    tasks.get(i).printTask();
                }
            }
            case "Change isDone" -> {
                splitMark(input, !input.contains("unmark"), tasks);
                saveToFile(tasks);
            }
            case "Add todo" -> {
                String item = splitToDo(input);
                tasks.add(new todo(item, false));
                printTaskAdditionMessages(tasks);
                saveToFile(tasks);
            }
            case "Add event" -> {
                String[] eventDetails = splitEvent(input);
                tasks.add(new Event(eventDetails[0], eventDetails[1], eventDetails[2], false));
                printTaskAdditionMessages(tasks);
                saveToFile(tasks);
            }
            case "Add deadline" -> {
                String[] deadlineDetails = splitDeadline(input);
                tasks.add(new Deadline(deadlineDetails[0], deadlineDetails[1], false));
                printTaskAdditionMessages(tasks);
                saveToFile(tasks);
            }
            case "Delete task" -> {
                int deleteIndex = getDeleteIndex(input, tasks);
                printTaskDeletionMessages(tasks, deleteIndex);
                tasks.remove(deleteIndex);
                saveToFile(tasks);
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

    public static void main(String[] args) {
        System.out.println("Hello! I'm Bobby! \nWhat can I do for you?");
        ArrayList<Task> tasks = new ArrayList<>();
        tasks = loadFromFile();
        Scanner scanner = new Scanner(System.in);  // Create a Scanner object
        File tasksList = new File("./src/main/java/data/bobby.txt");
        try {
            if (!tasksList.exists()) {
                Files.createDirectories(Paths.get("./src/main/java/data"));
                Files.createFile(Paths.get("./src/main/java/data/bobby.txt"));
            }
        } catch (IOException e){
            System.out.println("Error retrieving file");
        }
        String userInput = scanner.nextLine();
        while (true) {
            try {
                String action = processInput(userInput);
                if (action.equals("Terminate chat")) {
                    System.out.println("Bye! Hope to see you again soon!");
                    break;
                }
                carryOut(action, userInput, tasks);
            } catch (UnknownCommandException e) {
                System.out.println("I'm sorry I don't have that functionality yet!! Please try something else!");
                System.out.println("Examples of things I can do: \n - add todo\n - add event \n - add deadline");
            }
            userInput = scanner.nextLine();
        }
    }
}