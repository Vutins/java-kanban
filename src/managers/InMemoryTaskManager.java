package managers;

import classes.enums.TaskStatus;
import classes.tasks.Epic;
import classes.tasks.Subtask;
import classes.tasks.Task;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class InMemoryTaskManager implements TaskManager {

    private HashMap<Integer, Task> taskMaster;
    private HashMap<Integer, Epic> epicMaster;
    private HashMap<Integer, Subtask> subtaskMaster;
    private Integer id = 1;
    private HistoryManager historyManager;
    private TreeSet<Task> prioritizedTasks;

    public InMemoryTaskManager() {
        taskMaster = new HashMap<>();
        epicMaster = new HashMap<>();
        subtaskMaster = new HashMap<>();
        historyManager = Managers.getDefaultHistory();
        settingsPrioritizedTasks();
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

        if (task.getStartTime() != null) {
            prioritizedTasks.add(task);
        }
    }

    @Override
    public void addEpic(Epic epic) {
        epic.setId(id++);
        epicMaster.put(epic.getId(), epic);

        if (epic.getStartTime() != null) {
            prioritizedTasks.add(epic);
        }
    }

    @Override
    public void addSubtask(Subtask subtask) {
        Integer epicId = subtask.getEpicId();
        Epic epic = epicMaster.get(epicId);

        if (epic == null) {
            throw new IllegalArgumentException("Epic with id " + epicId + " not found");
        }

        if (epic.getEpicSubtasks() == null) {
            System.out.println("WARNING: epicSubtasks was null for epic id " + epicId);
        }
        subtask.setId(id++);
        subtaskMaster.put(subtask.getId(), subtask);
        epic.getEpicSubtasks().put(subtask.getId(), subtask);

        if (epic.getSubtasks().size() == 1) {
            epic.setStartTime(subtask.getStartTime());
        }

        if (epicMaster.get(subtask.getEpicId()).getSubtasks().size() == 1) {
                epicMaster.get(subtask.getEpicId()).setStartTime(subtask.getStartTime());
        }
        checkStatus();
        if (subtask.getStartTime() != null) {
            prioritizedTasks.add(subtask);
        }
    }

    @Override
    public void updateTask(Task task) {
        taskMaster.put(task.getId(), task);

        if (task.getStartTime() != null) {
            prioritizedTasks.add(task);
        }
    }

    @Override
    public void updateEpic(Epic epic) {
        epicMaster.put(epic.getId(), epic);

        if (epic.getStartTime() != null) {
            prioritizedTasks.add(epic);
        }
    }

    @Override
    public void updateSubtask(Subtask subtask) {
        subtaskMaster.put(subtask.getId(), subtask);
        epicMaster.get(subtask.getEpicId()).getEpicSubtasks().put(subtask.getId(), subtask);
        checkStatus();

        if (subtask.getStartTime() != null) {
            prioritizedTasks.add(subtask);
        }
    }

    @Override
    public void removeTaskById(Integer id) {
        if (taskMaster.get(id) != null) {
            prioritizedTasks.remove(taskMaster.get(id));
        }
        taskMaster.remove(id);
        historyManager.remove(id);
    }

    @Override
    public void removeEpicById(Integer id) {
        if (epicMaster.get(id) != null) {
            prioritizedTasks.remove(epicMaster.get(id));
        }
        epicMaster.remove(id);
        historyManager.remove(id);
    }

    @Override
    public void removeSubtaskById(Integer id) throws IOException {
        Subtask subtask = subtaskMaster.get(id);
        if (subtask != null) {
            prioritizedTasks.remove(subtask);

            Epic epic = epicMaster.get(subtask.getEpicId());
            if (epic != null && epic.getEpicSubtasks() != null) {
                epic.getEpicSubtasks().remove(id);
            }

            subtaskMaster.remove(id);
            historyManager.remove(id);
            checkStatus();
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
                    epic.setStartTime(subtask.getStartTime());
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
                epic.setEndTime(LocalDateTime.now());
            }
        }
    }

    public TreeSet<Task> getPrioritizedTasks() {
        return prioritizedTasks;
    }

    protected void settingsPrioritizedTasks() {
        prioritizedTasks = new TreeSet<>(comparatorStartTime());

        for (Task task : taskMaster.values()) {
            if (task.getStartTime() != null) {
                prioritizedTasks.add(task);
            }
        }
        for (Epic epic : epicMaster.values()) {
            if (epic.getStartTime() != null) {
                prioritizedTasks.add(epic);
            }
        }
        for (Subtask subtask : subtaskMaster.values()) {
            if (subtask.getStartTime() != null) {
                prioritizedTasks.add(subtask);
            }
        }
    }

    private Comparator<Task> comparatorStartTime() {
        Comparator<Task> comparator = Comparator.comparing(
                task -> task.getStartTime(),
                Comparator.nullsFirst(Comparator.naturalOrder()));
        return comparator;
    }

    private static boolean checkIntersectionTasks(Task task1, Task task2) {
        return !task1.getEndTime().isBefore(task2.getStartTime()) &&
                !task1.getStartTime().isAfter(task2.getEndTime());
    }
}


