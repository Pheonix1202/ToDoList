package zakhargoryainov.todolist.data;

import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import io.reactivex.Completable;
import io.reactivex.Observable;
import zakhargoryainov.todolist.database.TodoDatabase;
import zakhargoryainov.todolist.entities.TodoNotation;


@Singleton
public class RoomStrategy {

    private TodoDatabase database;

    @Inject
    public RoomStrategy(TodoDatabase database){
        this.database = database;
    }

    public Completable insertOrUpdateNotation(TodoNotation notation) {
        return Completable
                .fromAction(() -> database.todoDao().insertOrUpdateNotation(notation));
    }

    public Observable<List<TodoNotation>> getTodoList(){
        return database.todoDao().getNotations(false)
                .toObservable();
    }

    public Observable<List<TodoNotation>> getDoneList(){
        return database.todoDao().getNotations(true)
                .toObservable();
    }


    public Completable deleteDoneNotations() {
        return Completable
                .fromAction(() -> database.todoDao().deleteNotations(true));
    }

    public Completable deleteNotation(TodoNotation notation){
        return Completable
                .fromAction(() -> database.todoDao().deleteNotation(notation));
    }


}
