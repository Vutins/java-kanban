package classes.tasks;

import classes.enums.TaskStatus;
import classes.enums.TaskType;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class Epic extends Task {

    private final HashMap<Integer, Subtask> epicSubtasks;
    private LocalDateTime endTime;

    public Epic(String title, String description) {
        super(title,description);
        epicSubtasks = new HashMap<>();
    }

    public Epic(String title, String description, TaskStatus status, LocalDateTime startTime, Duration duration) {
        super(title, description, status, startTime, duration);
        epicSubtasks = new HashMap<>();
    }

    public ArrayList<Subtask> getSubtasks() {
        return new ArrayList<Subtask>(epicSubtasks.values());
    }

    public HashMap<Integer, Subtask> getEpicSubtasks() {
        return epicSubtasks;
    }

    @Override
    public TaskType getTaskClass() {
        return TaskType.EPIC;
    }

    @Override
    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    @Override
    public long getDuration() {
        if (duration == null || endTime == null) {
            return Duration.ZERO.toMinutes();
        } else {
            return Duration.between(startTime, endTime).toMinutes();
        }
    }
}
