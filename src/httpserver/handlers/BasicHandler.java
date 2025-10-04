package httpserver.handlers;

import com.google.gson.*;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import httpserver.adapters.DurationTypeAdapter;
import httpserver.adapters.LocalDateTimeTypeAdapter;
import managers.TaskManager;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;

class BasicHandler implements HttpHandler {

    protected final TaskManager taskManager;
    protected final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
            .registerTypeAdapter(Duration.class, new DurationTypeAdapter())
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeTypeAdapter())
            .create();

    public BasicHandler(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    @Override
    public void handle(HttpExchange exchange) {
        try {
            String method = exchange.getRequestMethod();
            System.out.println("Received request: " + method);
            switch (method) {
                case "GET":
                    getHandler(exchange);
                    break;
                case "POST":
                    postHandler(exchange);
                    break;
                case "DELETE":
                    deleteHandler(exchange);
                    break;
                default:
                    handleNotFound(exchange);
            }
        } catch (Exception e) {
            System.out.println("Unhandled exception");
            try {
                handleInternalServerError(exchange);
            } catch (Exception ex) {
                System.out.println("Failed to send error response");
            }
        }
    }

    protected void getHandler(HttpExchange exchange) throws IOException {
        String path = exchange.getRequestURI().getPath();
        System.out.println("getHandler - Path: " + path);

        String[] elements = path.split("/");
        elements = Arrays.stream(elements)
                .filter(e -> !e.isEmpty())
                .toArray(String[]::new);

        System.out.println("getHandler - Elements: " + Arrays.toString(elements));

        if (elements.length == 1) {
            getList(exchange);
        } else if (elements.length == 2) {
            try {
                Integer id = Integer.parseInt(elements[1]);
                getById(exchange, id);
            } catch (NumberFormatException e) {
                System.out.println("Invalid ID format: " + elements[1]);
                handleBadRequest(exchange);
            }
        } else if (elements.length == 3 && "subtasks".equals(elements[2])) {
            try {
                Integer id = Integer.parseInt(elements[1]);
                getEpicSubtasks(exchange, id);
            } catch (NumberFormatException e) {
                System.out.println("Invalid ID format for subtasks: " + elements[1]);
                handleBadRequest(exchange);
            }
        } else {
            System.out.println("Unhandled path format");
            handleNotFound(exchange);
        }
    }

    protected void postHandler(HttpExchange exchange) throws IOException {
        String body;
        try (InputStream is = exchange.getRequestBody()) {
            body = new String(is.readAllBytes(), StandardCharsets.UTF_8);
        }

        if (body == null || body.trim().isEmpty()) {
            handleBadRequest(exchange);
            return;
        }
        JsonElement jsonElement = JsonParser.parseString(body);
        JsonObject jsonObject = jsonElement.getAsJsonObject();

        if (jsonObject.has("id")) {
            postUpdate(exchange, jsonObject.get("id").getAsInt(), body);
        } else {
            postCreate(exchange, body);
        }
    }

    protected void postCreate(HttpExchange exchange, String body) throws IOException {
        handleBadRequest(exchange);
    }

    protected void postUpdate(HttpExchange exchange, Integer id, String body) throws IOException {
        handleBadRequest(exchange);
    }

    protected void getById(HttpExchange exchange, Integer id) throws IOException {
        handleBadRequest(exchange);
    }

    protected void getList(HttpExchange exchange) throws IOException {
        handleBadRequest(exchange);
    }

    protected void getEpicSubtasks(HttpExchange exchange, Integer id) throws IOException {
        handleBadRequest(exchange);
    }

    protected void deleteHandler(HttpExchange exchange) throws IOException {
        handleBadRequest(exchange);
    }

    protected void handleNotFound(HttpExchange exchange) throws IOException {
        String response = "Unknown request";
        exchange.sendResponseHeaders(404, response.getBytes().length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }

    protected void handleBadRequest(HttpExchange exchange) throws IOException {
        String response = "Bad request";
        exchange.sendResponseHeaders(400, response.getBytes().length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }

    protected void sendResponse(HttpExchange exchange, String response) throws IOException {
        exchange.getResponseHeaders().add("Content-Type", "application/json;charset=utf-8");
        exchange.sendResponseHeaders(200, response.getBytes().length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }

    protected void handleInternalServerError(HttpExchange exchange) throws IOException {
        String response = "Internal Server Error";
        exchange.sendResponseHeaders(500, response.getBytes().length);
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
    }
}
