package controllers;

import classes.tasks.Task;
import java.util.ArrayList;

public class TaskManager {

    private ArrayList<Task> taskMaster;
    private Integer id;
    private Task task;

    public TaskManager() {
        taskMaster = new ArrayList<>();
        id = 0;
        task = new Task();
    }

    public ArrayList<Task> getTaskMaster() {
        return taskMaster;
    }

    public void CountId() {
        task.setId(id);
        id++;
    }
}
