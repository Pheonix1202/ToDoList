package zakhargoryainov.todolist.authentication.login;


import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Захар on 01.08.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class)
public interface LoginView extends MvpView {

    @StateStrategyType(SkipStrategy.class)
    void onSuccessSignIn();

    void onFailedSignIn();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void showProgress();

    @StateStrategyType(AddToEndSingleStrategy.class)
    void hideProgress();

}
