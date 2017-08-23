package zakhargoryainov.todolist.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.provider.Settings;

import com.google.firebase.database.Exclude;

@Entity
public class TodoNotation {

    @PrimaryKey
    private String id;
    private String title;
    private String body;
    private int priority;
    private String formattedDeadline;
    private long deadlineTimestamp;
    private boolean isDone;
    private boolean isFailed;

    @Ignore
    public TodoNotation(String title, String formattedDeadline, int priority) {
        this.title = title;
        this.priority = priority;
        this.formattedDeadline = formattedDeadline;
        id = String.valueOf(System.currentTimeMillis());
    }

    public TodoNotation(){
        id = String.valueOf(System.currentTimeMillis());
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getFormattedDeadline() {
        return formattedDeadline;
    }

    public void setFormattedDeadline(String formattedDeadline) {
        this.formattedDeadline = formattedDeadline;
    }

    public long getDeadlineTimestamp() {
        return deadlineTimestamp;
    }

    public void setDeadlineTimestamp(long deadlineTimestamp) {
        this.deadlineTimestamp = deadlineTimestamp;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public boolean isFailed() {
        return isFailed;
    }

    public void setFailed(boolean failed) {
        isFailed = failed;
    }

}
