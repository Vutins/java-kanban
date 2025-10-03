package classes.tasks;

import classes.enums.TaskType;
import classes.enums.TaskStatus;
import com.google.gson.annotations.SerializedName;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Objects;

public class Task {

    @SerializedName("id")
    protected int id;
    @SerializedName("title")
    protected String title;
    @SerializedName("description")
    protected String description;
    @SerializedName("status")
    protected TaskStatus status;
    @SerializedName("duration")
    protected Duration duration;
    @SerializedName("startTime")
    protected LocalDateTime startTime;

    public Task() {
        status = TaskStatus.NEW;
        startTime = LocalDateTime.now();
        duration = Duration.ofMinutes(5);
    }

    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.status = TaskStatus.NEW;
        startTime = LocalDateTime.now();
        duration = Duration.ofMinutes(5);
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

    public Duration getDuration() {
        if (duration == null) {
            return Duration.ZERO;
        } else {
            return duration;
        }
    }

    protected void setTime() {
       if (status.equals(TaskStatus.IN_PROGRESS)) {
            startTime = LocalDateTime.now();
       } else if (status.equals(TaskStatus.DONE)) {
           if (startTime != null) {
               duration = Duration.between(startTime, LocalDateTime.now());
           }
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
