package zakhargoryainov.todolist.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import lombok.Getter;
import lombok.Setter;

@Entity
public class TodoUser {

    @PrimaryKey
    @Getter @Setter private String nickname;

    public TodoUser(String nickname) {
        this.nickname = nickname;
    }
}
