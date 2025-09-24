package managers;

import classes.enums.TaskStatus;
import classes.tasks.Epic;
import classes.tasks.Subtask;
import classes.tasks.Task;

import java.time.Duration;
import java.time.LocalDateTime;

public class CSVFormat {

    public static String toString(Task task) {
        return task.getId() + "," +
                task.getTaskClass() + "," +
                task.getTitle() + "," +
                task.getStatus() + "," +
                task.getDescription() + "," +
                task.getStartTime() + "," +
                task.getDuration() +
                System.lineSeparator();
    }

    public static String toStringSubtask(Subtask subtask) {
        return subtask.getId() + "," +
                subtask.getTaskClass() + "," +
                subtask.getTitle() + "," +
                subtask.getStatus() + "," +
                subtask.getDescription() + "," +
                subtask.getEpicId() + "," +
                subtask.getStartTime() + "," +
                subtask.getDuration() +
                System.lineSeparator();
    }

    public static Task fromString(String value) {
        if (value == null || value.trim().isEmpty() || "null".equals(value)) {
            return null;
        }

        String[] splitValue = value.split(",");

        if (splitValue[1].equals("TASK")) {
            LocalDateTime startTime = !"null".equals(splitValue[5]) ? parseDateTimeSafe(splitValue[5]) : null;
            Duration duration = !"null".equals(splitValue[6]) ? parseDurationSafe(splitValue[6]) : null;

            Task task = new Task(splitValue[2], splitValue[4], TaskStatus.valueOf(splitValue[3]),
                    startTime, duration);
            task.setId(Integer.parseInt(splitValue[0]));
            return task;
        } else if (splitValue[1].equals("EPIC")) {
            LocalDateTime startTime = !"null".equals(splitValue[5]) ? parseDateTimeSafe(splitValue[5]) : null;
            Duration duration = !"null".equals(splitValue[6]) ? parseDurationSafe(splitValue[6]) : null;

            Epic epic = new Epic(splitValue[2], splitValue[4], TaskStatus.valueOf(splitValue[3]),
                    startTime, duration);
            epic.setId(Integer.parseInt(splitValue[0]));
            return epic;
        } else if (splitValue[1].equals("SUBTASK")) {
            return fromStringSubtask(splitValue);
        }
        return null;
    }

    private static Subtask fromStringSubtask(String[] splitValue) {
        LocalDateTime startTime = !"null".equals(splitValue[6]) ? parseDateTimeSafe(splitValue[6]) : null;
        Duration duration = !"null".equals(splitValue[7]) ? parseDurationSafe(splitValue[7]) : null;

        Subtask subtask = new Subtask(splitValue[2], splitValue[4], TaskStatus.valueOf(splitValue[3]),
                Integer.parseInt(splitValue[5]), startTime, duration);
        subtask.setId(Integer.parseInt(splitValue[0]));
        return subtask;
    }

    private static LocalDateTime parseDateTimeSafe(String value) {
        if (value == null || value.equals("null") || value.trim().isEmpty()) {
            return null;
        }
        return LocalDateTime.parse(value);
    }

    private static Duration parseDurationSafe(String value) {
        if (value == null || value.equals("null") || value.trim().isEmpty()) {
            return null;
        }
        long minutes = Long.parseLong(value);
        return Duration.ofMinutes(minutes);
    }
}
