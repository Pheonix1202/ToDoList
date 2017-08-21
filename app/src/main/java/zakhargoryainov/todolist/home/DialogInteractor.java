package zakhargoryainov.todolist.home;

import android.support.v4.util.Pair;

import javax.inject.Inject;
import javax.inject.Singleton;
import zakhargoryainov.todolist.entities.TodoNotation;


@Singleton
public class DialogInteractor {

    private TodoNotation currentNotation;

    @Inject
    public DialogInteractor() {}

    public TodoNotation getCurrentNotation(){
        return currentNotation;
    }

    public void setCurrentNotation(TodoNotation notation){
        currentNotation = notation;
    }
}
