package IsraeliQueue;

import java.util.ArrayList;
import java.util.List;

public class TaskFactory {
    public List<Task> createNTasks(int n) {
        List<Task> tasks = new ArrayList<>(n);
        for (int idx = 0; idx < n; idx++) {
            tasks.add(new Task("Task" + (idx + 1), getRandomTaskType()));
        }
        return tasks;
    }

    private TaskType getRandomTaskType() {
        int random = (int) ((Math.random() * 10) % 3);
        switch (random) {
            case 0:
                return TaskType.TYPE1;
            case 1:
                return TaskType.TYPE2;
            case 2:
                return TaskType.TYPE3;
            default:
                return TaskType.TYPE1;
        }
    }
}
