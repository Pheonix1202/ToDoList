package zakhargoryainov.todolist.di.modules;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import zakhargoryainov.todolist.authentication.AuthService;

@Module
public class FirebaseModule {

    @Provides
    @Singleton
    public AuthService provideAuthService(){
        return  new AuthService(FirebaseAuth.getInstance());
    }

//    @Provides
//    @Singleton
//    public AuthInteractor provideAuthInteractor(AuthService authService){
//        return new AuthInteractor(authService,);
//    }

    @Provides
    @Singleton
    public FirebaseDatabase provideFirebaseDatabase(){
        return FirebaseDatabase.getInstance();
    }


}
