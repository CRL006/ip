package tasks;

public class Event extends Task{
    public String start;
    public String end;

    public Event(String item, String start, String end, boolean isDone) {
        super(item, isDone);
        this.start = start;
        this.end = end;
    }

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
