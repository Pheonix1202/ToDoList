package zakhargoryainov.todolist.authentication.registration;

import android.support.annotation.StringRes;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;


@StateStrategyType(AddToEndStrategy.class)
public interface RegistrationView extends MvpView {

    void onSuccessSignUp();

    void showError(@StringRes int message);

    void showError(String message);

    void showProgress();

    void hideProgress();
}
