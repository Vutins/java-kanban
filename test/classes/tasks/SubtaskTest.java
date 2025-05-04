package classes.tasks;

import managers.Managers;
import managers.TaskManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SubtaskTest {

    TaskManager taskManager;
    Epic epic1;
    Subtask subtask1;

    @BeforeEach
    void startProgram() {
        taskManager = Managers.getDefault();

        epic1 = new Epic("epic1", "delau epic1");
        taskManager.addEpic(epic1);
        subtask1 = new Subtask("subtask1", "delau subtask1", epic1.getId());
        taskManager.addSubtask(subtask1);
    }

    @Test
    void shouldAddSubtaskAndFindById() {
        assertEquals(subtask1, taskManager.getSubtaskById(subtask1.getId()));
    }
}