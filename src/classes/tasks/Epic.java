package classes.tasks;

import classes.enums.Class;
import java.util.HashMap;

public class Epic extends Task {

    private HashMap<Integer, Subtask> epicSubtasks;

    public Epic(String title, String description) {
        super(title,description);
        epicSubtasks = new HashMap<>();
    }

    public void putSubtask(Subtask subtask) {
        epicSubtasks.put(getId(), subtask);
    }

    public HashMap<Integer, Subtask> getEpicSubtasks() {
        return epicSubtasks;
    }

    @Override
    public Class getTaskClass() {
        return Class.EPIC;
    }
}
