package httpserver.handlers;

import classes.tasks.Epic;
import com.sun.net.httpserver.HttpExchange;
import managers.TaskManager;

import java.io.IOException;
import java.util.Arrays;

public class EpicsHandler extends BasicHandler {

    public EpicsHandler(TaskManager taskManager) {
        super(taskManager);
    }

    @Override
    protected void getById(HttpExchange exchange, Integer id) throws IOException {
        String response;
        try {
            Epic epic = taskManager.getEpicById(id);
            if (epic != null && taskManager.getEpics().contains(epic)) {
                response = gson.toJson(epic);
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
            if (!taskManager.getEpics().isEmpty()) {
                response = gson.toJson(taskManager.getEpics());
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
            Epic epic = gson.fromJson(body, Epic.class);
            taskManager.updateEpic(epic);
            String response = gson.toJson(taskManager.getEpicById(id));
            sendResponse(exchange, response);
        } catch (Exception e) {
            handleNotFound(exchange);
        }
    }

    @Override
    protected void postCreate(HttpExchange exchange, String body) throws IOException {
        try {
            Epic epic = gson.fromJson(body, Epic.class);
            taskManager.addEpic(epic);

            String response = gson.toJson(epic);
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

                Epic epic = taskManager.getEpicById(id);
                if (epic != null && taskManager.getEpics().contains(epic)) {
                    response = gson.toJson(epic);
                    taskManager.removeEpicById(id);
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

    @Override
    protected void getEpicSubtasks(HttpExchange exchange, Integer id) throws IOException {
        String response;
        if (taskManager.getEpics().contains(taskManager.getEpicById(id))
                && !taskManager.getSubtasksByEpic(taskManager.getEpicById(id)).isEmpty()) {
            response = gson.toJson(taskManager.getSubtasksByEpic(taskManager.getEpicById(id)));
            sendResponse(exchange, response);
        } else {
            handleNotFound(exchange);
        }
    }
}
