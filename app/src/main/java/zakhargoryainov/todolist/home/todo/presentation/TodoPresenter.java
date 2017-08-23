package zakhargoryainov.todolist.home.todo.presentation;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import javax.inject.Inject;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import zakhargoryainov.todolist.app.TodoApplication;
import zakhargoryainov.todolist.data.DataInteractor;


@InjectViewState
public class TodoPresenter extends MvpPresenter<TodoView> {

    @Inject
    DataInteractor dataInteractor;

    public TodoPresenter() {
        TodoApplication.getAppComponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        getViewState().setOnFabClickListener();
        dataInteractor.getTodoList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(notations -> getViewState().onDataChanged(notations),
                        throwable -> getViewState().onDataError(throwable.getMessage()));
    }

    public void hideItems(){
        getViewState().hideItems();
    }

    public void showItems(){
        getViewState().showItems();
    }

}
