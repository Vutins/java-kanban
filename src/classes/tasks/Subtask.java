package classes.tasks;

import classes.enums.Class;

public class Subtask extends Task {

    private Integer motherId;

    public Subtask(String title, String description) {
        super(title, description);
    }

    public void setMotherId(Integer id) {
        this.motherId = id;
    }

    public Integer getMotherId() {
        return motherId;
    }

    @Override
    public Class getTaskClass() {
        return Class.SUBTASK;
    }
}
