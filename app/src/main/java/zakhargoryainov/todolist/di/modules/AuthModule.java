package zakhargoryainov.todolist.di.modules;

import com.google.firebase.auth.FirebaseAuth;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import zakhargoryainov.todolist.authentication.AuthService;
import zakhargoryainov.todolist.authentication.AuthInteractor;

@Module
public class AuthModule {

    @Provides
    @Singleton
    public AuthService provideAuthService(){
        return  new AuthService(FirebaseAuth.getInstance());
    }

    @Provides
    @Singleton
    public AuthInteractor provideAuthInteractor(AuthService authService){
        return new AuthInteractor(authService);
    }
}
