package managers;

import classes.tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryHistoryManager<T> implements HistoryManager {

    private Map<Integer, Node<T>> mapHistory;
    private Node<T> head;
    private Node<T> tail;

    public InMemoryHistoryManager() {
        mapHistory = new HashMap<>();
        head = null;
        tail = null;
    }

    @Override
    public void add(Task task) {
        Node<Task> newNode = new Node<>(task);
        if (head == null) {
            head = (Node<T>) newNode;
            tail = (Node<T>) newNode;
        } else {
            tail.next = (Node<T>) newNode;
            newNode.prev = (Node<Task>) tail;
            tail = (Node<T>) newNode;
        }
        mapHistory.put(task.getId(), (Node<T>) newNode);
    }

    @Override
    public List<Task> getHistory() {
        List<Task> taskList = new ArrayList<>();
        for (Node<T> task : mapHistory.values()) {
            taskList.add((Task) task.data);
        }
        return taskList;
    }

    public void remove(int id) {
        mapHistory.remove(id);
    }


}

class Node<T> {
    public T data;
    public Node<T> next;
    public Node<T> prev;

    public Node(T data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}
