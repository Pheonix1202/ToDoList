package zakhargoryainov.todolist.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Захар on 03.08.2017.
 */

@Module
public class ContextModule {

    private Context context;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public Context provideContext(){
        return context;
    }




//    @Provides
//    @Singleton
//    public TodoRecyclerViewAdapter provideAdapter(){
//        return todoAdapter;
//    }
}
