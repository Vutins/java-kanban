import classes.tasks.Epic;
import classes.tasks.Subtask;
import classes.tasks.Task;
import controllers.TaskManager;

import java.util.Map;
import java.util.Set;

public class Main {

    public static void main(String[] args) {


        TaskManager taskManager = new TaskManager();

        Task task = new Task("переезд", "очень быстрый переезд");
        taskManager.putTask(task);
        Epic epic = new Epic("домашка", "сделать дз");
        taskManager.putTask(epic);
        Subtask subtask = new Subtask("алгебра", "второе задание");
        epic.putSubtask(subtask);

        Set<Map.Entry<Integer, Task>> entrySet = taskManager.getTaskMaster().entrySet();

        for (Map.Entry<Integer, Task> entry : entrySet) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }

    }
}
