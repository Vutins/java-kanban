package managers;

import classes.tasks.Epic;
import classes.tasks.Subtask;
import classes.tasks.Task;

import java.util.ArrayList;

public interface TaskManager {

    ArrayList<Task> getTasks();
    ArrayList<Epic> getEpics();
    ArrayList<Subtask> getSubtasksByEpic(Epic epic);
    ArrayList<Subtask> getSubtasks();


    void removeTasks();
    void removeEpics();
    void removeSubtasks(Epic epic);

    Task getTaskById(Integer id);
    Epic getEpicById(Integer id);
    Subtask getSubtaskById(Integer id);

    void addTask(Task task);
    void addEpic(Epic epic);
    void addSubtask(Subtask subtask);

    void updateTask(Task task);
    void updateEpic(Epic epic);
    void updateSubtask(Subtask subtask);

    void removeTaskById(Integer id);
    void removeEpicById(Integer id);
    void removeSubtaskById(Integer id);

    ArrayList<Task> getHistory();
}
