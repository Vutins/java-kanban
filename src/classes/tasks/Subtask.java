package classes.tasks;

import classes.enums.TaskType;

public class Subtask extends Task {

    private Integer epicId;

    public Subtask(String title, String description, Integer epicId) {
        super(title, description);
        this.epicId = epicId;
    }

    public Integer getEpicId() {
        return epicId;
    }

    @Override
    public TaskType getTaskClass() {
        return TaskType.SUBTASK;
    }
}
