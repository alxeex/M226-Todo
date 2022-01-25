package ch.tbz;

import lombok.Getter;

import java.util.ArrayList;


public class TaskService {

    @Getter
    private final ArrayList<Task> allTasks = new ArrayList<>();

    @Getter
    private final ArrayList<Task> currentTasks = new ArrayList<>();

    /**
     * Assigned Task wird erstellt
     * @param id Task ID
     * @param userId User ID
     * @param asignto Zugewiesene User ID
     * @param description Beschreibung des Tasks
     * @param title Titel des Tasks
     * @param dueDate Fälligkeitsdatum des Tasks
     * @return Returnt den Assigned Task
     */
    public AssignedTask createAssignedTask(int id, int userId, int asignto, String description, String title, String dueDate) {
        AssignedTask assignedTask = new AssignedTask(id, userId, asignto, description, title, dueDate);
        allTasks.add(assignedTask);
        return assignedTask;
    }

    /**
     * Private Task wird erstellt
     * @param id ID des Tasks
     * @param userId ID des Users welcher den Task erstellt hat
     * @param star Stern überflüssig (könnte noch ergänzt werden)
     * @param description Beschreibung des Tasks
     * @param title Titel des Tasks
     * @param dueDate Fälligkeitsdatum des Tasks
     * @return Returnt den Private Task
     */
    public PrivateTask createPrivate(int id, int userId, String star, String description, String title, String dueDate) {
        PrivateTask privateTask = new PrivateTask(id, userId, description, title, dueDate, star);
        allTasks.add(privateTask);
        return privateTask;
    }

    /**
     * Task editieren
     * @param id ID des Tasks
     * @param description Beschreibung des Tasks
     * @param title Titel des Tasks
     * @param dueDate Fälligkeitsdatum des Tasks
     * @return Returnt die Task ID
     */
    public Task editTask(int id, String description, String title, String dueDate) {
        getTask(id).setDescription(description);
        getTask(id).setTitle(title);
        getTask(id).setDueDate(dueDate);
        return getTask(id);
    }

    /**
     * Getter Task
     * @param id Task ID
     * @return Returnt den Task
     */
    public Task getTask(int id) {
        for (Task task : allTasks) {
            if (task.getId() == (id)) {
                return task;
            }
        }
        System.out.println("Diese Task wurde nicht gefunden");
        return null;
    }

    /**
     * Getter für Array List Task
     * @param userid ID des Users
     * @return Returnt den behandelten Task
     */
    public ArrayList<Task> getTasks(int userid) {
        this.currentTasks.clear();
        for (Task task : allTasks) {
            if (task.getCreator() == (userid)) {
                this.currentTasks.add(task);
            }
        }
        return this.currentTasks;
    }


    /**
     * Task löschen
     * @param id ID des Tasks
     */
    public void deleteTask(int id) {
        allTasks.remove(getTask(id));
    }

    /**
     * Task einem User zuweisen
     * @param task Task
     * @param userId ID des Users
     */
    public void assignUserToTask(AssignedTask task, int userId) {
        task.setAssignto(userId);
    }

}
