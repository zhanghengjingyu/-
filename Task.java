package TaskSchdeuler;

public interface Task {

    //获取任务状态:Runniing , Finished，Watting
    public String getStatus();

    //获取任务的id
    public String getTaskId();

    //获取用户id
    public String getUserId();

}
