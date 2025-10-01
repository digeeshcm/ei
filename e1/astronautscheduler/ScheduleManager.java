package astronautscheduler;

import java.util.*;

public class ScheduleManager {
    private static ScheduleManager instance;
    private List<Task> tasks;
    private List<TaskObserver> observers;

    private ScheduleManager() {
        tasks = new ArrayList<>();
        observers = new ArrayList<>();
    }

    public static ScheduleManager getInstance() {
        if (instance == null) {
            instance = new ScheduleManager();
        }
        return instance;
    }

    public void addObserver(TaskObserver observer) {
        observers.add(observer);
    }

    public void addTask(Task task) {
        tasks.add(task);
        notifyObservers(task);
        LoggerUtil.log("Task added: " + task.getDescription());
    }

    public void removeTask(String description) {
        Task toRemove = null;
        for (Task t : tasks) {
            if (t.getDescription().equalsIgnoreCase(description)) {
                toRemove = t;
                break;
            }
        }
        if (toRemove != null) {
            tasks.remove(toRemove);
            System.out.println("✅ Task removed successfully.");
            LoggerUtil.log("Task removed: " + description);
        } else {
            System.out.println("❌ Task not found.");
        }
    }

    public void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks scheduled for the day.");
            return;
        }
        tasks.sort(Comparator.comparing(Task::getStartTime));
        for (Task t : tasks) {
            System.out.println(t);
        }
    }

    private void notifyObservers(Task task) {
        for (TaskObserver obs : observers) {
            obs.onTaskAdded(task);
        }
    }
}
