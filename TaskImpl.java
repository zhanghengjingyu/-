package TaskSchdeuler;

public class TaskImpl implements Task{

    private String taskId;
    private String userId;
    private String status;

    public TaskImpl() {}
    public TaskImpl(String taskId,String userId,String status) {
        this.taskId = taskId;
        this.userId = userId;
        this.status = status;
    }

    public String getTaskId() {
        return taskId;
    }
    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

}
