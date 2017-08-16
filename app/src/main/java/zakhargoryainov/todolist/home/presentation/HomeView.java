package zakhargoryainov.todolist.home.presentation;

import com.arellomobile.mvp.MvpView;

interface  HomeView extends MvpView {

    void onAdditionSuccess();
    void onAdditionFailure();
}
