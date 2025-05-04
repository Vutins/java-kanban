package managers;

import classes.tasks.Task;

import java.util.ArrayList;

public class InMemoryHistoryManager implements HistoryManager {

    private ArrayList<Task> historyOfTasks;

    public InMemoryHistoryManager() {
        historyOfTasks = new ArrayList<>();
    }

    @Override
    public void add(Task task) {
        historyOfTasks.add(task);
    }

    @Override
    public ArrayList<Task> getHistory() {
        if (historyOfTasks.size() > 10) {
            for (int i = 0; i < historyOfTasks.size(); i++) {
                historyOfTasks.remove(i);
                if (historyOfTasks.size() <= 10) {
                    break;
                }
            }
        }
        return historyOfTasks;
    }
}
