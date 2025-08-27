package managers;

import classes.enums.TaskStatus;
import classes.enums.TaskType;
import classes.tasks.Epic;
import classes.tasks.Subtask;
import classes.tasks.Task;
import exception.ManagerSaveException;

import java.io.*;
import java.nio.file.Path;

public class FileBackedTaskManager extends InMemoryTaskManager {

    private Path saveFile;

    FileBackedTaskManager(Path saveFile) {
        this.saveFile = saveFile;
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

    private void save() throws ManagerSaveException {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(saveFile.toFile()));

            for (Task task : getTasks()) {
                writer.write(CSVFormat.toString(task));
            }

            for (Epic epic : getEpics()) {
                writer.write(CSVFormat.toString(epic));
            }

            for (Subtask subtask : getSubtasks()) {
                writer.write(CSVFormat.toStringSubtask(subtask));
            }
            writer.close();
        } catch (ManagerSaveException exception) {
            throw new ManagerSaveException();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static FileBackedTaskManager loadFromFile(Path file) throws IOException {
        BufferedReader fileReader = new BufferedReader(new FileReader(String.valueOf(file)));
        FileBackedTaskManager taskManager = new FileBackedTaskManager(file);
        while (fileReader.ready()) {
            String line = fileReader.readLine();
            Task task = CSVFormat.fromString(line);
            if (task.getTaskClass().equals(TaskType.TASK)) {
                taskManager.addTask(task);
            } else if (task.getTaskClass().equals(TaskType.EPIC)) {
                taskManager.addEpic((Epic) task);
            } else if (task.getTaskClass().equals(TaskType.SUBTASK)) {
                taskManager.addSubtask((Subtask) task);
            }
        }
        fileReader.close();
        return taskManager;
    }

    public static void main(String[] args) throws IOException {
        FileBackedTaskManager fileManager = new FileBackedTaskManager(new File("saveTasks2.csv").toPath());
        fileManager.addTask(new Task("task1", "Купить автомобиль"));
        fileManager.addEpic(new Epic("new Epic1", "Новый Эпик"));
        fileManager.addSubtask(new Subtask("New Subtask", "Подзадача", 1));
        fileManager.addSubtask(new Subtask("New Subtask2", "Подзадача2", 1));
        System.out.println(fileManager.getTasks());
        System.out.println(fileManager.getEpics());
        System.out.println(fileManager.getSubtasks());
        System.out.println("\n\n" + "new" + "\n\n");
        FileBackedTaskManager fileBackedTasksManager = loadFromFile(new File("saveTasks2.csv").toPath());
        System.out.println(fileBackedTasksManager.getTasks());
        System.out.println(fileBackedTasksManager.getEpics());
        System.out.println(fileBackedTasksManager.getSubtasks());
    }
}
