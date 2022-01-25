package ch.tbz;

public class PrivateTask extends Task{
    private String Star;

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
