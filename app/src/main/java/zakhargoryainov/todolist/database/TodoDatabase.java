package zakhargoryainov.todolist.database;

import android.arch.persistence.room.RoomDatabase;

/**
 * Created by Захар on 09.08.2017.
 */

public abstract class TodoDatabase extends RoomDatabase {
    public abstract TodoDao todoDao();
}
