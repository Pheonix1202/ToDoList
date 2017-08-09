package zakhargoryainov.todolist.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.firebase.database.Exclude;

/**
 * Created by Захар on 09.08.2017.
 */

@Entity(foreignKeys = @ForeignKey(entity = TodoUser.class,childColumns = "nickname",parentColumns = "nickname"))
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
    private String date;
    private boolean isDone;


    public TodoNotation(String title,  String date, int priority) {
        this.title = title;
        this.priority = priority;
        this.date = date;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
