package astronautscheduler;

public class TaskFactory {
    public static Task createTask(String desc, String start, String end, String priority) {
        return new Task(desc, start, end, priority);
    }
}
