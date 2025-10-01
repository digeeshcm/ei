package astronautscheduler;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        ScheduleManager manager = ScheduleManager.getInstance();
        ConflictObserver conflictObserver = new ConflictObserver(manager.getInstance().getTasks());

        manager.addObserver(conflictObserver);

        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Astronaut Daily Schedule ===");
            System.out.println("1. Add Task");
            System.out.println("2. Remove Task");
            System.out.println("3. View Tasks");
            System.out.println("4. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Description: ");
                    String desc = sc.nextLine();
                    System.out.print("Start Time (HH:mm): ");
                    String start = sc.nextLine();
                    System.out.print("End Time (HH:mm): ");
                    String end = sc.nextLine();
                    System.out.print("Priority (High/Medium/Low): ");
                    String priority = sc.nextLine();

                    Task task = TaskFactory.createTask(desc, start, end, priority);
                    manager.addTask(task);
                    break;

                case 2:
                    System.out.print("Enter task description to remove: ");
                    String rem = sc.nextLine();
                    manager.removeTask(rem);
                    break;

                case 3:
                    manager.viewTasks();
                    break;

                case 4:
                    System.out.println("👋 Exiting...");
                    return;

                default:
                    System.out.println("❌ Invalid choice.");
            }
        }
    }
}
