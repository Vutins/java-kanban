package classes.tasks;

import managers.HistoryManager;
import managers.Managers;
import managers.TaskManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    TaskManager taskManager;
    Task task1;
    Task task2;
    Epic epic1;
    static HistoryManager historyManager;

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
    }

    @Test
    void shouldEqualsById() {
        assertEquals(0, task1.getId());
        assertNotEquals(task1.getId(), task2.getId());
    }

    @Test
    void shouldEqualsHeirsById() {
        epic1.setId(task1.getId());
        assertEquals(task1, epic1);
    }

    @Test
    void shouldAddTaskAndFindById() {
        Task task3 = new Task("task3", "delau task3");
        taskManager.addTask(task3);
        assertEquals(task3, taskManager.getTaskById(task3.getId()));
    }

    @Test
    void shouldDontConflictTaskInTaskClassAndTaskInTaskManager() {
        assertEquals(taskManager.getTaskById(task1.getId()).getId(), task1.getId());
    }

    @Test
    void allLineShouldBeEqualsInTaskManagersAndInClass() {
        assertEquals(taskManager.getTaskById(task1.getId()).getId(), task1.id);
        assertEquals(taskManager.getTaskById(task1.getId()).getTitle(), task1.getTitle());
        assertEquals(taskManager.getTaskById(task1.getId()).getTaskClass(), task1.getTaskClass());
        assertEquals(taskManager.getTaskById(task1.getId()).getDescription(), task1.getDescription());
        assertEquals(taskManager.getTaskById(task1.getId()).getStatus(), task1.getStatus());
    }

    @Test
    void shouldSavePreviousVersionTaskInHistoryManager() {
        taskManager.getTaskById(task1.getId());
        taskManager.getTaskById(task2.getId());
        ArrayList<Task> history = new ArrayList<>(taskManager.getHistory());
        assertNotNull(history, "история пуста");
        assertEquals(2, history.size());
        assertEquals(task1, history.getFirst());
    }
}