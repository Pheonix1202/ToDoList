package zakhargoryainov.todolist.database;

import android.arch.persistence.room.RoomDatabase;


public abstract class TodoDatabase extends RoomDatabase {
    public abstract TodoDao todoDao();
}
