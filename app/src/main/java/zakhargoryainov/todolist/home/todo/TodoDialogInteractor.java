package zakhargoryainov.todolist.home.todo;

import javax.inject.Inject;
import javax.inject.Singleton;

import zakhargoryainov.todolist.entities.TodoNotation;

/**
 * Created by Захар on 09.08.2017.
 */
@Singleton
public class TodoDialogInteractor {

    private TodoNotation currentNotation;

    @Inject
    public TodoDialogInteractor() {
    }

    public TodoNotation getCurrentNotation(){
        TodoNotation resultNotation = currentNotation;
        return resultNotation;
    }

    public void setCurrentNotation(TodoNotation notation){
        currentNotation = notation;
    }
}
