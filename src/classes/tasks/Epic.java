package classes.tasks;

import classes.enums.Class;
import classes.enums.Status;

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
    public Class getTaskClass() {
        return Class.EPIC;
    }

    public void checkStatus() {
        if(epicSubtasks.isEmpty()) {
            setStatus(Status.NEW);
        }

        boolean check = false;

        for (Subtask subtask : epicSubtasks.values()) {
            if (subtask.getStatus() == Status.NEW) {
                setStatus(Status.NEW);
            } else {
                setStatus(Status.IN_PROGRESS);
            }

            if (subtask.getStatus() == Status.DONE) {
                check = true;
            } else {
                check = false;
            }
        }

        if(check) {
            setStatus(Status.DONE);
        }
    }
}
