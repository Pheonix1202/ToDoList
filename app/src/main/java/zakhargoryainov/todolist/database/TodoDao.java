package zakhargoryainov.todolist.database;

import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;
import io.reactivex.Flowable;
import zakhargoryainov.todolist.entities.TodoNotation;


public interface TodoDao {

    @Insert
    void insertNotation (TodoNotation notation);


    @Query("Select * from TodoNotation where nickname =:nickname")
    Flowable<List<TodoNotation>> getNotations(String nickname);


}
