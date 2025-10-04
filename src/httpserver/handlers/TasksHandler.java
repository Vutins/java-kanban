package httpserver.handlers;

import classes.tasks.Task;
import com.sun.net.httpserver.HttpExchange;
import managers.TaskManager;
import java.io.IOException;
import java.util.Arrays;

public class TasksHandler extends BasicHandler {

    public TasksHandler(TaskManager taskManager) {
        super(taskManager);
    }

    @Override
    protected void getById(HttpExchange exchange, Integer id) throws IOException {
        String response;
        try {
            Task task = taskManager.getTaskById(id);
            if (task != null) {
                response = gson.toJson(task);
                sendResponse(exchange, response);
            } else {
                handleNotFound(exchange);
            }
        } catch (Exception e) {
            handleInternalServerError(exchange);
        }
    }

    @Override
    protected void getList(HttpExchange exchange) throws IOException {
        String response;
        try {
            if (!taskManager.getTasks().isEmpty()) {
                response = gson.toJson(taskManager.getTasks());
                sendResponse(exchange, response);
            } else {
                handleNotFound(exchange);
            }
        } catch (Exception e) {
            handleInternalServerError(exchange);
        }
    }

    @Override
    protected void postUpdate(HttpExchange exchange, Integer id, String body) throws IOException {
        try {
            Task task = gson.fromJson(body, Task.class);
            taskManager.updateTask(task);
            String response = gson.toJson(taskManager.getTaskById(id));
            sendResponse(exchange, response);
        } catch (Exception e) {
            handleNotFound(exchange);
        }
    }

    @Override
    protected void postCreate(HttpExchange exchange, String body) throws IOException {
        try {
            Task task = gson.fromJson(body, Task.class);
            taskManager.addTask(task);

            String response = gson.toJson(task);
            sendResponse(exchange, response);
        } catch (Exception e) {
            handleNotFound(exchange);
        }
    }

    @Override
    protected void deleteHandler(HttpExchange exchange) throws IOException {
        try {
            String path = exchange.getRequestURI().getPath();
            String[] elements = path.split("/");
            elements = Arrays.stream(elements)
                    .filter(e -> !e.isEmpty())
                    .toArray(String[]::new);

            if (elements.length == 2) {
                Integer id = Integer.parseInt(elements[1]);
                String response;

                Task task = taskManager.getTaskById(id);
                if (task != null && taskManager.getTasks().contains(task)) {
                    response = gson.toJson(task);
                    taskManager.removeTaskById(id);
                    sendResponse(exchange, response);
                } else {
                    handleNotFound(exchange);
                }
            } else {
                handleBadRequest(exchange);
            }
        } catch (NumberFormatException e) {
            handleBadRequest(exchange);
        } catch (Exception e) {
            handleInternalServerError(exchange);
        }
    }
}