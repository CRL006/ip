package tasks;

public class todo extends Task{

    public todo(String item, boolean isDone) {
        super(item, isDone);
    }
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
