package classes.tasks;

import classes.enums.Status;
import classes.enums.Class;

public class Task {

    private int id;
    private String title;
    private String description;
     private Status status;
     private Class typeClass;

    public Task() {
        this.status = Status.NEW;
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

    public Status getStatus() {
        return status;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
    
    public void setTypeClass(Object obj) {   //подумать!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        this.typeClass = Class.TASK;
        obj = typeClass;
    }

















    






}
