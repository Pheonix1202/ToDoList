package zakhargoryainov.todolist.home.todo.presentation.dialog.details;

import com.arellomobile.mvp.MvpView;

import zakhargoryainov.todolist.entities.TodoNotation;

/**
 * Created by Захар on 14.08.2017.
 */

public interface TodoDetailsView extends MvpView {
    void extractData(TodoNotation notation);
    void onSuccess();
}
