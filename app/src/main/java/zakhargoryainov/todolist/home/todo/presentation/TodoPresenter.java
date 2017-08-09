package zakhargoryainov.todolist.home.todo.presentation;

import zakhargoryainov.todolist.entities.TodoNotation;

/**
 * Created by Захар on 02.08.2017.
 */

public class TodoPresenter {

    private TodoNotation currentNotation;

    public void setCurrentNotation(TodoNotation notation){
        currentNotation = notation;
    }

    public TodoNotation getCurrentNotation(){
        TodoNotation resultNotation = currentNotation;
        currentNotation = null;
        return resultNotation;
    }

    public void updateDatabases(TodoNotation notation){

    }


}
