package zakhargoryainov.todolist.authentication.registration;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import javax.inject.Inject;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import zakhargoryainov.todolist.R;
import zakhargoryainov.todolist.app.TodoApplication;
import zakhargoryainov.todolist.authentication.AuthInteractor;


@InjectViewState
public class RegistrationPresenter extends MvpPresenter<RegistrationView> {

    @Inject
    AuthInteractor interactor;

    public RegistrationPresenter() {
        TodoApplication.getAppComponent().inject(this);
    }

    public void SignUp(String email, String password, String confirmPassword) {
        if (password.equals(confirmPassword))
            interactor.signUp(email, password)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe(disposable -> getViewState().showProgress())
                    .doFinally(() -> getViewState().hideProgress())
                    .subscribe(() -> getViewState().onSuccessSignUp(),
                            throwable -> getViewState().showError(throwable.getMessage()));

        else getViewState().showError(R.string.passwords_do_not_match);
    }

}
