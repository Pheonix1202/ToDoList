package zakhargoryainov.todolist.di.modules;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import zakhargoryainov.todolist.home.todo.presentation.TodoFragment;
import zakhargoryainov.todolist.home.todo.presentation.TodoPresenter;
import zakhargoryainov.todolist.home.todo.presentation.adapter.TodoRecyclerViewAdapter;
import zakhargoryainov.todolist.home.todo.presentation.listener.OnNotationClickListener;

/**
 * Created by Захар on 03.08.2017.
 */

@Module
public class ContextModule {

    private Context context;
    private TodoPresenter presenter;

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public Context provideContext(){
        return context;
    }

    @Provides
    @Singleton
    public TodoPresenter todoPresenter(){
        return new TodoPresenter();
    }

//    @Provides
//    @Singleton
//    public TodoRecyclerViewAdapter provideAdapter(){
//        return todoAdapter;
//    }
}
