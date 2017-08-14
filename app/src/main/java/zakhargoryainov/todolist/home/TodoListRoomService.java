package zakhargoryainov.todolist.home;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;
import zakhargoryainov.todolist.database.TodoDatabase;
import zakhargoryainov.todolist.entities.TodoNotation;

/**
 * Created by Захар on 08.08.2017.
 */

@Singleton
public class TodoListRoomService {

    private TodoDatabase database;

    @Inject
    public TodoListRoomService(TodoDatabase database){
        this.database = database;
    }

    public Completable insertOrUpdateNotation(TodoNotation notation) {
        return Completable
                .fromAction(() -> database.todoDao().insertOrUpdateNotation(notation));
    }

    public Observable<List<TodoNotation>> getTodoList(){
        return database.todoDao().getNotations()
                .toObservable();
    }

    public Completable deleteNotations(List<TodoNotation> notations) {
        return Completable
                .fromAction(() -> database.todoDao().deleteNotations(notations));
    }



}
