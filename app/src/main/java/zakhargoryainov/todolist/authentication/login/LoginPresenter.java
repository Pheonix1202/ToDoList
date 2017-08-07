package zakhargoryainov.todolist.authentication.login;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;


import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import zakhargoryainov.todolist.app.ToDoApplication;
import zakhargoryainov.todolist.todo.authentication.AuthInteractor;
import zakhargoryainov.todolist.authentication.login.LoginView;


/**
 * Created by Захар on 01.08.2017.
 */

@InjectViewState
public class LoginPresenter extends MvpPresenter<LoginView> {

    public static final String TAG = "LoginPresenter";

    @Inject
    AuthInteractor interactor;

    public LoginPresenter() {
        ToDoApplication.getAppComponent().inject(this);
    }

    public void signIn(String email, String password){
        interactor.signIn(email,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> getViewState().showProgress())
                .doFinally(() -> getViewState().hideProgress())
                .subscribe(() -> getViewState().onSuccessSignIn(),
                        throwable -> getViewState().showError(throwable.getMessage()));

    }
}
