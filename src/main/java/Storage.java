import tasks.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    public static ArrayList<Task> loadFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        try (Scanner fileScanner = new Scanner(new File("./src/main/java/data/bobby.txt"))) {
            while (fileScanner.hasNextLine()) {
                String taskLine = fileScanner.nextLine();
                Task task = Task.fromString(taskLine);
                //System.out.println(task);
                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks from file.");
        }
        return tasks;
    }

    public static void setupFile() {
        File tasksList = new File("./src/main/java/data/bobby.txt");
        try {
            if (!tasksList.exists()) {
                Files.createDirectories(Paths.get("./src/main/java/data"));
                Files.createFile(Paths.get("./src/main/java/data/bobby.txt"));
            }
        } catch (IOException e){
            System.out.println("Error retrieving file");
        }
    }

    public static void saveToFile(ArrayList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter("./src/main/java/data/bobby.txt");
            for (Task task : tasks) {
                writer.write(task.toString() + "\n");  // Add each task to the file, followed by a new line
            }
            writer.close();  // Close the writer when done
        } catch (IOException e) {
            System.out.println("Error saving tasks to file.");
        }
    }
}
