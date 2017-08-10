package zakhargoryainov.todolist.home.todo;

import javax.inject.Inject;
import javax.inject.Singleton;
import zakhargoryainov.todolist.entities.TodoNotation;


@Singleton
public class TodoDialogInteractor {

    private TodoNotation currentNotation;

    @Inject
    public TodoDialogInteractor() {}

    public TodoNotation getCurrentNotation(){
        return currentNotation;
    }

    public void setCurrentNotation(TodoNotation notation){
        currentNotation = notation;
    }
}
