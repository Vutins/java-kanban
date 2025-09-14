package managers;
import classes.tasks.Epic;
import classes.tasks.Subtask;
import classes.tasks.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class InMemoryHistoryManagerTest {

    TaskManager taskManager;
    Task task1;
    Task task2;
    Epic epic1;
    Subtask subtask1;
    HistoryManager historyManager;

    @BeforeEach
    void startProgram() {
        taskManager = Managers.getDefault();
        historyManager = Managers.getDefaultHistory();

        task1 = new Task("delo1", "delau delo1");
        taskManager.addTask(task1);
        task2 = new Task("delo2", "delau delo2");
        taskManager.addTask(task2);
        epic1 = new Epic("epic1", "delau epic1");
        taskManager.addEpic(epic1);
        subtask1 = new Subtask("subtask1", "delau subtask1", epic1.getId());
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
    void shouldNotShowReplayOfTask() {
        taskManager.getTaskById(task1.getId());
        taskManager.getTaskById(task1.getId());

        taskManager.getTaskById(task2.getId());
        taskManager.getTaskById(task2.getId());

        taskManager.getEpicById(epic1.getId());
        taskManager.getEpicById(epic1.getId());

        taskManager.getSubtaskById(subtask1.getId());
        taskManager.getSubtaskById(subtask1.getId());
        taskManager.getSubtaskById(subtask1.getId());


        System.out.println("История:");
        for (Task task : taskManager.getHistory()) {
            System.out.println(task);
        }
    }

    @Test
    void shouldDeleteTaskFromHistoryMapById() throws IOException {
        taskManager.getTaskById(task1.getId());
        taskManager.getTaskById(task2.getId());
        taskManager.getEpicById(epic1.getId());
        taskManager.getSubtaskById(subtask1.getId());

        System.out.println("История до очистки:");
        for (Task task : taskManager.getHistory()) {
            System.out.println(task);
        }

        taskManager.removeTaskById(task1.getId());
        taskManager.removeSubtaskById(subtask1.getId());
        taskManager.removeEpicById(epic1.getId());

        System.out.println("История после очистки:");
        for (Task task : taskManager.getHistory()) {
            System.out.println(task);
        }
    }
}
