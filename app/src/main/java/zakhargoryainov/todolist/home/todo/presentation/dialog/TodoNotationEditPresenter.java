package zakhargoryainov.todolist.home.todo.presentation.dialog;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import javax.inject.Inject;

import zakhargoryainov.todolist.app.TodoApplication;
import zakhargoryainov.todolist.entities.TodoNotation;
import zakhargoryainov.todolist.home.todo.TodoDialogInteractor;

@InjectViewState
public class TodoNotationEditPresenter extends MvpPresenter<TodoNotationEditView> {

    @Inject
    public TodoDialogInteractor interactor;

    public TodoNotationEditPresenter() {
        TodoApplication.getAppComponent().inject(this);
    }

    public TodoNotation getCurrentNotation(){
        TodoNotation notation = interactor.getCurrentNotation();
        getViewState().extractData(notation);
        return notation;
    }

}
