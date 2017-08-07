package zakhargoryainov.todolist.authentication.registration;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Захар on 04.08.2017.
 */

@StateStrategyType(AddToEndStrategy.class)
public interface RegistrationView extends MvpView {

    void onSuccessSignUp();

    void showError(String message);

    void showProgress();

    void hideProgress();
}
