package ch.tbz;

import lombok.Getter;
import lombok.Setter;

public class AssignedTask extends Task {
    @Getter
    @Setter
    private int assignto;

    /**
     * Construktor Assigned Task
     * @param id ID des Task
     * @param newUserId User ID
     * @param assignto ID des zugewiesenen Users
     * @param description Beschreibung des Tasks
     * @param title Titel des Tasks
     * @param dueDate Fälligkeitsdatum des Tasks
     */
    public AssignedTask(int id, int newUserId, int assignto, String description, String title, String dueDate) {
        this.id = id;
        this.description = description;
        this.assignto = assignto;
        this.title = title;
        this.dueDate = dueDate;
        this.assignedTask = true;
        this.creator = newUserId;
    }
}