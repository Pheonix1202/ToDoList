package zakhargoryainov.todolist.home.done.presentation.dialog.details;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import zakhargoryainov.todolist.entities.TodoNotation;
import zakhargoryainov.todolist.home.done.OnDoneDialogDismissListener;


@StateStrategyType(AddToEndSingleStrategy.class)
public interface DoneDetailsView extends MvpView{
    void extractData(TodoNotation notation);
    void onRetrieveSuccess();
    void onDeleteSuccess();
    void onError(String message);
    void setListener(OnDoneDialogDismissListener listener);
    void setPosition(int position);
}
