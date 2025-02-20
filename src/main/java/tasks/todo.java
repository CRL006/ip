package tasks;

public class todo extends Task{
    public String item;

    public todo(String item) {
        this.item = item;
        this.isDone = false;
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
