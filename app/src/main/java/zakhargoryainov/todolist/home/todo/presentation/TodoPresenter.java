package zakhargoryainov.todolist.home.todo.presentation;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import javax.inject.Inject;

import zakhargoryainov.todolist.app.TodoApplication;
import zakhargoryainov.todolist.entities.TodoNotation;
import zakhargoryainov.todolist.home.todo.TodoDialogInteractor;


@InjectViewState
public class TodoPresenter extends MvpPresenter<TodoView> {

    @Inject
    TodoDialogInteractor interactor;


    public TodoPresenter() {
        TodoApplication.getAppComponent().inject(this);
    }

    public void sendNotationInDialog(TodoNotation notation){
        interactor.setCurrentNotation(notation);
    }

    public void updateDatabases(TodoNotation notation){
        //todo update database after editing
    }


}
