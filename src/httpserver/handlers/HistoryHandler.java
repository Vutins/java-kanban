package httpserver.handlers;

import com.sun.net.httpserver.HttpExchange;
import managers.TaskManager;
import java.io.IOException;

public class HistoryHandler extends BasicHandler {

    public HistoryHandler(TaskManager taskManager) {
        super(taskManager);

    }

    @Override
    protected void getList(HttpExchange exchange) throws IOException {
        String response;
        if (!taskManager.getHistory().isEmpty()) {
            response = gson.toJson(taskManager.getHistory());
            sendResponse(exchange, response);
        } else {
            handleNotFound(exchange);
        }
    }
}
