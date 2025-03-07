package tasks;

public class Deadline extends Task{
    public String item;
    public String dueDate;

    public Deadline(String item, String dueDate, boolean isDone) {
        super(item, isDone);
        this.item = item;
        this.dueDate = dueDate;
    }

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
