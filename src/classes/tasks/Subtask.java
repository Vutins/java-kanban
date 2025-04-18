package classes.tasks;

import classes.enums.Class;

public class Subtask extends Task {

    private int id;
    private String title;
    private String description;
    private Class typeClass;

    @Override
    public void setTypeClass(Object obj) {   //подумать!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        this.typeClass = Class.TASK;
        obj = typeClass;
    }

}
