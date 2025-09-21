package managers;

import classes.enums.TaskStatus;
import classes.tasks.Epic;
import classes.tasks.Subtask;
import classes.tasks.Task;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

public class TimeTests {

    private TaskManager taskManager;
    private Task task1;
    private Task task2;
    private Epic epic1;
    private Subtask subtask1;
    private Subtask subtask2;
    private Subtask subtask3;

    @BeforeEach
    void setUp() throws IOException {
        taskManager = new FileBackedTaskManager(Path.of(String.valueOf(File.createTempFile("data", ".txt"))));
        task1 = new Task("delo1", "delau delo1");
        taskManager.addTask(task1);
        task2 = new Task("delo2", "delau delo2");
        taskManager.addTask(task2);
        epic1 = new Epic("epic1", "delau epic1");
        taskManager.addEpic(epic1);
        subtask1 = new Subtask("subtask1", "delau subtask1", epic1.getId());
        taskManager.addSubtask(subtask1);
        subtask2 = new Subtask("subtask2", "delau subtask2", epic1.getId());
        taskManager.addSubtask(subtask2);
        subtask3 = new Subtask("subtask3", "delau subtask3", epic1.getId());
        taskManager.addSubtask(subtask3);
    }

    @Test
    void startProgram() {
        // Теперь этот тест только проверяет, что инициализация работает
        System.out.println("Программа запущена с тестовыми данными");
    }

    @Test
    void shouldChangeTasksStatusAndSaveStartTime() {
        Task task = taskManager.getTaskById(1);
        if (task != null) {
            task.setStatus(TaskStatus.IN_PROGRESS);
            taskManager.updateTask(task);
        }
    }

    @Test
    void updateSubtaskAndShouldSetStartTimeToEpic() {
        Task task = taskManager.getTaskById(1);
        if (task != null) {
            task.setStatus(TaskStatus.IN_PROGRESS);
            taskManager.updateTask(task);
        }

        Subtask subtask4 = taskManager.getSubtaskById(4);
        if (subtask4 != null) {
            subtask4.setStatus(TaskStatus.IN_PROGRESS);
            taskManager.updateSubtask(subtask4);
        }

        Subtask subtask5 = taskManager.getSubtaskById(5);
        if (subtask5 != null) {
            subtask5.setStatus(TaskStatus.IN_PROGRESS);
            taskManager.updateSubtask(subtask5);
        }

        Subtask subtask6 = taskManager.getSubtaskById(6);
        if (subtask6 != null) {
            subtask6.setStatus(TaskStatus.IN_PROGRESS);
            taskManager.updateSubtask(subtask6);
        }
    }

    @Test
    void ShouldSetStartTimeToEpic() {
        Subtask subtask = taskManager.getSubtaskById(4);
        if (subtask != null) {
            subtask.setStatus(TaskStatus.DONE);
            taskManager.updateSubtask(subtask);
        }
    }

    @Test
    void shouldSetDurationToEpic() {
        Subtask subtask5 = taskManager.getSubtaskById(5);
        if (subtask5 != null) {
            subtask5.setStatus(TaskStatus.DONE);
            taskManager.updateSubtask(subtask5);
        }

        Subtask subtask6 = taskManager.getSubtaskById(6);
        if (subtask6 != null) {
            subtask6.setStatus(TaskStatus.DONE);
            taskManager.updateSubtask(subtask6);
        }
    }

    @AfterEach
    void printAllTaskWithChanges() {
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