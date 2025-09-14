package managers;

import classes.enums.TaskStatus;
import classes.tasks.Epic;
import classes.tasks.Subtask;
import classes.tasks.Task;

public class CSVFormat {

    public static String toString(Task task) {
        return task.getId() + "," +
                task.getTaskClass() + "," +
                task.getTitle() + "," +
                task.getStatus() + "," +
                task.getDescription() + "," +
                System.lineSeparator();
    }

    public static String toStringSubtask(Subtask subtask) {
        return subtask.getId() + "," +
                subtask.getTaskClass() + "," +
                subtask.getTitle() + "," +
                subtask.getStatus() + "," +
                subtask.getDescription() + "," +
                subtask.getEpicId() + "," +
                System.lineSeparator();
    }

    public static Task fromString(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }

        String[] splitValue = value.split(",");
        if (splitValue[1].equals("TASK")) {
            Task task = new Task(splitValue[2], splitValue[4]);
            task.setId(Integer.parseInt(splitValue[0]));
            task.setStatus(TaskStatus.valueOf(splitValue[3]));
            return task;
        } else if (splitValue[1].equals("EPIC")) {
            Epic epic = new Epic(splitValue[2], splitValue[4]);
            epic.setId(Integer.parseInt(splitValue[0]));
            epic.setStatus(TaskStatus.valueOf(splitValue[3]));
            return epic;
        } else if (splitValue[1].equals("SUBTASK")) {
            Subtask subtask = new Subtask(splitValue[2], splitValue[4], Integer.parseInt(splitValue[5]));
            subtask.setId(Integer.parseInt(splitValue[0]));
            subtask.setStatus(TaskStatus.valueOf(splitValue[3]));
            return subtask;
        }
        return null;
    }
}
