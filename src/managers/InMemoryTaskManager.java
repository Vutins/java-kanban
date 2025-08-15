package managers;

import classes.enums.TaskStatus;
import classes.tasks.Epic;
import classes.tasks.Subtask;
import classes.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTaskManager implements TaskManager {

    private HashMap<Integer, Task> taskMaster;
    private HashMap<Integer, Epic> epicMaster;
    private HashMap<Integer, Subtask> subtaskMaster;
    private Integer id = 0;
    private HistoryManager historyManager;

    public InMemoryTaskManager() {
        taskMaster = new HashMap<>();
        epicMaster = new HashMap<>();
        subtaskMaster = new HashMap<>();
        historyManager = Managers.getDefaultHistory();
    }

    @Override
    public ArrayList<Task> getTasks() {
        ArrayList<Task> taskArrayList = new ArrayList<>();
        for (Task task : taskMaster.values()) {
                taskArrayList.add(task);
        }
        return taskArrayList;
    }

    @Override
    public ArrayList<Epic> getEpics() {
        ArrayList<Epic> epicArrayList = new ArrayList<>();
        for (Epic epic : epicMaster.values()) {
                epicArrayList.add(epic);
        }
        return epicArrayList;
    }

    @Override
    public ArrayList<Subtask> getSubtasksByEpic(Epic epic) {
        ArrayList<Subtask> subtaskArrayListByEpic = new ArrayList<>();
        for (Subtask subtask : epic.getEpicSubtasks().values()) {
                subtaskArrayListByEpic.add(subtask);
        }
        return subtaskArrayListByEpic;
    }

    @Override
    public ArrayList<Subtask> getSubtasks() {
        ArrayList<Subtask> subtaskArrayList = new ArrayList<>();
        for (Subtask subtask : subtaskMaster.values()) {
            subtaskArrayList.add(subtask);
        }
        return subtaskArrayList;
    }

    @Override
    public void removeTasks() {
        ArrayList<Integer> toRemoveTasks = new ArrayList<>();
        for (Task task : taskMaster.values()) {
                toRemoveTasks.add(task.getId());
        }

        for (Integer id : toRemoveTasks) {
            taskMaster.remove(id);
        }
    }

    @Override
    public void removeEpics() {
        ArrayList<Integer> toRemoveEpics = new ArrayList<>();
        for (Epic epic : epicMaster.values()) {
                toRemoveEpics.add(epic.getId());
        }

        for (Integer id : toRemoveEpics) {
            epicMaster.remove(id);
        }
    }

    @Override
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

    @Override
    public Task getTaskById(Integer id) {
        historyManager.add(taskMaster.get(id));
        return taskMaster.get(id);
    }

    @Override
    public Epic getEpicById(Integer id) {
        historyManager.add(epicMaster.get(id));
        return epicMaster.get(id);
    }

    @Override
    public Subtask getSubtaskById(Integer id) {
        historyManager.add(subtaskMaster.get(id));
        return subtaskMaster.get(id);
    }

    @Override
    public void addTask(Task task) {
        task.setId(id++);
        taskMaster.put(task.getId(), task);
    }

    @Override
    public void addEpic(Epic epic) {
        epic.setId(id++);
        epicMaster.put(epic.getId(), epic);
    }

    @Override
    public void addSubtask(Subtask subtask) {
        subtask.setId(id++);
        subtaskMaster.put(subtask.getId(), subtask);
        if (!subtask.getId().equals(subtask.getEpicId())) {
            epicMaster.get(subtask.getEpicId()).getEpicSubtasks().put(subtask.getId(), subtask);
        }
    }

    @Override
    public void updateTask(Task task) {
        taskMaster.put(task.getId(), task);
    }

    @Override
    public void updateEpic(Epic epic) {
        epicMaster.put(epic.getId(), epic);
    }

    @Override
    public void updateSubtask(Subtask subtask) {
        subtaskMaster.put(subtask.getId(), subtask);
        epicMaster.get(subtask.getEpicId()).getEpicSubtasks().put(subtask.getId(), subtask);
        checkStatus();
    }

    @Override
    public void removeTaskById(Integer id) {
        taskMaster.remove(id);
        historyManager.remove(id);
    }

    @Override
    public void removeEpicById(Integer id) {
        epicMaster.remove(id);
        historyManager.remove(id);
    }

    @Override
    public void removeSubtaskById(Integer id) throws IOException {
        if (taskMaster.get(id) != null) {
            epicMaster.get(subtaskMaster.get(id).getEpicId()).getEpicSubtasks().remove(id);
            historyManager.remove(id);
            subtaskMaster.remove(id);
        }
    }

    @Override
    public List<Task> getHistory() {
        return  historyManager.getHistory();
    }

    private void checkStatus() {
        for (Epic epic : epicMaster.values()) {
            if (epic.getEpicSubtasks().isEmpty()) {
                epic.setStatus(TaskStatus.NEW);
            }

            boolean check = false;

            for (Subtask subtask : epic.getEpicSubtasks().values()) {
                if (subtask.getStatus() == TaskStatus.NEW) {
                    epic.setStatus(TaskStatus.NEW);
                } else {
                    epic.setStatus(TaskStatus.IN_PROGRESS);
                    break;
                }
            }

            for (Subtask subtask : epic.getEpicSubtasks().values()) {
                if (subtask.getStatus() == TaskStatus.DONE) {
                    check = true;
                } else {
                    check = false;
                    break;
                }
            }

            if (check) {
                epic.setStatus(TaskStatus.DONE);
            }
        }
    }
}


