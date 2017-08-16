package zakhargoryainov.todolist.home.todo.presentation;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import javax.inject.Inject;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import zakhargoryainov.todolist.app.TodoApplication;
import zakhargoryainov.todolist.entities.TodoNotation;
import zakhargoryainov.todolist.data.TodoListInteractor;
import zakhargoryainov.todolist.home.todo.TodoDialogInteractor;


@InjectViewState
public class TodoPresenter extends MvpPresenter<TodoView> {

    @Inject
    TodoDialogInteractor interactor;
    @Inject TodoListInteractor todoListInteractor;

    public TodoPresenter() {
        TodoApplication.getAppComponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        todoListInteractor.getTodoList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(notations -> getViewState().onDataChanged(notations),
                        throwable -> getViewState().onDataError(throwable.getMessage()));
    }

    public void prepareDialogForNewNotation() {
        interactor.setCurrentNotation(new TodoNotation());
    }

    public void sendNotationToDialog(TodoNotation notation){
        interactor.setCurrentNotation(notation);
    }

    //todo повесить обработчики на итемы с выделением
}
