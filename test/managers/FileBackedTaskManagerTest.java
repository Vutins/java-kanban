package managers;

import classes.tasks.Epic;
import classes.tasks.Subtask;
import classes.tasks.Task;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class FileBackedTaskManagerTest {

    TaskManager taskManager;

    @Test
    void startProgram() throws IOException {
        taskManager = new FileBackedTaskManager(Path.of(String.valueOf(File.createTempFile("data", ".txt"))));

        Task task1 = new Task("delo1", "delau delo1");
        taskManager.addTask(task1);
        Task task2 = new Task("delo2", "delau delo2");
        taskManager.addTask(task2);
        Epic epic1 = new Epic("epic1", "delau epic1");
        taskManager.addEpic(epic1);
        Subtask subtask1 = new Subtask("subtask1", "delau subtask1", epic1.getId());
        taskManager.addSubtask(subtask1);

        System.out.println("Задачи:");
        for (Task task : taskManager.getTasks()) {
            System.out.println(task);
        }

        System.out.println("Эпики:");
        for (Epic epic : taskManager.getEpics()) {
            System.out.println(epic);

            for (Subtask subtask : taskManager.getSubtasksByEpic(epic)) {
                System.out.println("--> " + subtask);
            }
        }

        System.out.println("Подзадачи:");
        for (Subtask subtask : taskManager.getSubtasks()) {
            System.out.println(subtask);
        }
    }

    @Test
    void shouldAddTasksInFile() throws IOException {
        taskManager = FileBackedTaskManager.loadFromFile(Path.of(String.valueOf(File.createTempFile("data", ".txt"))));

        System.out.println("Задачи:");
        for (Task task : taskManager.getTasks()) {
            System.out.println(task);
        }

        System.out.println("Эпики:");
        for (Epic epic : taskManager.getEpics()) {
            System.out.println(epic);

            for (Subtask subtask : taskManager.getSubtasksByEpic(epic)) {
                System.out.println("--> " + subtask);
            }
        }

        System.out.println("Подзадачи:");
        for (Subtask subtask : taskManager.getSubtasks()) {
            System.out.println(subtask);
        }
    }

    @Test
    void shouldShowAllTasksFromStartProgram1() throws IOException {
        taskManager = FileBackedTaskManager.loadFromFile(Path.of(String.valueOf(File.createTempFile("data", ".txt"))));
        System.out.println("Задачи:");
        for (Task task : taskManager.getTasks()) {
            System.out.println(task);
        }

        System.out.println("Эпики:");
        for (Epic epic : taskManager.getEpics()) {
            System.out.println(epic);

            for (Subtask subtask : taskManager.getSubtasksByEpic(epic)) {
                System.out.println("--> " + subtask);
            }
        }

        System.out.println("Подзадачи:");
        for (Subtask subtask : taskManager.getSubtasks()) {
            System.out.println(subtask);
        }

        taskManager.removeTaskById(0);
        taskManager.removeSubtaskById(3);
    }

    @Test
    void ShouldShowTAsksWithoutDeletedTasks() throws IOException{
        taskManager = FileBackedTaskManager.loadFromFile(Path.of(String.valueOf(File.createTempFile("data", ".txt"))));

        System.out.println("Задачи:");
        for (Task task : taskManager.getTasks()) {
            System.out.println(task);
        }

        System.out.println("Эпики:");
        for (Epic epic : taskManager.getEpics()) {
            System.out.println(epic);

            for (Subtask subtask : taskManager.getSubtasksByEpic(epic)) {
                System.out.println("--> " + subtask);
            }
        }

        System.out.println("Подзадачи:");
        for (Subtask subtask : taskManager.getSubtasks()) {
            System.out.println(subtask);
        }
    }
}
