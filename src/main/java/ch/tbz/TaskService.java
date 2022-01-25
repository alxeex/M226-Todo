package ch.tbz;

import lombok.Getter;

import java.util.ArrayList;


public class TaskService {

    @Getter
    private final ArrayList<Task> allTasks = new ArrayList<>();

    @Getter
    private final ArrayList<Task> currentTasks = new ArrayList<>();

    public AssignedTask createAssignedTask(int id, int userId, int asignto, String description, String title, String dueDate) {
        AssignedTask assignedTask = new AssignedTask(id, userId, asignto, description, title, dueDate);
        allTasks.add(assignedTask);
        return assignedTask;
    }

    public PrivateTask createPrivate(int id, int userId, String star, String description, String title, String dueDate) {
        PrivateTask privateTask = new PrivateTask(id, userId, description, title, dueDate, star);
        allTasks.add(privateTask);
        return privateTask;
    }

    public Task editTask(int id, String description, String title, String dueDate) {
        getTask(id).setDescription(description);
        getTask(id).setTitle(title);
        getTask(id).setDueDate(dueDate);
        return getTask(id);
    }

    public Task getTask(int id) {
        for (Task task : allTasks) {
            if (task.getId() == (id)) {
                return task;
            }
        }
        System.out.println("Diese Task wurde nicht gefunden");
        return null;
    }

    public ArrayList<Task> getTasks(int userid) {
        this.currentTasks.clear();
        for (Task task : allTasks) {
            if (task.getCreator() == (userid)) {
                this.currentTasks.add(task);
            }
        }
        return this.currentTasks;
    }


    public void deleteTask(int id) {
        allTasks.remove(getTask(id));
    }

    public void assignUserToTask(AssignedTask task, int userId) {
        task.setAssignto(userId);
    }

}
