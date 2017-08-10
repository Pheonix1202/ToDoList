package zakhargoryainov.todolist.app;

import android.app.Application;
import zakhargoryainov.todolist.di.AppComponent;
import zakhargoryainov.todolist.di.DaggerAppComponent;
import zakhargoryainov.todolist.di.modules.AuthModule;
import zakhargoryainov.todolist.di.modules.ContextModule;


public class TodoApplication extends Application {

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
