package classes.tasks;

import classes.enums.Class;

public class Subtask extends Task {

    public Subtask(String title, String description) {
        super(title, description);
    }

    @Override
    public Class getTaskClass() {
        return Class.SUBTASK;
    }

}
