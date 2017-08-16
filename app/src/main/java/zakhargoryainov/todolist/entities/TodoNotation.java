package zakhargoryainov.todolist.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import com.google.firebase.database.Exclude;
import lombok.Data;

@Entity
public @Data class TodoNotation {

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
}
