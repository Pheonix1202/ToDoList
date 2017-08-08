package zakhargoryainov.todolist.di.modules;

import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import zakhargoryainov.todolist.authentication.AuthService;
import zakhargoryainov.todolist.authentication.AuthInteractor;

/**
 * Created by Захар on 03.08.2017.
 */

@Module(includes = {ContextModule.class})
public class AuthModule {

    @Provides
    @Singleton
    public AuthService provideAuthService(Context context){
        return  new AuthService(FirebaseAuth.getInstance(), context);
    }

    @Provides
    @Singleton
    public AuthInteractor provideAuthInteractor(AuthService authService){
        return new AuthInteractor(authService);
    }
}
