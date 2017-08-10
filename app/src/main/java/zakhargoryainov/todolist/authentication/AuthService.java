package zakhargoryainov.todolist.authentication;

import android.util.Log;
import com.google.firebase.auth.FirebaseAuth;
import javax.inject.Inject;
import io.reactivex.Completable;


public class AuthService {

    private final FirebaseAuth auth;

    @Inject
    public AuthService(FirebaseAuth auth) {
        this.auth = auth;
    }

    public Completable signIn(String email, String password) {
        return Completable.create(e -> auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> e.onComplete())
                .addOnFailureListener(e::onError))
                .doOnComplete(() -> Log.d("FB_COMPLETE",auth.getCurrentUser().getEmail()))
                .doOnError(throwable -> Log.d("FB_ERROR",throwable.getLocalizedMessage()));
    }

    public Completable signUp(String email, String password) {
        return Completable.create(e -> auth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> e.onComplete())
                .addOnFailureListener(e::onError))
                .doOnComplete(() -> Log.d("FB_COMPLETE",auth.getCurrentUser().getEmail()))
                .doOnError(throwable -> Log.d("FB_ERROR",throwable.getLocalizedMessage()));
    }
}
