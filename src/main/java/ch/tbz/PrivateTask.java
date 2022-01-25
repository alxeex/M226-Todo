package ch.tbz;

public class PrivateTask extends Task{
    private String Star;

    /**
     * Construktor Private Task
     * @param id ID des Tasks
     * @param userId ID des Users
     * @param description Beschreibung des Tasks
     * @param title Title des Tasks
     * @param dueDate Fälligkeitsdatum des Tasks
     * @param star Stern überflüssig
     */
    public PrivateTask(int id, int userId, String description, String title, String dueDate, String star) {
        this.id = id;
        this.creator = userId;
        this.description = description;
        this.title = title;
        this.dueDate = dueDate;
        this.Star = star;
        this.assignedTask = false;
    }
}
