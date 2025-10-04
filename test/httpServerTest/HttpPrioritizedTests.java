package httpServerTest;

import httpserver.HttpTaskServer;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class HttpPrioritizedTests {

    @Test
    void getPrioritized() {
        try {
            HttpTaskServer.start();
            URI urlTask = URI.create("http://localhost:8080/tasks");
            URI urlEpic = URI.create("http://localhost:8080/epics");
            HttpClient client = HttpClient.newBuilder()
                    .version(HttpClient.Version.HTTP_1_1)
                    .build();

            String gsonInputStringTask1 = "{\"title\": \"задача 1\", \"description\": \"описание 1\"}";
            byte[] jsonByteTask1 = gsonInputStringTask1.getBytes(StandardCharsets.UTF_8);
            HttpRequest requestTask1 = HttpRequest.newBuilder()
                    .uri(urlTask)
                    .header("Content-Type", "application/json; charset=utf-8")
                    .header("User-Agent", "Java HttpClient")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofByteArray(jsonByteTask1))
                    .build();
            HttpResponse<String> responseTask1 = client.send(requestTask1, HttpResponse.BodyHandlers.ofString());

            String gsonInputStringCreate2 = "{\"title\": \"задача 2\", \"description\": \"описание 2\"}";
            byte[] jsonByteCreate2 = gsonInputStringCreate2.getBytes(StandardCharsets.UTF_8);
            HttpRequest requestCreate2 = HttpRequest.newBuilder()
                    .uri(urlTask)
                    .header("Content-Type", "application/json; charset=utf-8")
                    .header("User-Agent", "Java HttpClient")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofByteArray(jsonByteCreate2))
                    .build();
            HttpResponse<String> responseCreate2 = client.send(requestCreate2, HttpResponse.BodyHandlers.ofString());

            String gsonInputStringEpic1 = "{\"title\": \"эпик 1\", \"description\": \"описание эпика1\"}";
            byte[] jsonByteEpic1 = gsonInputStringEpic1.getBytes(StandardCharsets.UTF_8);
            HttpRequest requestEpic1 = HttpRequest.newBuilder()
                    .uri(urlEpic)
                    .header("Content-Type", "application/json; charset=utf-8")
                    .header("User-Agent", "Java HttpClient")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofByteArray(jsonByteEpic1))
                    .build();
            HttpResponse<String> responseEpic1 = client.send(requestEpic1, HttpResponse.BodyHandlers.ofString());

            URI urlEpicById = URI.create("http://localhost:8080/epics/3");
            HttpRequest requestGetEpic1 = HttpRequest.newBuilder()
                    .uri(urlEpicById)
                    .header("Content-Type", "application/json; charset=utf-8")
                    .header("User-Agent", "Java HttpClient")
                    .header("Accept", "application/json")
                    .GET()
                    .build();
            HttpResponse<String> responseGetEpic1 = client.send(requestGetEpic1, HttpResponse.BodyHandlers.ofString());

            URI urlTaskById = URI.create("http://localhost:8080/tasks/1");
            HttpRequest requestGetTask1 = HttpRequest.newBuilder()
                    .uri(urlTaskById)
                    .header("Content-Type", "application/json; charset=utf-8")
                    .header("User-Agent", "Java HttpClient")
                    .header("Accept", "application/json")
                    .GET()
                    .build();
            HttpResponse<String> responseGetTask1 = client.send(requestGetTask1, HttpResponse.BodyHandlers.ofString());

            URI urlPrioritized = URI.create("http://localhost:8080/prioritized");
            HttpRequest requestPrioritized = HttpRequest.newBuilder()
                    .uri(urlPrioritized)
                    .header("Content-Type", "application/json; charset=utf-8")
                    .header("User-Agent", "Java HttpClient")
                    .header("Accept", "application/json")
                    .GET()
                    .build();
            HttpResponse<String> responsePrioritized = client.send(requestPrioritized, HttpResponse.BodyHandlers.ofString());
            System.out.println("status" + responsePrioritized.statusCode());
            System.out.println("body" + responsePrioritized.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
