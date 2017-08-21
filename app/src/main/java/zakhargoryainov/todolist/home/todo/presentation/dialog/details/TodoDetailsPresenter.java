package zakhargoryainov.todolist.home.todo.presentation.dialog.details;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import javax.inject.Inject;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import zakhargoryainov.todolist.app.TodoApplication;
import zakhargoryainov.todolist.entities.TodoNotation;
import zakhargoryainov.todolist.data.TodoListInteractor;
import zakhargoryainov.todolist.home.todo.OnDetailsDialogDismissListener;


@InjectViewState
public class TodoDetailsPresenter extends MvpPresenter<TodoDetailsView> {

    @Inject TodoListInteractor todoListInteractor;
    private TodoNotation notation;

    public TodoDetailsPresenter(){
        TodoApplication.getAppComponent().inject(this);
    }

    public void reinitDialog(TodoNotation notation, int itemPosition, OnDetailsDialogDismissListener listener){
        this.notation = notation;
        getViewState().extractData(notation);
        getViewState().setListener(listener);
        getViewState().setPosition(itemPosition);
    }

    public void completeTodoNotation(){
        notation.setFailed(false);
        notation.setDone(true);
        todoListInteractor.insertOrUpdateNotation(notation)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> getViewState().onCompleteSuccess(),
                        throwable -> getViewState().onError(throwable.getMessage()));
    }

    public void deleteTodoNotation(){
        todoListInteractor.deleteNotation(notation)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(()->getViewState().onDeleteSuccess(),
                        throwable -> getViewState().onError(throwable.getMessage()));
    }
}
