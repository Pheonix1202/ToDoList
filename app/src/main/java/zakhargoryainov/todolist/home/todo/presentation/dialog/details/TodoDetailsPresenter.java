package zakhargoryainov.todolist.home.todo.presentation.dialog.details;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import javax.inject.Inject;
import zakhargoryainov.todolist.app.TodoApplication;
import zakhargoryainov.todolist.entities.TodoNotation;
import zakhargoryainov.todolist.home.TodoListInteractor;
import zakhargoryainov.todolist.home.todo.TodoDialogInteractor;


@InjectViewState
public class TodoDetailsPresenter extends MvpPresenter<TodoDetailsView> {

    @Inject TodoDialogInteractor todoDialogInteractor;
    @Inject TodoListInteractor todoListInteractor;

    public TodoDetailsPresenter(){
        TodoApplication.getAppComponent().inject(this);
    }

    public TodoNotation getCurrentNotation(){
        TodoNotation notation = todoDialogInteractor.getCurrentNotation();
        getViewState().extractData(notation);
        return notation;
    }

}
