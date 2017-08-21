package zakhargoryainov.todolist.home.done.presentation.dialog.details;

import android.support.v4.util.Pair;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import javax.inject.Inject;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import zakhargoryainov.todolist.app.TodoApplication;
import zakhargoryainov.todolist.data.TodoListInteractor;
import zakhargoryainov.todolist.entities.TodoNotation;
import zakhargoryainov.todolist.home.done.OnDoneDialogDismissListener;

@InjectViewState
public class DoneDetailsPresenter extends MvpPresenter<DoneDetailsView> {

    @Inject TodoListInteractor todoListInteractor;
    private TodoNotation notation;

    public DoneDetailsPresenter() {
        TodoApplication.getAppComponent().inject(this);
    }

    public void reinitDialog(TodoNotation notation, int itemPosition, OnDoneDialogDismissListener listener){
        this.notation = notation;
        getViewState().extractData(notation);
        getViewState().setListener(listener);
        getViewState().setPosition(itemPosition);
    }

    public void retrieveNotation() {
        notation.setFailed(false);
        notation.setDone(false);
        todoListInteractor.insertOrUpdateNotation(notation)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(()-> getViewState().onRetrieveSuccess())
                .doOnError(throwable -> getViewState().onError(throwable.getMessage()))
                .subscribe();
    }

    public void deleteNotation() {
        todoListInteractor.deleteNotation(notation)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(()->getViewState().onDeleteSuccess())
                .doOnError(throwable -> getViewState().onError(throwable.getMessage()))
                .subscribe();
    }
}
