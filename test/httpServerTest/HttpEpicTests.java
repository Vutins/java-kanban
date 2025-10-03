package httpServerTest;

import httpserver.HttpTaskServer;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class HttpEpicTests {

    @Test
    void postEpics() {
        try {
            HttpTaskServer.start();
            URI url = URI.create("http://localhost:8080/epics");
            HttpClient client = HttpClient.newBuilder()
                    .version(HttpClient.Version.HTTP_1_1)
                    .build();

            String gsonInputStringCreate = "{\"title\": \"epic1\", \"description\": \"description1\"}";
            byte[] jsonByteCreate = gsonInputStringCreate.getBytes(StandardCharsets.UTF_8);
            HttpRequest requestCreate = HttpRequest.newBuilder()
                    .uri(url)
                    .header("Content-Type", "application/json; charset=utf-8")
                    .header("User-Agent", "Java HttpClient")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofByteArray(jsonByteCreate))
                    .build();

            HttpResponse<String> responseCreate = client.send(requestCreate, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status:" + responseCreate.statusCode());
            System.out.println("Body:" + responseCreate.body());

            String gsonInputStringUpdate = "{\"title\": \"дело 1\", \"description\": \"описание дела 1\", \"id\": \"1\"}";
            byte[] jsonByteUpdate = gsonInputStringUpdate.getBytes(StandardCharsets.UTF_8);
            HttpRequest requestUpdate = HttpRequest.newBuilder()
                    .uri(url)
                    .header("Content-Type", "application/json; charset=utf-8")
                    .header("User-Agent", "Java HttpClient")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofByteArray(jsonByteUpdate))
                    .build();

            HttpResponse<String> responseUpdate = client.send(requestUpdate, HttpResponse.BodyHandlers.ofString());
            System.out.println("status:" + responseUpdate.statusCode());
            System.out.println("body:" + responseUpdate.body());

            HttpTaskServer.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void getEpics() {
        try {
            HttpTaskServer.start();
            URI url = URI.create("http://localhost:8080/epics");
            HttpClient client = HttpClient.newBuilder()
                    .version(HttpClient.Version.HTTP_1_1)
                    .build();

            String gsonInputStringCreate1 = "{\"title\": \"epic1\", \"description\": \"description1\"}";
            byte[] jsonByteCreate1 = gsonInputStringCreate1.getBytes(StandardCharsets.UTF_8);
            HttpRequest requestCreate1 = HttpRequest.newBuilder()
                    .uri(url)
                    .header("Content-Type", "application/json; charset=utf-8")
                    .header("User-Agent", "Java HttpClient")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofByteArray(jsonByteCreate1))
                    .build();
            HttpResponse<String> responseCreate1 = client.send(requestCreate1, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status:" + responseCreate1.statusCode());
            System.out.println("Body:" + responseCreate1.body());

            String gsonInputStringUpdate = "{\"title\": \"дело 1\", \"description\": \"описание дела 1\", \"id\": \"1\"}";
            byte[] jsonByteUpdate = gsonInputStringUpdate.getBytes(StandardCharsets.UTF_8);
            HttpRequest requestUpdate = HttpRequest.newBuilder()
                    .uri(url)
                    .header("Content-Type", "application/json; charset=utf-8")
                    .header("User-Agent", "Java HttpClient")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofByteArray(jsonByteUpdate))
                    .build();
            HttpResponse<String> responseUpdate = client.send(requestUpdate, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status:" + responseUpdate.statusCode());
            System.out.println("Body:" + responseUpdate.body());

            String gsonInputStringCreate2 = "{\"title\": \"дело 2\", \"description\": \"описание дела 2\"}";
            byte[] jsonByteCreate2 = gsonInputStringCreate2.getBytes(StandardCharsets.UTF_8);
            HttpRequest requestCreate2 = HttpRequest.newBuilder()
                    .uri(url)
                    .header("Content-Type", "application/json; charset=utf-8")
                    .header("User-Agent", "Java HttpClient")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofByteArray(jsonByteCreate2))
                    .build();
            HttpResponse<String> responseCreate2 = client.send(requestCreate2, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status:" + responseCreate2.statusCode());
            System.out.println("Body:" + responseCreate2.body());

            String gsonInputStringCreate3 = "{\"title\": \"дело 3\", \"description\": \"описание дела 3\"}";
            byte[] jsonByteCreate3 = gsonInputStringCreate3.getBytes(StandardCharsets.UTF_8);
            HttpRequest requestCreate3 = HttpRequest.newBuilder()
                    .uri(url)
                    .header("Content-Type", "application/json; charset=utf-8")
                    .header("User-Agent", "Java HttpClient")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofByteArray(jsonByteCreate3))
                    .build();
            HttpResponse<String> responseCreate3 = client.send(requestCreate3, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status:" + responseCreate3.statusCode());
            System.out.println("Body:" + responseCreate3.body());


            System.out.println("*");
            System.out.println("*");
            System.out.println("Весь список Эпиков:");
            HttpRequest requestGet = HttpRequest.newBuilder()
                    .uri(url)
                    .header("Content-Type", "application/json; charset=utf-8")
                    .header("User-Agent", "Java HttpClient")
                    .header("Accept", "application/json")
                    .GET()
                    .build();
            HttpResponse<String> responseGet = client.send(requestGet, HttpResponse.BodyHandlers.ofString());
            System.out.println("status" + responseGet.statusCode());
            System.out.println("body" + responseGet.body());

            System.out.println("*");
            System.out.println("*");
            System.out.println("Эпик по id = 2:");
            URI url2 = URI.create("http://localhost:8080/epics/2");
            HttpRequest requestGetById = HttpRequest.newBuilder()
                    .uri(url2)
                    .header("Content-Type", "application/json; charset=utf-8")
                    .header("User-Agent", "Java HttpClient")
                    .header("Accept", "application/json")
                    .GET()
                    .build();
            HttpResponse<String> responseGetById = client.send(requestGetById, HttpResponse.BodyHandlers.ofString());
            System.out.println("status" + responseGetById.statusCode());
            System.out.println("body" + responseGetById.body());

            HttpTaskServer.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void deleteEpics() {
        try {
            HttpTaskServer.start();
            URI url = URI.create("http://localhost:8080/epics");
            HttpClient client = HttpClient.newBuilder()
                    .version(HttpClient.Version.HTTP_1_1)
                    .build();

            String gsonInputStringCreate1 = "{\"title\": \"epic1\", \"description\": \"description1\"}";
            byte[] jsonByteCreate1 = gsonInputStringCreate1.getBytes(StandardCharsets.UTF_8);
            HttpRequest requestCreate1 = HttpRequest.newBuilder()
                    .uri(url)
                    .header("Content-Type", "application/json; charset=utf-8")
                    .header("User-Agent", "Java HttpClient")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofByteArray(jsonByteCreate1))
                    .build();
            HttpResponse<String> responseCreate1 = client.send(requestCreate1, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status:" + responseCreate1.statusCode());
            System.out.println("Body:" + responseCreate1.body());

            String gsonInputStringUpdate = "{\"title\": \"дело 1\", \"description\": \"описание дела 1\", \"id\": \"1\"}";
            byte[] jsonByteUpdate = gsonInputStringUpdate.getBytes(StandardCharsets.UTF_8);
            HttpRequest requestUpdate = HttpRequest.newBuilder()
                    .uri(url)
                    .header("Content-Type", "application/json; charset=utf-8")
                    .header("User-Agent", "Java HttpClient")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofByteArray(jsonByteUpdate))
                    .build();
            HttpResponse<String> responseUpdate = client.send(requestUpdate, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status:" + responseUpdate.statusCode());
            System.out.println("Body:" + responseUpdate.body());

            String gsonInputStringCreate2 = "{\"title\": \"дело 2\", \"description\": \"описание дела 2\"}";
            byte[] jsonByteCreate2 = gsonInputStringCreate2.getBytes(StandardCharsets.UTF_8);
            HttpRequest requestCreate2 = HttpRequest.newBuilder()
                    .uri(url)
                    .header("Content-Type", "application/json; charset=utf-8")
                    .header("User-Agent", "Java HttpClient")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofByteArray(jsonByteCreate2))
                    .build();
            HttpResponse<String> responseCreate2 = client.send(requestCreate2, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status:" + responseCreate2.statusCode());
            System.out.println("Body:" + responseCreate2.body());

            String gsonInputStringCreate3 = "{\"title\": \"дело 3\", \"description\": \"описание дела 3\"}";
            byte[] jsonByteCreate3 = gsonInputStringCreate3.getBytes(StandardCharsets.UTF_8);
            HttpRequest requestCreate3 = HttpRequest.newBuilder()
                    .uri(url)
                    .header("Content-Type", "application/json; charset=utf-8")
                    .header("User-Agent", "Java HttpClient")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofByteArray(jsonByteCreate3))
                    .build();
            HttpResponse<String> responseCreate3 = client.send(requestCreate3, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status:" + responseCreate3.statusCode());
            System.out.println("Body:" + responseCreate3.body());

            System.out.println("*");
            System.out.println("*");
            System.out.println("Список до удаления:");
            HttpRequest requestGet = HttpRequest.newBuilder()
                    .uri(url)
                    .header("Content-Type", "application/json; charset=utf-8")
                    .header("User-Agent", "Java HttpClient")
                    .header("Accept", "application/json")
                    .GET()
                    .build();
            HttpResponse<String> responseGet = client.send(requestGet, HttpResponse.BodyHandlers.ofString());
            System.out.println("status" + responseGet.statusCode());
            System.out.println("body" + responseGet.body());
            System.out.println("*");
            System.out.println("*");

            URI url2 = URI.create("http://localhost:8080/epics/2");
            HttpRequest requestDelete = HttpRequest.newBuilder()
                    .uri(url2)
                    .header("Content-Type", "application/json; charset=utf-8")
                    .header("User-Agent", "Java HttpClient")
                    .header("Accept", "application/json")
                    .DELETE()
                    .build();
            HttpResponse<String> responseDelete = client.send(requestDelete, HttpResponse.BodyHandlers.ofString());

            System.out.println("Удаленный элемент:");
            System.out.println("status" + responseDelete.statusCode());
            System.out.println("body" + responseDelete.body());

            System.out.println("*");
            System.out.println("*");
            System.out.println("Список после удаления (id = 2):");

            HttpResponse<String> responseGet2 = client.send(requestGet, HttpResponse.BodyHandlers.ofString());
            System.out.println("status" + responseGet2.statusCode());
            System.out.println("body" + responseGet2.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void shouldAddAndGetSubtasksByEpic() {
        try {
            HttpTaskServer.start();
            URI url = URI.create("http://localhost:8080/epics");
            HttpClient client = HttpClient.newBuilder()
                    .version(HttpClient.Version.HTTP_1_1)
                    .build();

            String gsonInputStringCreate1 = "{\"title\": \"epic1\", \"description\": \"description1\"}";
            byte[] jsonByteCreate1 = gsonInputStringCreate1.getBytes(StandardCharsets.UTF_8);
            HttpRequest requestCreate1 = HttpRequest.newBuilder()
                    .uri(url)
                    .header("Content-Type", "application/json; charset=utf-8")
                    .header("User-Agent", "Java HttpClient")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofByteArray(jsonByteCreate1))
                    .build();
            HttpResponse<String> responseCreate1 = client.send(requestCreate1, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status:" + responseCreate1.statusCode());
            System.out.println("Body:" + responseCreate1.body());

            String gsonInputStringUpdate = "{\"title\": \"дело 1\", \"description\": \"описание дела 1\", \"id\": \"1\"}";
            byte[] jsonByteUpdate = gsonInputStringUpdate.getBytes(StandardCharsets.UTF_8);
            HttpRequest requestUpdate = HttpRequest.newBuilder()
                    .uri(url)
                    .header("Content-Type", "application/json; charset=utf-8")
                    .header("User-Agent", "Java HttpClient")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofByteArray(jsonByteUpdate))
                    .build();
            HttpResponse<String> responseUpdate = client.send(requestUpdate, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status:" + responseUpdate.statusCode());
            System.out.println("Body:" + responseUpdate.body());

            URI urlSubtask = URI.create("http://localhost:8080/subtasks");
            String gsonInputStringCreateSubtask = "{\"title\": \"поддело 1 \", \"description\": \"описание поддела 1\", \"epicId\": \"1\"}";
            byte[] jsonByteSubtask = gsonInputStringCreateSubtask.getBytes(StandardCharsets.UTF_8);
            HttpRequest requestCreateSubtask = HttpRequest.newBuilder()
                    .uri(urlSubtask)
                    .header("Content-Type", "application/json; charset=utf-8")
                    .header("User-Agent", "Java HttpClient")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofByteArray(jsonByteSubtask))
                    .build();
            HttpResponse<String> responseSubtasks = client.send(requestCreateSubtask, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status:" + responseSubtasks.statusCode());
            System.out.println("Body:" + responseSubtasks.body());

            String gsonInputStringCreate2 = "{\"title\": \"дело 2\", \"description\": \"описание дела 2\"}";
            byte[] jsonByteCreate2 = gsonInputStringCreate2.getBytes(StandardCharsets.UTF_8);
            HttpRequest requestCreate2 = HttpRequest.newBuilder()
                    .uri(url)
                    .header("Content-Type", "application/json; charset=utf-8")
                    .header("User-Agent", "Java HttpClient")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofByteArray(jsonByteCreate2))
                    .build();
            HttpResponse<String> responseCreate2 = client.send(requestCreate2, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status:" + responseCreate2.statusCode());
            System.out.println("Body:" + responseCreate2.body());

            String gsonInputStringCreate3 = "{\"title\": \"дело 3\", \"description\": \"описание дела 3\"}";
            byte[] jsonByteCreate3 = gsonInputStringCreate3.getBytes(StandardCharsets.UTF_8);
            HttpRequest requestCreate3 = HttpRequest.newBuilder()
                    .uri(url)
                    .header("Content-Type", "application/json; charset=utf-8")
                    .header("User-Agent", "Java HttpClient")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofByteArray(jsonByteCreate3))
                    .build();
            HttpResponse<String> responseCreate3 = client.send(requestCreate3, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status:" + responseCreate3.statusCode());
            System.out.println("Body:" + responseCreate3.body());

            System.out.println("*");
            System.out.println("*");
            System.out.println("Весь список Эпиков:");
            HttpRequest requestGet = HttpRequest.newBuilder()
                    .uri(url)
                    .header("Content-Type", "application/json; charset=utf-8")
                    .header("User-Agent", "Java HttpClient")
                    .header("Accept", "application/json")
                    .GET()
                    .build();
            HttpResponse<String> responseGet = client.send(requestGet, HttpResponse.BodyHandlers.ofString());
            System.out.println("status" + responseGet.statusCode());
            System.out.println("body" + responseGet.body());

            System.out.println("*");
            System.out.println("*");
            System.out.println("Эпик по id = 1:");
            URI url2 = URI.create("http://localhost:8080/epics/1");
            HttpRequest requestGetById = HttpRequest.newBuilder()
                    .uri(url2)
                    .header("Content-Type", "application/json; charset=utf-8")
                    .header("User-Agent", "Java HttpClient")
                    .header("Accept", "application/json")
                    .GET()
                    .build();
            HttpResponse<String> responseGetById = client.send(requestGetById, HttpResponse.BodyHandlers.ofString());
            System.out.println("status" + responseGetById.statusCode());
            System.out.println("body" + responseGetById.body());

            HttpTaskServer.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
