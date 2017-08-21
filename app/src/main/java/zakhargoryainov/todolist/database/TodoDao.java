package zakhargoryainov.todolist.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import java.util.List;
import io.reactivex.Flowable;
import io.reactivex.Single;
import zakhargoryainov.todolist.entities.TodoNotation;

@Dao
public interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertOrUpdateNotation (TodoNotation notation);

    @Query("Select * from TodoNotation where isDone = :isDone ")
    Flowable<List<TodoNotation>> getNotations(boolean isDone);

    @Query("Delete from TodoNotation where isDone = :isDone")
    void deleteNotations(boolean isDone);

    @Query("Select  * from TodoNotation where isDone = :isDone and deadlineTimestamp < :currentTimestamp")
    Flowable<List<TodoNotation>> getExpiredNotations(boolean isDone, long currentTimestamp);

    @Delete
    void deleteNotation(TodoNotation notation);

}
