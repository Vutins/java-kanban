import classes.enums.TaskStatus;
import classes.tasks.Epic;
import classes.tasks.Subtask;
import classes.tasks.Task;
import managers.InMemoryTaskManager;
import managers.TaskManager;


public class Main {

    public static void main(String[] args) {

        InMemoryTaskManager taskManager = new InMemoryTaskManager();

        Epic epic1 = new Epic("Эпик 1","Нужно сделать");
        taskManager.addEpic(epic1);
        System.out.println(epic1.toString());

        Subtask subtask1 = new Subtask("Subtask1 создания ",
                "Написать что то ", epic1.getId());
        taskManager.addSubtask(subtask1);
        System.out.println(subtask1.toString());

//        Subtask subtask2 = new Subtask("Subtask2 создания ",
//                "Написать что то ", epic1.getId());
//        taskManager.addSubtask(subtask2);
//
//        Task task1 = new Task("Таск1", "купить машину");
//        taskManager.addTask(task1);
//
//        System.out.println(epic1);
//
//        subtask1.setStatus(TaskStatus.IN_PROGRESS);
//        taskManager.updateSubtask(subtask1);
//        System.out.println(epic1);
//
//        subtask2.setStatus(TaskStatus.DONE);
//        taskManager.updateSubtask(subtask2);
//        System.out.println(epic1);
//
//        subtask1.setStatus(TaskStatus.DONE);
//        taskManager.updateSubtask(subtask1);
//        System.out.println(epic1);
//
//       taskManager.getEpicById(0);
//       taskManager.getTaskById(3);
//
//        printAllTasks(taskManager);
//    }
//
//   private static void printAllTasks(TaskManager manager) {
//        System.out.println("Задачи:");
//        for (Task task : manager.getTasks()) {
//            System.out.println(task);
//        }
//
//        System.out.println("Эпики:");
//        for (Epic epic : manager.getEpics()) {
//            System.out.println(epic);
//
//            for (Subtask subtask : manager.getSubtasksByEpic(epic)) {
//                System.out.println("--> " + subtask);
//            }
//        }
//
//        System.out.println("Подзадачи:");
//        for (Subtask subtask : manager.getSubtasks()) {
//            System.out.println(subtask);
//        }
//
//        System.out.println("История:");
//        for (Task task : manager.getHistory()) {
//            System.out.println(task);
//        }
    }
}
