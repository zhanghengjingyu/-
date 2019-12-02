package TaskSchdeuler;
import java.util.LinkedList;

public class EqualScheduleBlockingQueue {
    private LinkedList<UserTask> userTaskList;
    public static int point = 0;

    public EqualScheduleBlockingQueue(LinkedList<UserTask> userTaskList) {
        this.userTaskList = userTaskList;
    }

    public LinkedList<UserTask> getUserTaskList() {
        return userTaskList;
    }

    /**
     * 向循环队列添加任务
     * @param task
     * @return
     */
    public synchronized boolean put(Task task){
        for (UserTask userTask: this.userTaskList) {
            if(task.getUserId().equals(userTask.getUserId())){
                userTask.getTaskQueue().add(task);
                return true;
            }
        }
        //创建新的节点
        UserTask newUserTask = new UserTask(task.getUserId(),new LinkedList<>());
        newUserTask.getTaskQueue().add(task);
        this.userTaskList.add(newUserTask);
        return true;
    }

    /**
     * 从循环队列中取任务
     * @return
     */
    public synchronized Task get(){
        while(true){
            //判断当前位置是否越界
            int currentLen = this.userTaskList.size();
            if( currentLen <= this.point){
                if(currentLen == 0 || currentLen == 1)
                    this.point =0;
                else
                    this.point = this.point % currentLen;
            }
            //如果想要同步的话，这个while操作会一直占用，直到拿到task为止，如果想要加锁的话，这个时候就不能put了，该怎么做
            UserTask userTask = this.userTaskList.get(this.point);
            if(userTask != null){
                for (Task task: userTask.getTaskQueue()) {
                    if(!task.getStatus().equals("1")){
                        this.point ++;
                        return task;
                    }
                }
                //如果任务队列里面都是running，按理说应该是在从新拿，point++；
                this.point ++;
            }
        }
    }

    /**
     * 删除执行完毕的任务
     * @param task
     * @return
     */
    public synchronized boolean remove(Task task){
        for (UserTask userTask: this.userTaskList) {
            if(userTask.getUserId().equals(task.getUserId())){
                for (Task tmptask : userTask.getTaskQueue()){
                    if(tmptask.getTaskId().equals(task.getTaskId()))
                        userTask.getTaskQueue().remove(task);
                }
                if(userTask.getTaskQueue() == null){
                    this.userTaskList.remove(userTask);
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Task task1 = new TaskImpl("task1","user1","2");
        Task task2 = new TaskImpl("task2","user2","1");
        Task task3 = new TaskImpl("task3","user3","1");
        Task task4 = new TaskImpl("task4","user3","2");
        EqualScheduleBlockingQueue equalScheduleBlockingQueue = new EqualScheduleBlockingQueue(new LinkedList<>());
        equalScheduleBlockingQueue.put(task1);
        equalScheduleBlockingQueue.put(task2);
        equalScheduleBlockingQueue.put(task3);

        for (UserTask userTask: equalScheduleBlockingQueue.userTaskList) {
                System.out.println(userTask.getUserId());
        }
        System.out.println("-----------------------------------------");
        System.out.println(equalScheduleBlockingQueue.get().getTaskId());
        System.out.println(equalScheduleBlockingQueue.point);
        System.out.println(equalScheduleBlockingQueue.get().getTaskId());
        System.out.println(equalScheduleBlockingQueue.point);
        System.out.println(equalScheduleBlockingQueue.get().getTaskId());
        System.out.println(equalScheduleBlockingQueue.point);
        System.out.println(equalScheduleBlockingQueue.get().getTaskId());
        System.out.println(equalScheduleBlockingQueue.point);
    }


}

