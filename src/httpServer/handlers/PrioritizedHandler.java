package httpServer.handlers;

import com.sun.net.httpserver.HttpExchange;
import managers.TaskManager;
import java.io.IOException;

public class PrioritizedHandler extends BasicHandler {

    public PrioritizedHandler(TaskManager taskManager) {
        super(taskManager);
    }

    @Override
    protected void getList(HttpExchange exchange) throws IOException {
        String response;
        if (!taskManager.getPrioritizedTasks().isEmpty()) {
            response = gson.toJson(taskManager.getPrioritizedTasks());
            sendResponse(exchange, response);
        } else {
            handleNotFound(exchange);
        }
    }
}
