package ch.tbz;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public abstract class Task {
    @Getter
    @Setter
    protected int id;
    @Getter
    @Setter
    protected String description;
    @Getter
    @Setter
    protected String title;
    @Getter
    @Setter
    protected int creator;
    @Getter
    @Setter
    protected String dueDate;
    @Getter
    @Setter
    public boolean assignedTask;
}
