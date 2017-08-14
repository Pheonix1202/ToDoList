package zakhargoryainov.todolist.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import zakhargoryainov.todolist.entities.TodoNotation;

@Dao
public interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrUpdateNotation (TodoNotation notation);

    @Query("Select * from TodoNotation")
    Flowable<List<TodoNotation>> getNotations();

    @Delete
    void deleteNotations(List<TodoNotation> notations);

}
