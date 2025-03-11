package tasks;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The {@code Deadline} class represents a deadline which has an item description,
 * a due date, and a completion status (done or not done).
 *
 * <p>It is a subclass of {@link Task} and inherits its attributes and methods,
 * including the task description and completion status. The deadline task includes
 * an additional field {@code dueDate}, which represents when the task is due.</p>
 *
 * @see Task
 */
public class Deadline extends Task{
    public String item;
    public String dueDate;

    public Deadline(String item, String dueDate, boolean isDone) {
        super(item, isDone);
        this.item = item;

        if (dueDate.contains("-")) {
            try {
                LocalDate d1 = LocalDate.parse(dueDate);
                this.dueDate = d1.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            } catch (DateTimeParseException e){
                this.dueDate = dueDate;
                System.out.println("The due date has been saved as " + dueDate + ".\nIf you want it to be in the format similar to: Oct 4 2025, please enter the date in the format: 2025-04-10");
            }
        }
        else {
            this.dueDate = dueDate;
        }
    }

    /**
     * Returns a string representation of the task, specifically for a deadline task for it to be saved in the file
     *
     * @return A string representation of the deadline task in the format:
     *         "D | [done/not done] | [task description] | by: [due date]".
     */
    @Override
    public String toString() {
        return "D | " + (isDone ? "done" : "not done") + " | " + item + " | by: " + dueDate;
    }
    @Override
    public void printTask()
    {
        System.out.print("  [D][");
        if (isDone) {
            System.out.print("X");
        } else {
            System.out.print(" ");
        }
        System.out.println("] " + this.item + " (by: " + this.dueDate + ")");
    }
}
