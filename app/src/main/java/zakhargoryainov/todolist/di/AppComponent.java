package zakhargoryainov.todolist.di;

import android.content.Context;
import javax.inject.Singleton;
import dagger.Component;
import zakhargoryainov.todolist.authentication.AuthService;
import zakhargoryainov.todolist.authentication.registration.RegistrationPresenter;
import zakhargoryainov.todolist.di.modules.AuthModule;
import zakhargoryainov.todolist.di.modules.ContextModule;
import zakhargoryainov.todolist.home.done.presentation.DoneFragment;
import zakhargoryainov.todolist.home.todo.presentation.TodoFragment;
import zakhargoryainov.todolist.authentication.AuthInteractor;
import zakhargoryainov.todolist.authentication.login.LoginPresenter;
import zakhargoryainov.todolist.home.todo.presentation.TodoPresenter;
import zakhargoryainov.todolist.home.todo.presentation.dialog.TodoNotationEditDialogFragment;
import zakhargoryainov.todolist.home.todo.presentation.dialog.TodoNotationEditPresenter;


@Singleton
@Component(modules = {AuthModule.class, ContextModule.class})
public interface AppComponent  {
    Context getContext();
    AuthService getAuthService();//todo checkout usage
    AuthInteractor getAuthInteractor();

    void inject(LoginPresenter loginPresenter);
    void inject(RegistrationPresenter registrationPresenter);
    void inject(TodoFragment fragment);
    void inject(DoneFragment fragment);
    void inject(TodoNotationEditDialogFragment dialogFragment);
    void inject(TodoNotationEditPresenter todoNotationEditPresenter);
    void inject(TodoPresenter todoPresenter);

}
