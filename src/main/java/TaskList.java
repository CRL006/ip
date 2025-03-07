import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.todo;

import java.util.ArrayList;

public class TaskList {

    public static void addTodo(String item, ArrayList<Task> tasks) {
        tasks.add(new todo(item, false));
    }

    public static void addEvent(String item, String start, String end, ArrayList<Task> tasks) {
        tasks.add(new Event(item, start, end, false));
    }

    public static void addDeadline(String item, String time, ArrayList<Task> tasks) {
        tasks.add(new Deadline(item, time, false));
    }

    public static void deleteItem(int deleteIndex, ArrayList<Task> tasks) {
        tasks.remove(deleteIndex);
    }

}
