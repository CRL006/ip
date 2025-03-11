package tasks;

/**
 * Represents a generic task with a description and a completion status.
 *
 * <p>The {@code Task} class provides basic functionality for managing tasks, including
 * setting their completion status, printing task-related information, and converting
 * task information from a string representation.</p>
 *
 * <p>It is a parent class of {@link Todo}, {@link Deadline} and {@link Event} and provides a template for them to inherit
 * </p>
 */
public class Task {
    public String item;
    public boolean isDone;
    public Task(String item, boolean isDone) {
        this.item = item;
        this.isDone = isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public void printAcknowledgement() {
        System.out.println("Got it. I've added this task:");
    }

    public void printTaskCount(int n) {
        System.out.println("Now you have " + n + " task(s) in the list.");
    }

    /**
     * Prints the task details.
     * <p>
     * This method is intended to be overridden by subclasses {@link Deadline}, {@link Event}, {@link Todo})
     * to provide specific behavior for printing task details. The base implementation does nothing.
     * </p>
     */
    public void printTask(){
        return;
    }

    /**
     * Returns the task details.
     * <p>
     * This method is intended to be overridden by subclasses {@link Deadline}, {@link Event}, {@link Todo})
     * to provide specific behavior for returning the task details. The base implementation does nothing.
     * </p>
     */
    public String getTask(){
        return "";
    }

    public void printDeleteAcknowledgement() {
        System.out.println("Noted. I've removed this task: ");
    }

    public String toString() {
        return " | " + (isDone ? "done" : "not done") + " | " + item;
    }

    /**
     * Converts a string representation of a task back into a {@link Task} object from the file.
     * <p>
     * This method processes a string formatted with task details
     * and converts it into the corresponding {@link Todo}, {@link Deadline},
     * or {@link Event} object. It is loads tasks from the file.
     * </p>
     *
     * @param taskString The string representation of the task, formatted as "Type | Status | Description | Additional Info".
     * @return A {@link Task} object corresponding to the string input, which could be a {@link Todo}, {@link Deadline}, or {@link Event}.
     *         If the string does not match any known task types, a default {@link Task} with "Not found" description is returned.
     */
    public static Task fromString(String taskString) {
        String[] parts = taskString.split(" \\| ");
        if (parts[0].equals("T")) {
            boolean isDone = parts[1].equals("done");
            String item = parts[2];
            return new Todo(item, isDone);
        } else if (parts[0].equals("D")) {
            boolean isDone = parts[1].equals("done");
            String item = parts[2];
            String dueDate = parts[3].substring(4);
            return new Deadline(item, dueDate, isDone);
        } else if (parts[0].equals("E")) {
            boolean isDone = parts[1].equals("done");
            String item = parts[2];
            String start = parts[3].substring(6);
            String end = parts[4].substring(4);
            return new Event(item, start, end, isDone);
        }
        else {
            return new Task("Not found", false);
        }
    }
}
