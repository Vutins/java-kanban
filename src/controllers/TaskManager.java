package controllers;

import classes.enums.Class;
import classes.tasks.Epic;
import classes.tasks.Subtask;
import classes.tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {

    private HashMap<Integer, Task> taskMaster;
    private Integer id = 0;

    public TaskManager() {
        taskMaster = new HashMap<>();
    }

    public ArrayList<Task> getTasks() {
        ArrayList<Task> taskArrayList = new ArrayList<>();
        for (Task task : taskMaster.values()) {
            if (task.getTaskClass() == Class.TASK) {
                taskArrayList.add(task);
            }
        }
        return taskArrayList;
    }

    public ArrayList<Epic> getEpics() {
        ArrayList<Epic> epicArrayList = new ArrayList<>();
        for (Task epic : taskMaster.values()) {
            if (epic.getTaskClass() == Class.EPIC) {
                epicArrayList.add((Epic) epic);
            }
        }
        return epicArrayList;
    }

    public ArrayList<Subtask> getSubtasks() {
        ArrayList<Subtask> subtaskArrayList = new ArrayList<>();
        for (Task subtask : taskMaster.values()) {
            if (subtask.getTaskClass() == Class.SUBTASK) {
                subtaskArrayList.add((Subtask) subtask);
            }
        }
        return subtaskArrayList;
    }

    public void removeTasks() {
        ArrayList<Integer> toRemoveTasks = new ArrayList<>();
        for (Task task : taskMaster.values()) {
            if (task.getTaskClass() == Class.TASK) {
                toRemoveTasks.add(task.getId());
            }
        }

        for (Integer id : toRemoveTasks) {
            taskMaster.remove(id);
        }
    }

    public void removeEpics() {
        ArrayList<Integer> toRemoveEpics = new ArrayList<>();
        for (Task epic : taskMaster.values()) {
            if (epic.getTaskClass() == Class.EPIC) {
                toRemoveEpics.add(epic.getId());
            }
        }

        for (Integer id : toRemoveEpics) {
            taskMaster.remove(id);
        }
    }

    public void removeSubtasks(Epic epic) {
        ArrayList<Integer> toRemoveSubtasks = new ArrayList<>();
        for (Task subtask : taskMaster.values()) {
            if (subtask.getTaskClass() == Class.EPIC) {
                toRemoveSubtasks.add(subtask.getId());
            }
        }

        for (Integer id : toRemoveSubtasks) {
            taskMaster.remove(id);
        }
        epic.getSubtasks().clear();
    }

    public Task getAllTaskById(Integer id) {
        return taskMaster.get(id);
    }

    public void putTask(Task task) {
        task.setId(id++);
        taskMaster.put(task.getId(), task);
    }

    public void putEpic(Epic epic) {
        epic.setId(id++);
        taskMaster.put(epic.getId(), epic);
    }

    public void putSubtask(Epic epic, Subtask subtask) {
        subtask.setId(id++);
        taskMaster.put(subtask.getId(), subtask);
        epic.getEpicSubtasks().put(subtask.getId(), subtask);
        subtask.setMotherId(epic.getId());
    }

    public void updateTask(Integer id, Task task) {
        taskMaster.put(id, task);
    }

    public void updateEpic(Integer id, Epic epic) {
        taskMaster.put(id, epic);
    }

    public void updateSubtask(Integer id, Subtask subtask) {
        taskMaster.put(id, subtask);
        ((Epic) taskMaster.get(subtask.getMotherId())).getEpicSubtasks().put(id, subtask);

    }

    public void removeTaskById(Integer id) {
        if (taskMaster.get(id).getTaskClass() == Class.SUBTASK) {
            ((Epic) taskMaster.get(((Subtask) taskMaster.get(id)).getMotherId())).getEpicSubtasks().remove(id);
        }
        taskMaster.remove(id);
    }
}


