package zakhargoryainov.todolist.home.todo.presentation;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

/**
 * Created by Захар on 02.08.2017.
 */

@StateStrategyType(SingleStateStrategy.class)
public interface TodoView  extends MvpView {

}
