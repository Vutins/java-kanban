package classes.tasks;

import managers.HistoryManager;
import managers.Managers;
import managers.TaskManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EpicTest {

    TaskManager taskManager;
    Epic epic1;
    Epic epic2;
    Epic epic3;
    Subtask subtask1;

    @BeforeEach
    void startProgram() {
        taskManager = Managers.getDefault();

        epic1 = new Epic("epic1", "delau epic1");
        taskManager.addEpic(epic1);
        epic2 = new Epic("epic2", "delau epic2");
        taskManager.addEpic(epic2);
        epic3 = new Epic("epic3", "delau epic3");
        taskManager.addEpic(epic3);
        subtask1 = new Subtask("subtask1", "delau subtask1", epic1.getId());

    }

    @Test
    void shouldNotAddEpicInEpicList() {
        Subtask subtask2 = new Subtask("subtask2", "delau subtask2", epic1.getId());
        taskManager.addSubtask(subtask2);
        //помогите, пожалуйста, не знаю как реализовать этот тест - "проверьте, что объект Epic нельзя добавить в самого себя в виде подзадачи;"
    }

    @Test
    void shouldReturnWorkManagers() {
        HistoryManager historyManager = Managers.getDefaultHistory();
        historyManager.add(epic1);
        assertEquals(epic1, historyManager.getHistory().get(0));
    }

    @Test
    void shouldAddEpicAndFindById() {
        Epic epic4 = new Epic("epic4", "delau epic4");
        taskManager.addEpic(epic4);
        assertEquals(epic4, taskManager.getEpicById(epic4.getId()));
    }
}