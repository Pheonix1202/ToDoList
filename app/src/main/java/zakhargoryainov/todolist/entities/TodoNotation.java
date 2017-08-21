package zakhargoryainov.todolist.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import com.google.firebase.database.Exclude;

@Entity
public class TodoNotation {

    @Exclude
    @Ignore
    public static final int TIER_1 = 1,
            TIER_2 = 2,
            TIER_3 = 3,
            TIER_4 = 4;

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nickname;
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
    }

    public TodoNotation(){}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }



    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
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
