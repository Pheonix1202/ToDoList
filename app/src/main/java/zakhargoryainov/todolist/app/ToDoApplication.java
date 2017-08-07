package zakhargoryainov.todolist.app;

import android.app.Application;
import android.content.Context;
import android.os.Trace;
import android.support.annotation.NonNull;
import android.widget.Toast;

import zakhargoryainov.todolist.di.AppComponent;
//import zakhargoryainov.todolist.di.DaggerAppComponent;
import zakhargoryainov.todolist.di.DaggerAppComponent;
import zakhargoryainov.todolist.di.modules.AuthModule;
import zakhargoryainov.todolist.di.modules.ContextModule;
//import zakhargoryainov.todolist.di.DaggerAppComponent;
import zakhargoryainov.todolist.authentication.AuthService;

/**
 * Created by Захар on 03.08.2017.
 */

public class ToDoApplication extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        //todo узнать, почему приложению требуется много времени для запуска
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .authModule(new AuthModule())
                .contextModule(new ContextModule(this))
                .build();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }

}
