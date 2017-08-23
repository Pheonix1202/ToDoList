package zakhargoryainov.todolist.data;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.Observable;
import zakhargoryainov.todolist.entities.TodoNotation;


public class DataInteractor {

//    private RoomStrategy roomService;
//    private FirebaseStrategy firebaseService;
    private DataServiceStrategy strategy;


    @Inject
    public DataInteractor(RoomStrategy roomStrategy,
                          FirebaseStrategy firebaseService) {
//        this.roomStrategy = roomStrategy;
//        this.firebaseService = firebaseService;
    }

    public Completable insertNotation(TodoNotation notation) {
        return strategy.insert(notation);
    }

    public Completable updateNotation(TodoNotation notation) {
        return strategy.update(notation);
    }

    public Observable<List<TodoNotation>> getTodoList() {
        return strategy.getTodoList();
    }

    public Observable<List<TodoNotation>> getDoneList() {
        return strategy.getDoneList();
    }

    public Completable deleteNotations() {
        return strategy.deleteDoneNotations();
    }

    public Completable deleteNotation(TodoNotation notation) {
        return strategy.delete(notation);
    }
}
