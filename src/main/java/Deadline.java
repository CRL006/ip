public class Deadline extends Task{
    public String item;
    public String dueDate;

    public Deadline(String item, String dueDate) {
        this.item = item;
        this.dueDate = dueDate;
        this.isDone = false;
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
