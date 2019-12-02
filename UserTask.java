package TaskSchdeuler;

import java.util.LinkedList;
public class UserTask{
    private String userId;
    private LinkedList<Task> taskQueue;

    public UserTask(String userId, LinkedList<Task> taskQueue) {
        this.userId = userId;
        this.taskQueue = taskQueue;
    }

    public String getUserId() {
        return userId;
    }

    public LinkedList<Task> getTaskQueue() {
        return taskQueue;
    }

}
