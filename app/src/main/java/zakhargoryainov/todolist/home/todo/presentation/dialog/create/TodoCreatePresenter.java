package zakhargoryainov.todolist.home.todo.presentation.dialog.create;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import zakhargoryainov.todolist.app.TodoApplication;
import zakhargoryainov.todolist.entities.TodoNotation;
import zakhargoryainov.todolist.data.TodoListInteractor;
import zakhargoryainov.todolist.home.todo.TodoDialogInteractor;

@InjectViewState
public class TodoCreatePresenter extends MvpPresenter<TodoCreateView> {

    @Inject  TodoDialogInteractor dialogInteractor;
    @Inject  TodoListInteractor todoListInteractor;

    public TodoCreatePresenter() {
        TodoApplication.getAppComponent().inject(this);
    }

    public TodoNotation getCurrentNotation(){
        TodoNotation notation = dialogInteractor.getCurrentNotation();
        return notation;
    }

    public void insertOrUpdateTodoNotation(TodoNotation notation) {
        todoListInteractor.insertOrUpdateNotation(notation)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d("Room","Insertion completed"))
                .subscribe(() -> getViewState().onSuccess());
    }
}
