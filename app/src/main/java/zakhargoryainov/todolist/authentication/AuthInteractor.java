package zakhargoryainov.todolist.authentication;

import com.google.firebase.auth.FirebaseUser;
import javax.inject.Inject;
import io.reactivex.Completable;
import zakhargoryainov.todolist.authentication.localdata.LoginRepository;
import zakhargoryainov.todolist.data.FirebaseStrategy;


public class AuthInteractor {

    private AuthService authService;
    private FirebaseStrategy firebaseStrategy;
    private LoginRepository repository;

    @Inject
    public AuthInteractor(AuthService authService,
                          FirebaseStrategy firebaseStrategy,
                          LoginRepository repository) {
        this.authService = authService;
        this.firebaseStrategy = firebaseStrategy;
        this.repository = repository;
    }

    public Completable signIn(String email, String password){
        return authService.signIn(email, password)
                .doOnComplete(()->repository.saveLoginPassword(email, password));
    }

    public Completable signUp(String email, String password){
        return authService.signUp(email,password);
    }

    public void onSignIn(FirebaseUser user) {
        firebaseStrategy.onSignIn(user);
    }
}
