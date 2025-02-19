public class Task {
    public boolean isDone;
    public Task() {
        this.isDone = false;
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
}
