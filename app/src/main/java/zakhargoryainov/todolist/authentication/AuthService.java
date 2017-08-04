package zakhargoryainov.todolist.todo.authentication;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;
import javax.inject.Singleton;

import dagger.Provides;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import zakhargoryainov.todolist.R;
import zakhargoryainov.todolist.todo.dto.ApiToken;

/**
 * Created by Захар on 02.08.2017.
 */

public class AuthService {

    private final FirebaseAuth auth;

    private final Context context; //todo remove

    @Inject
    public AuthService(FirebaseAuth auth, Context context) {
        this.auth = auth;
        this.context = context;
    }

    public Completable signIn(String email, String password) {
        return Completable.create(e -> auth.signInWithEmailAndPassword(email, password)
                .addOnSuccessListener(authResult -> e.onComplete())
                .addOnFailureListener(e::onError)) //todo check auth failed logic
                .doOnComplete(() -> Log.d("FB_COMPLETE",auth.getCurrentUser().getEmail()))
                .doOnError(throwable -> Log.d("FB_ERROR",throwable.getLocalizedMessage()));
//
//        Single<Task<AuthResult>> response = Single.create(e ->
//                auth.signInWithEmailAndPassword(email, password)
//                        .addOnCompleteListener(task -> {
//                            if (task.isSuccessful()) e.onSuccess(task);
//                            else e.onError(new Throwable(context.getString(R.string.auth_error)));
//                        })
//                        .addOnFailureListener(e::onError));


    }

    public Completable signUp(String email, String password) {
        return Completable.create(e -> auth.createUserWithEmailAndPassword(email, password))
                .addOnSuccessListener(authResult -> e.onComplete())
                .addOnFailureListener(e::onError)) //todo check auth failed logic
                .doOnComplete(() -> Log.d("FB_COMPLETE",auth.getCurrentUser().getEmail()))
                .doOnError(throwable -> Log.d("FB_ERROR",throwable.getLocalizedMessage()));
    }
}
