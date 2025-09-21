package classes.tasks;

import classes.enums.TaskType;
import classes.enums.TaskStatus;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class Task {

    protected int id;
    protected String title;
    protected String description;
    protected TaskStatus status;
    protected Duration duration;
    protected LocalDateTime startTime;

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.status = TaskStatus.NEW;
    }

    public Task(String title, String description, TaskStatus status, LocalDateTime startTime, Duration duration) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.startTime = startTime;
        this.duration = duration;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
        setTime();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public TaskType getTaskClass() {
        return TaskType.TASK;
    }

    public LocalDateTime getEndTime() {
        if (startTime == null || duration == null) {
            return null;
        }
        return startTime.plus(duration);
    }

    public LocalDateTime getStartTime() {
        if (startTime == null) {
            return null;
        }
        return startTime;
    }

    public long getDuration() {
        if (duration == null) {
            return Duration.ZERO.toMinutes();
        } else {
            return duration.toMinutes();
        }
    }

    protected void setTime() {
       if (status.equals(TaskStatus.IN_PROGRESS)) {
            startTime = LocalDateTime.now();
       } else if (status.equals(TaskStatus.DONE)) {
           duration = Duration.between(startTime, LocalDateTime.now());
       }
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) return false;
        Task task = (Task) object;
        if (this.getId().equals(task.getId())) return true;
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", duration=" + (duration != null ? duration.toMinutes() : "null") +
                ", startTime=" + startTime +
                '}';
    }
}
