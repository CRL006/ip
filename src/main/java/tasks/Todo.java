package tasks;

/**
 * The {@code Todo} class represents a todo which has an item description, and a completion status (done or not done).
 *
 * <p>It is a subclass of {@link Task} and inherits its attributes and methods,
 * including the task description and completion status. The deadline task includes
 * an additional field {@code dueDate}, which represents when the task is due.</p>
 *
 * @see Task
 */
public class Todo extends Task{

    public Todo(String item, boolean isDone) {
        super(item, isDone);
    }

    /**
     * Returns a string representation of the task, specifically for a todo task for it to be saved in the file
     *
     * @return A string representation of the deadline task in the format:
     *         "D | [done/not done] | [task description] | by: [due date]".
     */
    @Override
    public String toString() {
        return "T | " + (isDone ? "done" : "not done") + " | " + item;
    }

    @Override
    public void printTask()
    {
        System.out.print("  [T][");
        if (isDone) {
            System.out.print("X");
        } else {
            System.out.print(" ");
        }
        System.out.println("] " + item);
    }
}
