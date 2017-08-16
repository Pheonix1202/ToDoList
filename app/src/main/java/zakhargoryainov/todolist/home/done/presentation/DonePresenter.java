package zakhargoryainov.todolist.home.done.presentation;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import zakhargoryainov.todolist.app.TodoApplication;
import zakhargoryainov.todolist.data.TodoListInteractor;


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
        todoListInteractor.getDoneList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(notations -> getViewState().onDataChanged(notations),
                        throwable -> getViewState().onDataError(throwable.getMessage()));
    }

    public void deleteNotations(){
        todoListInteractor.deleteNotations()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> getViewState().onSuccess());
    }

}
