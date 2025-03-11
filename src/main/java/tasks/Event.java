package tasks;

/**
 * The {@code Deadline} class represents an event which has an item description,
 * a due date, and a completion status (done or not done).
 *
 * <p>It is a subclass of {@link Task} and inherits its attributes and methods,
 * including the task description and completion status. The event task includes
 * two additional fields {@code start} and {@code end}, which represents when the task starts and ends.</p>
 *
 * @see Task
 */
public class Event extends Task{
    public String start;
    public String end;

    public Event(String item, String start, String end, boolean isDone) {
        super(item, isDone);
        this.start = start;
        this.end = end;
    }

    /**
     * Returns a string representation of the task, specifically for an event task for it to be saved in the file
     *
     * @return A string representation of the deadline task in the format:
     *         "D | [done/not done] | [task description] | by: [due date]".
     */
    @Override
    public String toString() {
        return "E | " + (isDone ? "done" : "not done") + " | " + item + " | from: " + start + " | to: " + end;
    }

    @Override
    public void printTask()
    {
        System.out.print("  [E][");
        if (isDone) {
            System.out.print("X");
        } else {
            System.out.print(" ");
        }
        System.out.println("] " + this.item + " (from: " + this.start + " to: " + this.end + ")");
    }
}
