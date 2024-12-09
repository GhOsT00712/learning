package IsraeliQueue;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        IsraeliQueue israeliQueue = new IsraeliQueue();
        List<Task> tasks = new TaskFactory().createNTasks(10);
        for (Task task : tasks) {
            israeliQueue.offer(task);
        }
        TaskType pooledTaskType = israeliQueue.poll();
        Task task = new Task("Task11", pooledTaskType);
        israeliQueue.offer(task);

        pooledTaskType = israeliQueue.poll();

    }
}
