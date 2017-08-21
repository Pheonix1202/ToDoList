package zakhargoryainov.todolist.home.todo.presentation.dialog.details;

import com.arellomobile.mvp.MvpView;

import zakhargoryainov.todolist.entities.TodoNotation;
import zakhargoryainov.todolist.home.todo.OnDetailsDialogDismissListener;


public interface TodoDetailsView extends MvpView {
    void extractData(TodoNotation notation);
    void onCompleteSuccess();
    void onDeleteSuccess();
    void onError(String message);
    void setPosition(int position);
    void setListener(OnDetailsDialogDismissListener listener);
}
