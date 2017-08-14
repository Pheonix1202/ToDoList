package zakhargoryainov.todolist.home.todo.presentation.dialog.create;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.SkipStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

import zakhargoryainov.todolist.entities.TodoNotation;

/**
 * Created by Захар on 09.08.2017.
 */

@StateStrategyType(AddToEndSingleStrategy.class)
public interface TodoCreateView extends MvpView{
    void extractData(TodoNotation notation);
    void onSuccess();
}
