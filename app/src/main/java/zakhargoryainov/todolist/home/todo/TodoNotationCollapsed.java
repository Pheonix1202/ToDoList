package zakhargoryainov.todolist.home.todo;

import java.util.Date;

/**
 * Created by Захар on 07.08.2017.
 */

public class TodoNotationCollapsed {
    private String title;
    private String date;
    private int priority;

    public TodoNotationCollapsed(String title, String date, int priority) {
        this.title = title;
        this.date = date;
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
