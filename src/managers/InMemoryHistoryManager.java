package managers;

import classes.tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryHistoryManager implements HistoryManager {

    private Map<Integer, Node> historyMap;
    private Node head;
    private Node tail;
    private List<Task> historyAdd;

    public InMemoryHistoryManager() {
        historyMap = new HashMap<>();
        head = null;
        tail = null;
        historyAdd = new ArrayList<>();
    }

    @Override
    public void add(Task task) {
        Node existingNode = historyMap.get(task.getId());
        if (existingNode != null) {
            removeNode(existingNode);
        }
        historyAdd.add(task);
        linkLast(task);
    }

    @Override
    public List<Task> getHistory() {
        return getTasks();
    }

    public void remove(int id) {
        if (historyMap.get(id) != null) {
            removeNode(historyMap.get(id));
        }
    }

    public void linkLast(Task task) {
        Node newNode = new Node(task);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        historyMap.put(task.getId(), newNode);
    }

    public List<Task> getTasks() {
        List<Task> historyTasks = new ArrayList<>();
        for (Node node : historyMap.values()) {
            historyTasks.add(node.data);
        }
        return historyTasks;
    }

    public void removeNode(Node node) {
        if (node == null) {
            return;
        }

        if (historyMap.get(node.data.getId()).prev == null) {
            if (historyMap.get(node.data.getId()).next != null) {
                historyMap.get(node.data.getId()).next.prev = null;
                head = historyMap.get(node.data.getId()).next;
            } else {
                head = null;
                tail = null;
            }
        } else if (historyMap.get(node.data.getId()).next == null) {
            historyMap.get(node.data.getId()).prev.next = null;
            tail = historyMap.get(node.data.getId()).prev;
        } else {
            historyMap.get(node.data.getId()).prev.next = historyMap.get(node.data.getId()).next;
            historyMap.get(node.data.getId()).next.prev = historyMap.get(node.data.getId()).prev;
        }
        historyMap.remove(node.data.getId());
    }

}

class Node {
    public Task data;
    public Node next;
    public Node prev;

    public Node(Task data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}
