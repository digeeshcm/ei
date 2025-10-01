package astronautscheduler;

import java.util.List;

public class ConflictObserver implements TaskObserver {
    private List<Task> tasks;

    public ConflictObserver(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public void onTaskAdded(Task newTask) {
        for (Task t : tasks) {
            if (isOverlapping(t, newTask)) {
                System.out.println("⚠️ Conflict: " + newTask.getDescription() +
                        " overlaps with existing task: " + t.getDescription());
                return;
            }
        }
    }

    private boolean isOverlapping(Task t1, Task t2) {
        return !(t1.getEndTime().compareTo(t2.getStartTime()) <= 0 ||
                 t2.getEndTime().compareTo(t1.getStartTime()) <= 0);
    }
}
