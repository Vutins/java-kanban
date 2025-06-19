package classes.tasks;

import classes.enums.TaskType;
import classes.enums.TaskStatus;

import java.util.Objects;

public class Task {

    protected int id;
    protected String title;
    protected String description;
    protected TaskStatus status;


    public Task(String title, String description) {
        this.title = title;
        this.description = description;
        this.status = TaskStatus.NEW;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
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

    @Override
    public boolean equals(Object object) {
        if (object == null) return false;
        Task task = (Task) object;
        if (this.getId() == task.getId()) return true;
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return getTaskClass() + "{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
