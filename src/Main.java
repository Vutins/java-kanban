import classes.enums.TaskStatus;
import classes.tasks.Epic;
import classes.tasks.Subtask;
import classes.tasks.Task;
import managers.TaskManager;


public class Main {

    public static void main(String[] args) {

        TaskManager taskManager = new TaskManager();

        Epic epic1 = new Epic("Эпик 1","Нужно сделать");
        taskManager.addEpic(epic1);

        Subtask subtask1 = new Subtask("Subtask1 создания ",
                "Написать что то ", epic1.getId());
        taskManager.addSubtask(subtask1);

        Subtask subtask2 = new Subtask("Subtask2 создания ",
                "Написать что то ", epic1.getId());
        taskManager.addSubtask(subtask2);

        System.out.println(epic1);

        subtask1.setStatus(TaskStatus.IN_PROGRESS);
        taskManager.updateSubtask(subtask1);
        System.out.println(epic1);

        subtask2.setStatus(TaskStatus.DONE);
        taskManager.updateSubtask(subtask2);
        System.out.println(epic1);

        subtask1.setStatus(TaskStatus.DONE);
        taskManager.updateSubtask(subtask1);
        System.out.println(epic1);
    }
}
