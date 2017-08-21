package zakhargoryainov.todolist.di;

import android.content.Context;
import javax.inject.Singleton;
import dagger.Component;
import zakhargoryainov.todolist.authentication.AuthService;
import zakhargoryainov.todolist.authentication.registration.RegistrationPresenter;
import zakhargoryainov.todolist.data.TodoListRoomService;
import zakhargoryainov.todolist.database.TodoDatabase;
import zakhargoryainov.todolist.di.modules.FirebaseModule;
import zakhargoryainov.todolist.di.modules.ContextModule;
import zakhargoryainov.todolist.di.modules.DatabaseModule;
import zakhargoryainov.todolist.home.done.presentation.DoneFragment;
import zakhargoryainov.todolist.home.done.presentation.DonePresenter;
import zakhargoryainov.todolist.home.done.presentation.dialog.details.DoneDetailsPresenter;
import zakhargoryainov.todolist.home.presentation.HomePresenter;
import zakhargoryainov.todolist.home.todo.presentation.TodoFragment;
import zakhargoryainov.todolist.authentication.AuthInteractor;
import zakhargoryainov.todolist.authentication.login.LoginPresenter;
import zakhargoryainov.todolist.home.todo.presentation.TodoPresenter;
import zakhargoryainov.todolist.home.todo.presentation.dialog.create.TodoCreateDialogFragment;
import zakhargoryainov.todolist.home.todo.presentation.dialog.create.TodoCreatePresenter;
import zakhargoryainov.todolist.home.todo.presentation.dialog.details.TodoDetailsPresenter;


@Singleton
@Component(modules = {FirebaseModule.class, ContextModule.class, DatabaseModule.class})
public interface AppComponent  {
    Context getContext();
    TodoDatabase getTodoDatabase();
    TodoListRoomService provideRoom();

    void inject(LoginPresenter loginPresenter);
    void inject(RegistrationPresenter registrationPresenter);
    void inject(TodoFragment fragment);
    void inject(DoneFragment fragment);
    void inject(TodoCreateDialogFragment dialogFragment);
    void inject(TodoCreatePresenter todoCreatePresenter);
    void inject(TodoPresenter todoPresenter);
    void inject(HomePresenter homePresenter);
    void inject(DonePresenter donePresenter);
    void inject(TodoDetailsPresenter todoDetailsPresenter);
    void inject(DoneDetailsPresenter doneDetailsPresenter);

}
