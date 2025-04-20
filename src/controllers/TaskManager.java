package controllers;

import classes.tasks.Task;
import java.util.HashMap;

public class TaskManager {

    private HashMap<Integer, Task> taskMaster;
    private Integer id;


    public TaskManager() {
        taskMaster = new HashMap<>();
        id = 0;
    }

    public HashMap<Integer, Task> getTaskMaster() {
        return taskMaster;
    }

    public void putTask(Task task) {
        taskMaster.put(id++, task);
    }
}
