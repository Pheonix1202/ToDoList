package zakhargoryainov.todolist.home.done.presentation;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import zakhargoryainov.todolist.app.TodoApplication;
import zakhargoryainov.todolist.data.DataInteractor;


@InjectViewState
public class DonePresenter extends MvpPresenter<DoneView> {

    @Inject
    DataInteractor dataInteractor;

    public DonePresenter() {
        TodoApplication.getAppComponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        dataInteractor.getDoneList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(notations -> getViewState().onDataChanged(notations),
                        throwable -> getViewState().onDataError(throwable.getMessage()));
    }

    public void deleteNotations(){
        dataInteractor.deleteNotations()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> getViewState().onSuccess());
    }

    public void showItems(){
        getViewState().showItems();
    }

    public void hideItems(){
        getViewState().hideItems();
    }

}
