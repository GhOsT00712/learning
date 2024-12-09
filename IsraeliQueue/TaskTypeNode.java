package IsraeliQueue;

import java.util.ArrayList;
import java.util.List;

public class TaskTypeNode {
    TaskType taskType;
    List<Task> tasks;

    public TaskTypeNode(TaskType taskType) {
        this.taskType = taskType;
        this.tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void processNode(){
        System.out.println("Processing tasks of type: " + taskType);
        for (Task task : tasks) {
            System.out.println("Processing task: " + task.name);
        }
        System.out.println("Finished processing tasks of type: " + taskType);
        this.tasks.clear();
    }

}
