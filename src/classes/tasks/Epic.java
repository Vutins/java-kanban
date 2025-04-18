package classes.tasks;

import classes.enums.Class;
import java.util.HashMap;

public class Epic extends Task {

    private int id;
    private String title;
    private String description;
    private HashMap<Integer, Subtask> epicSubtasks;
    private Class typeClass;

    public Epic(String title, String description) {
        epicSubtasks = new HashMap<>();
    }

    @Override
    public void setTypeClass(Object obj) {   //подумать!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        this.typeClass = Class.EPIC;
        obj = typeClass;
    }

    public void addSubtask(Integer id, Subtask subtask) {
        epicSubtasks.put(id, subtask);
    }


}
