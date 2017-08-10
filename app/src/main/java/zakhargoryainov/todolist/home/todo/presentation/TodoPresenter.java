package zakhargoryainov.todolist.home.todo.presentation;

import javax.inject.Inject;

import zakhargoryainov.todolist.app.TodoApplication;
import zakhargoryainov.todolist.entities.TodoNotation;
import zakhargoryainov.todolist.home.todo.TodoDialogInteractor;
import zakhargoryainov.todolist.home.todo.presentation.adapter.TodoRecyclerViewAdapter;

/**
 * Created by Захар on 02.08.2017.
 */

public class TodoPresenter {

    private TodoDialogInteractor interactor;

    @Inject
    public TodoPresenter(TodoDialogInteractor interactor) {
        //TodoApplication.getAppComponent().inject(this);
        this.interactor = interactor;
    }

    public void sendNotationInDialog(TodoNotation notation){
        interactor.setCurrentNotation(notation);
    }

    public void updateDatabases(TodoNotation notation){
        //todo update database after editing
    }


}
