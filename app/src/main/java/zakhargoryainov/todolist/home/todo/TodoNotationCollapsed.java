package zakhargoryainov.todolist.home.todo;

import java.util.Date;

import zakhargoryainov.todolist.home.NotationCollapsed;

/**
 * Created by Захар on 07.08.2017.
 */

public class TodoNotationCollapsed extends NotationCollapsed {
    private int priority;

    public TodoNotationCollapsed(String title, String date, int priority) {
        super(title,date);
        this.priority = priority;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
