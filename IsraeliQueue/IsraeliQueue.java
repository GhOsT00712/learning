package IsraeliQueue;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class IsraeliQueue {
    
    Map<TaskType, TaskTypeNode> map;
    Queue<TaskTypeNode> queue;

    public IsraeliQueue() {
        map = new HashMap<>();
        queue = new LinkedList<>();
    }

    public void offer(Task task) {
        TaskTypeNode node = map.get(task.taskType);
        if (node == null) {
            node = new TaskTypeNode(task.taskType);
            map.put(task.taskType, node);
            queue.offer(node);
        }
        node.addTask(task);
    }

    public TaskType poll(){
        TaskTypeNode node = queue.poll();
        if (node != null) {
            node.processNode();
        }
        map.remove(node.taskType);
        return node.taskType;
    }
    
}
