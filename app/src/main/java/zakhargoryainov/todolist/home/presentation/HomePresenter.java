package zakhargoryainov.todolist.home.presentation;

import com.arellomobile.mvp.MvpPresenter;

import javax.inject.Inject;

import zakhargoryainov.todolist.app.TodoApplication;
import zakhargoryainov.todolist.data.TodoListInteractor;


public class HomePresenter extends MvpPresenter<HomeView> {
    @Inject
    TodoListInteractor interactor;

    public HomePresenter() {
        TodoApplication.getAppComponent().inject(this);
    }
}
