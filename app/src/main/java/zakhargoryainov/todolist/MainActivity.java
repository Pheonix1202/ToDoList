package zakhargoryainov.todolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import io.reactivex.Completable;
import zakhargoryainov.todolist.app.TodoApplication;
import zakhargoryainov.todolist.authentication.localdata.LoginRepository;
import zakhargoryainov.todolist.authentication.presentation.AuthActivity;
import zakhargoryainov.todolist.data.FirebaseStrategy;
import zakhargoryainov.todolist.data.notifications.TodoListService;
import zakhargoryainov.todolist.home.presentation.HomeActivity;


public class MainActivity extends AppCompatActivity {

    private LoginRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startTodolistService();
        repository = TodoApplication.getAppComponent().getLoginRepository();
        if (repository.containsLoginPassword()) trySignInAndGoHome();
        else goHome();
    }

    private void startTodolistService(){
        Intent serviceIntent = new Intent(this, TodoListService.class);
        startService(serviceIntent);
    }


    private void trySignInAndGoHome() {
        FirebaseStrategy firebaseStrategy = TodoApplication.getAppComponent().getFirebaseDatabase();
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        Pair<String, String> credentials = repository.loadLoginPassword();
        Completable
                .fromAction(() -> firebaseAuth.signInWithEmailAndPassword(credentials.first, credentials.second))
                .doOnComplete(() -> firebaseStrategy.onSignIn(firebaseAuth.getCurrentUser()))
                .doOnError(throwable -> Toast.makeText(getBaseContext(), throwable.getMessage(), Toast.LENGTH_LONG).show())
                .doFinally(this::goHome)
                .subscribe();

    }

    private void goHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    private void startAuth() {
        Intent intent = new Intent(MainActivity.this, AuthActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

}
