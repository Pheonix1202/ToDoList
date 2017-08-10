package zakhargoryainov.todolist.di.modules;

import android.arch.persistence.room.Room;
import android.content.Context;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import zakhargoryainov.todolist.database.TodoDatabase;


@Module(includes = ContextModule.class)
public class DatabaseModule {

    @Provides
    @Singleton
    public TodoDatabase provideRoomDatabase(Context context){
        TodoDatabase database = Room.databaseBuilder(context, TodoDatabase.class, "todolist.db").build();
        return database;
    }
}
