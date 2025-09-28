package httpServer.handlers;

import classes.tasks.Subtask;
import com.sun.net.httpserver.HttpExchange;
import managers.TaskManager;
import java.io.IOException;
import java.util.Arrays;

public class SubtasksHandler extends BasicHandler {

    public SubtasksHandler(TaskManager taskManager) {
        super(taskManager);
    }

    @Override
    protected void getById(HttpExchange exchange, Integer id) throws IOException {
        String response;
        try {
            Subtask subtask = taskManager.getSubtaskById(id);
            if (subtask != null && taskManager.getSubtasks().contains(subtask)) {
                response = gson.toJson(subtask);
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
            if (!taskManager.getSubtasks().isEmpty()) {
                response = gson.toJson(taskManager.getSubtasks());
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
            Subtask subtask = gson.fromJson(body, Subtask.class);
            taskManager.updateSubtask(subtask);

            String response = gson.toJson(taskManager.getSubtaskById(id));
            sendResponse(exchange, response);
        } catch (Exception e) {
            handleNotFound(exchange);
        }
    }

    @Override
    protected void postCreate(HttpExchange exchange, String body) throws IOException {
        try {
            Subtask subtask = gson.fromJson(body, Subtask.class);
            taskManager.addSubtask(subtask);

            String response = gson.toJson(subtask);
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

                Subtask subtask = taskManager.getSubtaskById(id);
                if (subtask != null && taskManager.getSubtasks().contains(subtask)) {
                    response = gson.toJson(subtask);
                    taskManager.removeSubtaskById(id);
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
