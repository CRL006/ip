package tasks;

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

    public void printTask(){
        return;
    }

    public void printDeleteAcknowledgement() {
        System.out.println("Noted. I've removed this task: ");
    }

    public String toString() {
        return " | " + (isDone ? "done" : "not done") + " | " + item;
    }

    public static Task fromString(String taskString) {
        String[] parts = taskString.split(" \\| ");
        if (parts[0].equals("T")) {
            boolean isDone = parts[1].equals("done");
            String item = parts[2];
            return new todo(item, isDone);
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
