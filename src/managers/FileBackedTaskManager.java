package managers;

import classes.enums.TaskStatus;
import classes.tasks.Epic;
import classes.tasks.Subtask;
import classes.tasks.Task;
import exception.ManagerSaveException;

import java.io.*;
import java.nio.file.Path;

public class FileBackedTaskManager extends InMemoryTaskManager {

    Path saveFile;


    FileBackedTaskManager(Path saveFile) throws IOException {
        this.saveFile = saveFile;
        loadFromFile(saveFile);
    }

    @Override
    public void removeTasks() {
        super.removeTasks();
        save();
    }

    @Override
    public void removeEpics() {
        super.removeEpics();
        save();
    }

    @Override
    public void removeSubtasks(Epic epic) {
        super.removeSubtasks(epic);
        save();
    }

    @Override
    public void addTask(Task task) {
        super.addTask(task);
        save();
    }

    @Override
    public void addEpic(Epic epic) {
        super.addEpic(epic);
        save();
    }

    @Override
    public void addSubtask(Subtask subtask) {
        super.addSubtask(subtask);
        save();
    }

    @Override
    public void updateTask(Task task) {
        super.updateTask(task);
        save();
    }

    @Override
    public void updateEpic(Epic epic) {
        super.updateEpic(epic);
        save();
    }

    @Override
    public void updateSubtask(Subtask subtask) {
        super.updateSubtask(subtask);
        save();
    }

    @Override
    public void removeTaskById(Integer id) {
        super.removeTaskById(id);
        save();
    }

    @Override
    public void removeEpicById(Integer id) {
        super.removeEpicById(id);
        save();
    }

    @Override
    public void removeSubtaskById(Integer id) throws IOException {
        super.removeSubtaskById(id);
        save();
    }

    public void save() throws ManagerSaveException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(saveFile.toFile()));

            for (Task task : getTasks()) {
                writer.write(toString(task));
            }

            for (Epic epic : getEpics()) {
                writer.write(toString(epic));
            }

            for (Subtask subtask : getSubtasks()) {
                writer.write(toStringSubtask(subtask));
            }
            writer.close();

        } catch (ManagerSaveException exception) {
            throw new ManagerSaveException();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private String toString(Task task) {
        return task.getId() + "," +
                task.getTaskClass() + "," +
                task.getTitle() + "," +
                task.getStatus() + "," +
                task.getDescription() + "," +
                System.lineSeparator();
    }

    private String toStringSubtask(Subtask subtask) {
        return subtask.getId() + "," +
                subtask.getTaskClass() + "," +
                subtask.getTitle() + "," +
                subtask.getStatus() + "," +
                subtask.getDescription() + "," +
                subtask.getEpicId() + "," +
                System.lineSeparator();
    }

    private Task fromString(String value) {
        if (value == null || value.trim().isEmpty()) {
            return null;
        }

        String[] splitValue = value.split(",");
        if (splitValue[1].equals("TASK")) {
            Task task = new Task(splitValue[2], splitValue[4]);
            task.setId(Integer.parseInt(splitValue[0]));
            task.setStatus(TaskStatus.valueOf(splitValue[3]));
            addTask(task);
            return task;
        } else if (splitValue[1].equals("EPIC")) {
            Epic epic = new Epic(splitValue[2], splitValue[4]);
            epic.setId(Integer.parseInt(splitValue[0]));
            epic.setStatus(TaskStatus.valueOf(splitValue[3]));
            addEpic(epic);
            return epic;
        } else if (splitValue[1].equals("SUBTASK")) {
            Subtask subtask = new Subtask(splitValue[2], splitValue[4], Integer.parseInt(splitValue[5]));
            subtask.setId(Integer.parseInt(splitValue[0]));
            subtask.setStatus(TaskStatus.valueOf(splitValue[3]));
            addSubtask(subtask);
            return subtask;
        }
        return null;
    }

    private void loadFromFile(Path file) throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader(String.valueOf(file)));
        while (fileReader.ready()) {
            String line = fileReader.readLine();
            fromString(line);
        }
        fileReader.close();
    }
}
