package zakhargoryainov.todolist.home;

import com.google.firebase.database.FirebaseDatabase;

import io.reactivex.Completable;
import zakhargoryainov.todolist.entities.TodoNotation;


public class TodoListFirebaseDatabaseService {

    private final FirebaseDatabase database;

    public TodoListFirebaseDatabaseService(FirebaseDatabase database) {
        this.database = database;
    }

    public Completable addTodoNotation(TodoNotation notation){
        return null;
    }

    public Completable setTodoNotationIsDone(TodoNotation notation){
        return null;
    }

}
