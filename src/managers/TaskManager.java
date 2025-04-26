package managers;

import classes.enums.TaskType;
import classes.enums.TaskStatus;
import classes.tasks.Epic;
import classes.tasks.Subtask;
import classes.tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {

    private HashMap<Integer, Task> taskMaster;
    private HashMap<Integer, Epic> epicMaster;
    private HashMap<Integer, Subtask> subtaskMaster;
    private Integer id = 0;

    public TaskManager() {
        taskMaster = new HashMap<>();
        epicMaster = new HashMap<>();
        subtaskMaster = new HashMap<>();
    }

    public ArrayList<Task> getTasks() {
        ArrayList<Task> taskArrayList = new ArrayList<>();
        for (Task task : taskMaster.values()) {
                taskArrayList.add(task);
        }
        return taskArrayList;
    }

    public ArrayList<Epic> getEpics() {
        ArrayList<Epic> epicArrayList = new ArrayList<>();
        for (Epic epic : epicMaster.values()) {
                epicArrayList.add(epic);
        }
        return epicArrayList;
    }

    public ArrayList<Subtask> getSubtasks(Epic epic) {
        ArrayList<Subtask> subtaskArrayList = new ArrayList<>();
        for (Subtask subtask : epic.getEpicSubtasks().values()) {
                subtaskArrayList.add(subtask);
        }
        return subtaskArrayList;
    }

    public void removeTasks() {
        ArrayList<Integer> toRemoveTasks = new ArrayList<>();
        for (Task task : taskMaster.values()) {
                toRemoveTasks.add(task.getId());
        }

        for (Integer id : toRemoveTasks) {
            taskMaster.remove(id);
        }
    }

    public void removeEpics() {
        ArrayList<Integer> toRemoveEpics = new ArrayList<>();
        for (Epic epic : epicMaster.values()) {
                toRemoveEpics.add(epic.getId());
        }

        for (Integer id : toRemoveEpics) {
            epicMaster.remove(id);
        }
    }

    public void removeSubtasks(Epic epic) {
        ArrayList<Integer> toRemoveSubtasks = new ArrayList<>();
        for (Subtask subtask : subtaskMaster.values()) {
                toRemoveSubtasks.add(subtask.getId());
        }

        for (Integer id : toRemoveSubtasks) {
            subtaskMaster.remove(id);
        }
        epic.getEpicSubtasks().clear();
    }

    public Task getTaskById(Integer id) {
        return taskMaster.get(id);
    }

    public Epic getEpicById(Integer id) {
        return epicMaster.get(id);
    }

    public Subtask getSubtaskById(Integer id) {
        return subtaskMaster.get(id);
    }

    public void addTask(Task task) {
        task.setId(id++);
        taskMaster.put(task.getId(), task);
    }

    public void addEpic(Epic epic) {
        epic.setId(id++);
        epicMaster.put(epic.getId(), epic);
    }

    public void addSubtask(Epic epic, Subtask subtask) {
        subtask.setId(id++);
        subtaskMaster.put(subtask.getId(), subtask);
        epic.getEpicSubtasks().put(subtask.getId(), subtask);
    }

    public void updateTask(Integer id, Task task) {
        taskMaster.put(id, task);
    }

    public void updateEpic(Integer id, Epic epic) {
        epicMaster.put(id, epic);
    }

    public void updateSubtask(Integer id, Subtask subtask) {
        subtaskMaster.put(id, subtask);
        epicMaster.get(subtask.getEpicId()).getEpicSubtasks().put(id, subtask);

    }

    public void removeTaskById(Integer id) {
        taskMaster.remove(id);
    }

    public void removeEpicById(Integer id) {
        epicMaster.remove(id);
    }

    public void removeSubtaskById(Integer id) {
        epicMaster.get(subtaskMaster.get(id).getEpicId()).getEpicSubtasks().remove(id);
        subtaskMaster.remove(id);
    }

    public void checkStatus() {
        for(Epic epic : epicMaster.values()) {
            if (epic.getEpicSubtasks().isEmpty()) {
                epic.setStatus(TaskStatus.NEW);
            }

            boolean check = false;

            for (Subtask subtask : epic.getEpicSubtasks().values()) {
                if (subtask.getStatus() == TaskStatus.NEW) {
                    epic.setStatus(TaskStatus.NEW);
                } else {
                    epic.setStatus(TaskStatus.IN_PROGRESS);
                }

                if (subtask.getStatus() == TaskStatus.DONE) {
                    check = true;
                } else {
                    check = false;
                }
            }

            if(check) {
                epic.setStatus(TaskStatus.DONE);
            }
        }
    }
}


