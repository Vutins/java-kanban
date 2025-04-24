import classes.tasks.Epic;
import classes.tasks.Subtask;
import classes.tasks.Task;
import controllers.TaskManager;


public class Main {

    public static void main(String[] args) {


        TaskManager taskManager = new TaskManager();

        Task task = new Task("переезд", "очень быстрый переезд");
        taskManager.putTask(task);
        Epic epic = new Epic("домашка", "сделать дз");
        taskManager.putEpic(epic);
        Subtask subtask = new Subtask("алгебра", "второе задание");
        taskManager.putSubtask(epic, subtask);

    }
}
