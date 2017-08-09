package zakhargoryainov.todolist.authentication;

        import android.content.Context;
        import android.util.Log;
        import com.google.firebase.auth.FirebaseAuth;
        import javax.inject.Inject;
        import io.reactivex.Completable;

/**
 * Created by Захар on 02.08.2017.
 */

public class AuthService {

    private final FirebaseAuth auth;

    //private final Context context; //todo remove if there is not custom error handling

    @Inject
    public AuthService(FirebaseAuth auth, Context context) {
        this.auth = auth;
        //this.context = context;
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
