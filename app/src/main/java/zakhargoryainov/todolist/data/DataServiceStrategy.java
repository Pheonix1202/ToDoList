package zakhargoryainov.todolist.data;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Observable;
import zakhargoryainov.todolist.entities.TodoNotation;

/**
 * Created by Захар on 22.08.2017.
 */

public interface DataServiceStrategy {

    Completable insert(TodoNotation notation);
    Completable update(TodoNotation notation);
    Completable delete(TodoNotation notation);
    Completable deleteDoneNotations();
    Observable<List<TodoNotation>> getTodoList();
    Observable<List<TodoNotation>> getDoneList();


}
