package zakhargoryainov.todolist.database;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import zakhargoryainov.todolist.entities.TodoNotation;

/**
 * Created by Захар on 09.08.2017.
 */

public interface TodoDao {

    @Insert
    Completable insertNotation (TodoNotation notation);

    @Delete
    Completable deleteNotations(TodoNotation... notations);

    @Query("Select * from TodoNotation where nickname =:nickname")
    Single<List<TodoNotation>> getNotations(String nickname);


}
