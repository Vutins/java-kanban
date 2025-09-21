package managers;

import classes.enums.TaskStatus;
import classes.tasks.Epic;
import classes.tasks.Subtask;
import classes.tasks.Task;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.time.LocalDateTime;

public class TimeTests {

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
        Subtask subtask2 = new Subtask("subtask2", "delau subtask2", epic1.getId());
        taskManager.addSubtask(subtask2);
        Subtask subtask3 = new Subtask("subtask3", "delau subtask3", epic1.getId());
        taskManager.addSubtask(subtask3);

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
        taskManager = new FileBackedTaskManager(Path.of(String.valueOf(File.createTempFile("data", ".txt"))));
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
    void shouldChangeTasksStatusAndSaveStartTime() throws IOException {
        taskManager = new FileBackedTaskManager(Path.of(String.valueOf(File.createTempFile("data", ".txt"))));

        taskManager.getTaskById(1).setStatus(TaskStatus.IN_PROGRESS);
        taskManager.updateTask(taskManager.getTaskById(1));

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
    void updateSubtaskAndShouldSetStartTimeToEpic() throws IOException {
        taskManager = new FileBackedTaskManager(Path.of(String.valueOf(File.createTempFile("data", ".txt"))));

        taskManager.getTaskById(1).setStatus(TaskStatus.IN_PROGRESS);
        taskManager.updateTask(taskManager.getTaskById(1));

        taskManager.getSubtaskById(4).setStatus(TaskStatus.IN_PROGRESS);
        taskManager.updateSubtask(taskManager.getSubtaskById(4));

        taskManager.getSubtaskById(5).setStatus(TaskStatus.IN_PROGRESS);
        taskManager.updateSubtask(taskManager.getSubtaskById(5));

        taskManager.getSubtaskById(6).setStatus(TaskStatus.IN_PROGRESS);
        taskManager.updateSubtask(taskManager.getSubtaskById(6));

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
    void ShouldSetStartTimeToEpic() throws IOException{
        taskManager = new FileBackedTaskManager(Path.of(String.valueOf(File.createTempFile("data", ".txt"))));

        taskManager.getSubtaskById(4).setStatus(TaskStatus.DONE);
        taskManager.updateSubtask(taskManager.getSubtaskById(4));

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
    void shouldSetDurationToEpic() throws IOException {
        taskManager = new FileBackedTaskManager(Path.of(String.valueOf(File.createTempFile("data", ".txt"))));

        taskManager.getSubtaskById(5).setStatus(TaskStatus.DONE);
        taskManager.updateSubtask(taskManager.getSubtaskById(5));

        taskManager.getSubtaskById(6).setStatus(TaskStatus.DONE);
        taskManager.updateSubtask(taskManager.getSubtaskById(6));

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
