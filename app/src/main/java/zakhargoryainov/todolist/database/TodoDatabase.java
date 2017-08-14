package zakhargoryainov.todolist.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import zakhargoryainov.todolist.entities.TodoNotation;
import zakhargoryainov.todolist.entities.TodoUser;

@Database(entities = {TodoNotation.class,TodoUser.class},version = 1)
public abstract class TodoDatabase extends RoomDatabase {
    public abstract TodoDao todoDao();
}
