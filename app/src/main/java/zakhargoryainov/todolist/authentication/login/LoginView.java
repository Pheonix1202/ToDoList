package zakhargoryainov.todolist.todo.authentication.login;


import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.AddToEndStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;


/**
 * Created by Захар on 01.08.2017.
 */

@StateStrategyType(AddToEndStrategy.class)
public interface LoginView extends MvpView {

    //@StateStrategyType(SkipStrategy.class)
    void onSuccessSignIn();

    void onFailedSignIn(String message); //todo mb showError?

    @StateStrategyType(AddToEndSingleStrategy.class) //todo skipStrategy
    void showProgress();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void hideProgress();

    void gotoRegistration();

    void gotoHome();

}
