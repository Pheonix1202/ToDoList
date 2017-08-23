package zakhargoryainov.todolist.authentication.login;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import zakhargoryainov.todolist.app.TodoApplication;
import zakhargoryainov.todolist.authentication.AuthInteractor;


@InjectViewState
public class LoginPresenter extends MvpPresenter<LoginView> {

    @Inject AuthInteractor interactor;

    public LoginPresenter() {
        TodoApplication.getAppComponent().inject(this);
    }

    public void signIn(String email, String password){
        interactor.signIn(email,password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(disposable -> getViewState().showProgress())
                .doFinally(() -> getViewState().hideProgress())
                .doOnComplete(() -> interactor.onSignIn(FirebaseAuth.getInstance().getCurrentUser()))
                .subscribe(() -> getViewState().onSuccessSignIn(),
                        throwable -> getViewState().showError(throwable.getMessage()));
    }
}
