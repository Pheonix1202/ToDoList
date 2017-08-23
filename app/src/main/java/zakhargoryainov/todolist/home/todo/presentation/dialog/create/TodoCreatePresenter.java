package zakhargoryainov.todolist.home.todo.presentation.dialog.create;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import zakhargoryainov.todolist.app.TodoApplication;
import zakhargoryainov.todolist.data.DataInteractor;
import zakhargoryainov.todolist.entities.TodoNotation;
import zakhargoryainov.todolist.home.todo.OnCreateDialogDismissListener;

@InjectViewState
public class TodoCreatePresenter extends MvpPresenter<TodoCreateView> {

    @Inject
    DataInteractor dataInteractor;

    public TodoCreatePresenter() {
        TodoApplication.getAppComponent().inject(this);
    }

    public void addNewNotation(TodoNotation notation) {
        if (notation.getTitle().length() < 1) getViewState().onError("empty title isn't allowed");
        else if (notation.getDeadlineTimestamp() < System.currentTimeMillis()) getViewState().onError("you can't schedule your past");
        else dataInteractor.insertNotation(notation)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d("Room","Insertion completed"))
                .subscribe(() -> getViewState().onCreateSuccess());
    }

    public void reinitDialog(OnCreateDialogDismissListener listener){
        getViewState().setListener(listener);
    }
}
