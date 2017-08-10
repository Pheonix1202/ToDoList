package zakhargoryainov.todolist.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import com.google.firebase.database.Exclude;
import lombok.Data;


@Entity(foreignKeys = @ForeignKey(entity = TodoUser.class,childColumns = "nickname",parentColumns = "nickname"))
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
    private String date;
    private boolean isDone;

    public TodoNotation(String title,  String date, int priority) {
        this.title = title;
        this.priority = priority;
        this.date = date;
    }
}
