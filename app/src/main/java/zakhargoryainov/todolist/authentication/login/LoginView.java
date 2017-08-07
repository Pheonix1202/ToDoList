package zakhargoryainov.todolist.authentication.login;


import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;



@StateStrategyType(AddToEndStrategy.class)
public interface LoginView extends MvpView {

    void onSuccessSignIn();

    void showError(String message);

    @StateStrategyType(SkipStrategy.class)
    void showProgress();

    @StateStrategyType(SkipStrategy.class)
    void hideProgress();

}
