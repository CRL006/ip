public class Event extends Task{
    public String item;
    public String start;
    public String end;

    public Event(String item, String start, String end) {
        this.item = item;
        this.start = start;
        this.end = end;
        this.isDone = false;
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
