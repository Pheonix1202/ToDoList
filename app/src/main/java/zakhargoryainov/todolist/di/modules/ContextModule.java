package zakhargoryainov.todolist.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import zakhargoryainov.todolist.home.todo.presentation.adapter.TodoRecyclerViewAdapter;

/**
 * Created by Захар on 03.08.2017.
 */

@Module
public class ContextModule {

    private Context context;
    private TodoRecyclerViewAdapter todoAdapter;

    public ContextModule(Context context) {
        this.context = context;
        this.todoAdapter = new TodoRecyclerViewAdapter(context);
    }

    @Provides
    @Singleton
    public Context provideContext(){
        return context;
    }

    @Provides
    @Singleton
    public TodoRecyclerViewAdapter provideAdapter(){
        return todoAdapter;
    }
}
