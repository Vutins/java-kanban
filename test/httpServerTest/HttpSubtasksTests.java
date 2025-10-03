package httpServerTest;

import httpserver.HttpTaskServer;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class HttpSubtasksTests {

    @Test
    void postSubtasks() {
        try {
            HttpTaskServer.start();
            URI urlSubtasks = URI.create("http://localhost:8080/subtasks");
            URI urlEpics = URI.create("http://localhost:8080/epics");
            HttpClient client = HttpClient.newBuilder()
                    .version(HttpClient.Version.HTTP_1_1)
                    .build();

            String gsonInputStringCreateEpic1 = "{\"title\": \"epic1\", \"description\": \"descriptionEpic1\"}";
            byte[] jsonByteCreateEpic1 = gsonInputStringCreateEpic1.getBytes(StandardCharsets.UTF_8);
            HttpRequest requestCreateEpic1 = HttpRequest.newBuilder()
                    .uri(urlEpics)
                    .header("Content-Type", "application/json; charset=utf-8")
                    .header("User-Agent", "Java HttpClient")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofByteArray(jsonByteCreateEpic1))
                    .build();
            HttpResponse<String> responseCreateEpic1 = client.send(requestCreateEpic1, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status:" + responseCreateEpic1.statusCode());
            System.out.println("Body:" + responseCreateEpic1.body());

            String gsonInputStringCreateEpic2 = "{\"title\": \"epic2\", \"description\": \"descriptionEpic2\"}";
            byte[] jsonByteCreateEpic2 = gsonInputStringCreateEpic2.getBytes(StandardCharsets.UTF_8);
            HttpRequest requestCreateEpic2 = HttpRequest.newBuilder()
                    .uri(urlEpics)
                    .header("Content-Type", "application/json; charset=utf-8")
                    .header("User-Agent", "Java HttpClient")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofByteArray(jsonByteCreateEpic2))
                    .build();
            HttpResponse<String> responseCreateEpic2 = client.send(requestCreateEpic2, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status:" + responseCreateEpic2.statusCode());
            System.out.println("Body:" + responseCreateEpic2.body());

            String gsonInputStringCreate = "{\"title\": \"сабтаск1\", \"description\": \"описание1\", \"epicId\": \"1\", \"id\": \"3\"}";
            byte[] jsonByteCreate = gsonInputStringCreate.getBytes(StandardCharsets.UTF_8);
            HttpRequest requestCreate = HttpRequest.newBuilder()
                    .uri(urlSubtasks)
                    .header("Content-Type", "application/json; charset=utf-8")
                    .header("User-Agent", "Java HttpClient")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofByteArray(jsonByteCreate))
                    .build();
            HttpResponse<String> responseCreate = client.send(requestCreate, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status:" + responseCreate.statusCode());
            System.out.println("Body:" + responseCreate.body());

            String gsonInputStringUpdate = "{\"title\": \"subtask1\", \"description\": \"description1\", \"epicId\": \"1\"}";
            byte[] jsonByteUpdate = gsonInputStringUpdate.getBytes(StandardCharsets.UTF_8);
            HttpRequest requestUpdate = HttpRequest.newBuilder()
                    .uri(urlSubtasks)
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
    void getSubtasks() {
        try {
            HttpTaskServer.start();
            URI urlSubtasks = URI.create("http://localhost:8080/subtasks");
            URI urlEpics = URI.create("http://localhost:8080/epics");
            URI urlEpicBySubtask = URI.create("http://localhost:8080/epics/1");
            HttpClient client = HttpClient.newBuilder()
                    .version(HttpClient.Version.HTTP_1_1)
                    .build();

            String gsonInputStringCreateEpic1 = "{\"title\": \"epic1\", \"description\": \"descriptionEpic1\"}";
            byte[] jsonByteCreateEpic1 = gsonInputStringCreateEpic1.getBytes(StandardCharsets.UTF_8);
            HttpRequest requestCreateEpic1 = HttpRequest.newBuilder()
                    .uri(urlEpics)
                    .header("Content-Type", "application/json; charset=utf-8")
                    .header("User-Agent", "Java HttpClient")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofByteArray(jsonByteCreateEpic1))
                    .build();
            HttpResponse<String> responseCreateEpic1 = client.send(requestCreateEpic1, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status:" + responseCreateEpic1.statusCode());
            System.out.println("Body:" + responseCreateEpic1.body());

            String gsonInputStringCreateEpic2 = "{\"title\": \"epic2\", \"description\": \"descriptionEpic2\"}";
            byte[] jsonByteCreateEpic2 = gsonInputStringCreateEpic2.getBytes(StandardCharsets.UTF_8);
            HttpRequest requestCreateEpic2 = HttpRequest.newBuilder()
                    .uri(urlEpics)
                    .header("Content-Type", "application/json; charset=utf-8")
                    .header("User-Agent", "Java HttpClient")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofByteArray(jsonByteCreateEpic2))
                    .build();
            HttpResponse<String> responseCreateEpic2 = client.send(requestCreateEpic2, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status:" + responseCreateEpic2.statusCode());
            System.out.println("Body:" + responseCreateEpic2.body());

            String gsonInputStringCreate = "{\"title\": \"сабтаск1\", \"description\": \"описание1\", \"epicId\": \"1\", \"id\": \"3\"}";
            byte[] jsonByteCreate = gsonInputStringCreate.getBytes(StandardCharsets.UTF_8);
            HttpRequest requestCreate = HttpRequest.newBuilder()
                    .uri(urlSubtasks)
                    .header("Content-Type", "application/json; charset=utf-8")
                    .header("User-Agent", "Java HttpClient")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofByteArray(jsonByteCreate))
                    .build();
            HttpResponse<String> responseCreate = client.send(requestCreate, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status:" + responseCreate.statusCode());
            System.out.println("Body:" + responseCreate.body());

            String gsonInputStringUpdate = "{\"title\": \"subtask1\", \"description\": \"description1\", \"epicId\": \"1\"}";
            byte[] jsonByteUpdate = gsonInputStringUpdate.getBytes(StandardCharsets.UTF_8);
            HttpRequest requestUpdate = HttpRequest.newBuilder()
                    .uri(urlSubtasks)
                    .header("Content-Type", "application/json; charset=utf-8")
                    .header("User-Agent", "Java HttpClient")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofByteArray(jsonByteUpdate))
                    .build();
            HttpResponse<String> responseUpdate = client.send(requestUpdate, HttpResponse.BodyHandlers.ofString());
            System.out.println("status:" + responseUpdate.statusCode());
            System.out.println("body:" + responseUpdate.body());

            String gsonInputStringCreate2 = "{\"title\": \"subtask2\", \"description\": \"description2\", \"epicId\": \"1\"}";
            byte[] jsonByteCreate2 = gsonInputStringCreate2.getBytes(StandardCharsets.UTF_8);
            HttpRequest requestCreate2 = HttpRequest.newBuilder()
                    .uri(urlSubtasks)
                    .header("Content-Type", "application/json; charset=utf-8")
                    .header("User-Agent", "Java HttpClient")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofByteArray(jsonByteCreate2))
                    .build();
            HttpResponse<String> responseCreate2 = client.send(requestCreate2, HttpResponse.BodyHandlers.ofString());
            System.out.println("status:" + responseCreate2.statusCode());
            System.out.println("body:" + responseCreate2.body());

            String gsonInputStringCreate3 = "{\"title\": \"subtask3\", \"description\": \"description3\", \"epicId\": \"2\"}";
            byte[] jsonByteCreate3 = gsonInputStringCreate3.getBytes(StandardCharsets.UTF_8);
            HttpRequest requestCreate3 = HttpRequest.newBuilder()
                    .uri(urlSubtasks)
                    .header("Content-Type", "application/json; charset=utf-8")
                    .header("User-Agent", "Java HttpClient")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofByteArray(jsonByteCreate3))
                    .build();
            HttpResponse<String> responseCreate3 = client.send(requestCreate3, HttpResponse.BodyHandlers.ofString());
            System.out.println("status:" + responseCreate3.statusCode());
            System.out.println("body:" + responseCreate3.body());

            System.out.println("*");
            System.out.println("*");
            System.out.println("All Subtasks:");

            HttpRequest requestGet = HttpRequest.newBuilder()
                    .uri(urlSubtasks)
                    .header("Content-Type", "application/json; charset=utf-8")
                    .header("User-Agent", "Java HttpClient")
                    .header("Accept", "application/json")
                    .GET()
                    .build();
            HttpResponse<String> responseGet = client.send(requestGet, HttpResponse.BodyHandlers.ofString());
            System.out.println("status:" + responseGet.statusCode());
            System.out.println("body:" + responseGet.body());

            System.out.println("*");
            System.out.println("*");
            System.out.println("Subtasks (epicId = 1):");

            HttpRequest requestGetByEpic = HttpRequest.newBuilder()
                    .uri(urlEpicBySubtask)
                    .header("Content-Type", "application/json; charset=utf-8")
                    .header("User-Agent", "Java HttpClient")
                    .header("Accept", "application/json")
                    .GET()
                    .build();
            HttpResponse<String> responseGetByEpic = client.send(requestGetByEpic, HttpResponse.BodyHandlers.ofString());
            System.out.println("status:" + responseGetByEpic.statusCode());
            System.out.println("body:" + responseGetByEpic.body());

            HttpTaskServer.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void deleteSubtasks() {
        try {
            HttpTaskServer.start();
            URI urlSubtasks = URI.create("http://localhost:8080/subtasks");
            URI urlEpics = URI.create("http://localhost:8080/epics");
            HttpClient client = HttpClient.newBuilder()
                    .version(HttpClient.Version.HTTP_1_1)
                    .build();

            String gsonInputStringCreateEpic1 = "{\"title\": \"epic1\", \"description\": \"descriptionEpic1\"}";
            byte[] jsonByteCreateEpic1 = gsonInputStringCreateEpic1.getBytes(StandardCharsets.UTF_8);
            HttpRequest requestCreateEpic1 = HttpRequest.newBuilder()
                    .uri(urlEpics)
                    .header("Content-Type", "application/json; charset=utf-8")
                    .header("User-Agent", "Java HttpClient")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofByteArray(jsonByteCreateEpic1))
                    .build();
            HttpResponse<String> responseCreateEpic1 = client.send(requestCreateEpic1, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status:" + responseCreateEpic1.statusCode());
            System.out.println("Body:" + responseCreateEpic1.body());

            String gsonInputStringCreateEpic2 = "{\"title\": \"epic2\", \"description\": \"descriptionEpic2\"}";
            byte[] jsonByteCreateEpic2 = gsonInputStringCreateEpic2.getBytes(StandardCharsets.UTF_8);
            HttpRequest requestCreateEpic2 = HttpRequest.newBuilder()
                    .uri(urlEpics)
                    .header("Content-Type", "application/json; charset=utf-8")
                    .header("User-Agent", "Java HttpClient")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofByteArray(jsonByteCreateEpic2))
                    .build();
            HttpResponse<String> responseCreateEpic2 = client.send(requestCreateEpic2, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status:" + responseCreateEpic2.statusCode());
            System.out.println("Body:" + responseCreateEpic2.body());

            String gsonInputStringCreate = "{\"title\": \"сабтаск1\", \"description\": \"описание1\", \"epicId\": \"1\", \"id\": \"3\"}";
            byte[] jsonByteCreate = gsonInputStringCreate.getBytes(StandardCharsets.UTF_8);
            HttpRequest requestCreate = HttpRequest.newBuilder()
                    .uri(urlSubtasks)
                    .header("Content-Type", "application/json; charset=utf-8")
                    .header("User-Agent", "Java HttpClient")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofByteArray(jsonByteCreate))
                    .build();
            HttpResponse<String> responseCreate = client.send(requestCreate, HttpResponse.BodyHandlers.ofString());
            System.out.println("Status:" + responseCreate.statusCode());
            System.out.println("Body:" + responseCreate.body());

            String gsonInputStringUpdate = "{\"title\": \"subtask1\", \"description\": \"description1\", \"epicId\": \"1\"}";
            byte[] jsonByteUpdate = gsonInputStringUpdate.getBytes(StandardCharsets.UTF_8);
            HttpRequest requestUpdate = HttpRequest.newBuilder()
                    .uri(urlSubtasks)
                    .header("Content-Type", "application/json; charset=utf-8")
                    .header("User-Agent", "Java HttpClient")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofByteArray(jsonByteUpdate))
                    .build();
            HttpResponse<String> responseUpdate = client.send(requestUpdate, HttpResponse.BodyHandlers.ofString());
            System.out.println("status:" + responseUpdate.statusCode());
            System.out.println("body:" + responseUpdate.body());

            String gsonInputStringCreate2 = "{\"title\": \"subtask2\", \"description\": \"description2\", \"epicId\": \"1\"}";
            byte[] jsonByteCreate2 = gsonInputStringCreate2.getBytes(StandardCharsets.UTF_8);
            HttpRequest requestCreate2 = HttpRequest.newBuilder()
                    .uri(urlSubtasks)
                    .header("Content-Type", "application/json; charset=utf-8")
                    .header("User-Agent", "Java HttpClient")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofByteArray(jsonByteCreate2))
                    .build();
            HttpResponse<String> responseCreate2 = client.send(requestCreate2, HttpResponse.BodyHandlers.ofString());
            System.out.println("status:" + responseCreate2.statusCode());
            System.out.println("body:" + responseCreate2.body());

            String gsonInputStringCreate3 = "{\"title\": \"subtask3\", \"description\": \"description3\", \"epicId\": \"2\"}";
            byte[] jsonByteCreate3 = gsonInputStringCreate3.getBytes(StandardCharsets.UTF_8);
            HttpRequest requestCreate3 = HttpRequest.newBuilder()
                    .uri(urlSubtasks)
                    .header("Content-Type", "application/json; charset=utf-8")
                    .header("User-Agent", "Java HttpClient")
                    .header("Accept", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofByteArray(jsonByteCreate3))
                    .build();
            HttpResponse<String> responseCreate3 = client.send(requestCreate3, HttpResponse.BodyHandlers.ofString());
            System.out.println("status:" + responseCreate3.statusCode());
            System.out.println("body:" + responseCreate3.body());

            System.out.println("*");
            System.out.println("*");
            System.out.println("Список до удаления:");
            HttpRequest requestGet = HttpRequest.newBuilder()
                    .uri(urlSubtasks)
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

            URI url2 = URI.create("http://localhost:8080/subtasks/3");
            HttpRequest requestDelete = HttpRequest.newBuilder()
                    .uri(url2)
                    .header("Content-Type", "application/json; charset=utf-8")
                    .header("User-Agent", "Java HttpClient")
                    .header("Accept", "application/json")
                    .DELETE()
                    .build();
            HttpResponse<String> responseDelete = client.send(requestDelete, HttpResponse.BodyHandlers.ofString());

            System.out.println("Удаленный элемент (id = 3):");
            System.out.println("status" + responseDelete.statusCode());
            System.out.println("body" + responseDelete.body());

            System.out.println("*");
            System.out.println("*");
            System.out.println("Список после удаления :");

            HttpResponse<String> responseGet2 = client.send(requestGet, HttpResponse.BodyHandlers.ofString());
            System.out.println("status" + responseGet2.statusCode());
            System.out.println("body" + responseGet2.body());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
