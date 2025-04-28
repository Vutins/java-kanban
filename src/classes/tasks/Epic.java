package classes.tasks;

import classes.enums.TaskType;
import java.util.ArrayList;
import java.util.HashMap;

public class Epic extends Task {

    private HashMap<Integer, Subtask> epicSubtasks;

    public Epic(String title, String description) {
        super(title,description);
        epicSubtasks = new HashMap<>();
    }

    public ArrayList<Subtask> getSubtasks() {
        return new ArrayList<Subtask>(epicSubtasks.values());
    }

    public HashMap<Integer, Subtask> getEpicSubtasks() {
        return epicSubtasks;
    }

    @Override
    public TaskType getTaskClass() {
        return TaskType.EPIC;
    }
}
