package zakhargoryainov.todolist.di;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;
import zakhargoryainov.todolist.authentication.AuthService;
import zakhargoryainov.todolist.authentication.registration.RegistrationPresenter;
import zakhargoryainov.todolist.di.modules.AuthModule;
import zakhargoryainov.todolist.di.modules.ContextModule;
import zakhargoryainov.todolist.todo.authentication.AuthInteractor;

import zakhargoryainov.todolist.authentication.login.LoginPresenter;

/**
 * Created by Захар on 02.08.2017.
 */

@Singleton
@Component(modules = {AuthModule.class, ContextModule.class})
public interface AppComponent  {
    Context getContext();
    AuthService getAuthService();
    AuthInteractor getAuthInteractor();

    void inject(LoginPresenter loginPresenter);
    void inject(RegistrationPresenter registrationPresenter);
}
