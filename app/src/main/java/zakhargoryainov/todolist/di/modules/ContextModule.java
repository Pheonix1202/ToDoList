package zakhargoryainov.todolist.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import zakhargoryainov.todolist.authentication.localdata.LoginRepository;

/**
 * Created by Захар on 03.08.2017.
 */

@Module
public class ContextModule {

    private Context context;
    private LoginRepository loginRepository;

    public ContextModule(Context context) {
        this.context = context;
        loginRepository = new LoginRepository(context);
    }

    @Provides
    @Singleton
    public Context provideContext(){
        return context;
    }

    @Provides
    @Singleton
    public LoginRepository provideLoginPreferences(){
        return loginRepository;
    }




//    @Provides
//    @Singleton
//    public TodoRecyclerViewAdapter provideAdapter(){
//        return todoAdapter;
//    }
}
