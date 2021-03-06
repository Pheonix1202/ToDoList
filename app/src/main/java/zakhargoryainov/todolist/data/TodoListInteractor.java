package zakhargoryainov.todolist.data;

import java.util.List;
import javax.inject.Inject;
import io.reactivex.Completable;
import io.reactivex.Observable;
import zakhargoryainov.todolist.entities.TodoNotation;


public class TodoListInteractor {

    private TodoListRoomService roomService;
    private TodoListFirebaseDatabaseService firebaseService;

    @Inject
    public TodoListInteractor(TodoListRoomService roomService,
                              TodoListFirebaseDatabaseService firebaseService) {
        this.roomService = roomService;
        this.firebaseService = firebaseService;
    }

    public Completable insertOrUpdateNotation(TodoNotation notation){
         return roomService.insertOrUpdateNotation(notation);
    }

    public Observable<List<TodoNotation>> getTodoList(){
        return roomService.getTodoList();
    }

    public Observable<List<TodoNotation>> getDoneList(){
        return roomService.getDoneList();
    }

    public Completable deleteNotations(){
        return  roomService.deleteDoneNotations();
    }

    public Completable deleteNotation(TodoNotation notation){
        return roomService.deleteNotation(notation);
    }


}
