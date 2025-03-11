import tasks.Task;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The {@code Storage} class is responsible for handling file operations related to saving, loading,
 * and setting up tasks in a file.
 * <p>
 * This class provides methods to:
 * <ul>
 *   <li>Load tasks from a file</li>
 *   <li>Save tasks to a file</li>
 *   <li>Set up the required file and directories if they don't exist</li>
 * </ul>
 * All tasks are stored in a file named "bobby.txt" located in the "data" directory.
 * </p>
 *
 * <p>If file-related errors occur, such as issues reading from or writing to the file,
 * error messages will be printed to the console.</p>
 */

public class Storage {

    /**
     * Loads a list of tasks from a file.
     * <p>
     * This method reads each line from the "bobby.txt" file located in the "data" directory,
     * converts it into a {@link Task} object using the {@code Task.fromString()} method, and adds it to an {@link ArrayList}.
     * If the file cannot be read due to an {@link IOException}, an error message is printed to the console.
     * </p>
     *
     * @return An {@link ArrayList} containing the tasks loaded from the file. If the file is empty or unreadable,
     *         an empty list is returned.
     * @throws IOException if an error occurs during file reading.
     */
    public static ArrayList<Task> loadFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        try (Scanner fileScanner = new Scanner(new File("./src/main/java/data/bobby.txt"))) {
            while (fileScanner.hasNextLine()) {
                String taskLine = fileScanner.nextLine();
                Task task = Task.fromString(taskLine);
                tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks from file.");
        }
        return tasks;
    }

    /**
     * Creates and sets up a file for storing tasks if it does not already exist.
     * <p>
     * This method checks if the file "bobby.txt" located in the "data" directory exists.
     * If the file or the directory does not exist, the directory and the file will be created.
     * If an {@link IOException} occurs during this process, an error message is printed to the console.
     * </p>
     *
     * @throws IOException if an error occurs while creating directories or the file.
     */
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

    /**
     * Saves a list of tasks to a file.
     * <p>
     * This method writes each task from the list into the "bobby.txt" file,
     * located in the "data" directory. Each task is written on a new line.
     * If an {@link IOException} occurs during the writing process, an error message is printed to the console.
     * </p>
     *
     * @param tasks The list of {@link Task} objects to be saved to the file.
     * @throws IOException if an error occurs during writing to the file.
     */
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
