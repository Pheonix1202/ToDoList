package zakhargoryainov.todolist.home.done.presentation;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import java.util.List;

import zakhargoryainov.todolist.entities.TodoNotation;

/**
 * Created by Захар on 02.08.2017.
 */

@StateStrategyType(SingleStateStrategy.class)
public interface DoneView extends MvpView{
    void onDataChanged(List<TodoNotation> notations);
    void onDataError(String message);
    void onSuccess();
    void hideItems();
    void showItems();
}
