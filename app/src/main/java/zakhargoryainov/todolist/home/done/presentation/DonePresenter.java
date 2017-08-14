package zakhargoryainov.todolist.home.done.presentation;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import zakhargoryainov.todolist.app.TodoApplication;
import zakhargoryainov.todolist.entities.TodoNotation;
import zakhargoryainov.todolist.home.TodoListInteractor;


@InjectViewState
public class DonePresenter extends MvpPresenter<DoneView> {

    @Inject
    TodoListInteractor todoListInteractor;

    public DonePresenter() {
        TodoApplication.getAppComponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
//        todoListInteractor.getTodoList()
//                .subscribe(notations -> getViewState().onDataChanged(notations),
//                        throwable -> getViewState().onDataError(throwable.getMessage()));
    }

    public void deleteNotation(List<TodoNotation> notations){
        todoListInteractor.deleteNotations(notations)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnComplete(() -> Log.d("Room","Deleting completed"))
                .subscribe(() -> getViewState().onSuccess());
    }

}
