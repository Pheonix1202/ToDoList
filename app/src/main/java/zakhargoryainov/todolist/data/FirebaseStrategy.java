package zakhargoryainov.todolist.data;

import android.util.Log;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;
import io.reactivex.Completable;
import io.reactivex.Observable;
import zakhargoryainov.todolist.database.TodoDatabase;
import zakhargoryainov.todolist.entities.TodoNotation;

@Singleton
public class FirebaseStrategy implements DataServiceStrategy{

    private final FirebaseDatabase firebaseDatabase;
    private final TodoDatabase todoDatabase;
    private String userID;

    @Inject
    public FirebaseStrategy(FirebaseDatabase firebaseDatabase, TodoDatabase todoDatabase) {
        this.firebaseDatabase = firebaseDatabase;
        this.todoDatabase = todoDatabase;
    }

    public void onSignIn(FirebaseUser user) {
//       this.user = user;
        userID = user.getUid();
        Log.d("FIREBASE", userID);
        Log.d("FIREBASE", user.getEmail());
    }


    @Override
    public Completable insert(TodoNotation notation) {
        final DatabaseReference reference = firebaseDatabase.getReference()
                .child("users")
                .child(userID)
                .child("todolist")
                .child(notation.getId());
        return Completable.fromAction(() -> insertIntoFirebase(reference,notation))
                .doOnComplete(() -> Log.d("FIREBASE", notation.getTitle()))
                .doOnError(throwable -> Log.d("FIREBASE", throwable.getMessage()));
    }

    @Override
    public Completable update(TodoNotation notation) {
        final DatabaseReference reference = firebaseDatabase.getReference()
            .child("users")
            .child(userID)
            .child("todolist")
            .child(notation.getId());
        return Completable.fromAction(() -> insertIntoFirebase(reference,notation))
                .doOnComplete(() -> Log.d("FIREBASE", notation.getTitle()))
                .doOnError(throwable -> Log.d("FIREBASE", throwable.getMessage()));
    }

    @Override
    public Completable delete(TodoNotation notation) {
        final DatabaseReference reference = firebaseDatabase.getReference()
                .child("users")
                .child(userID)
                .child("todolist")
                .child(notation.getId());
        return Completable.fromAction(() -> deleteFromFirebase(reference,notation))
                .doOnComplete(() -> Log.d("FIREBASE", notation.getTitle()))
                .doOnError(throwable -> Log.d("FIREBASE", throwable.getMessage()));
    }

    @Override
    public Completable deleteDoneNotations() {
        GenericTypeIndicator<List<TodoNotation>> typeIndicator =
                new GenericTypeIndicator<List<TodoNotation>>() {};
        final DatabaseReference reference = firebaseDatabase.getReference()
                .child("users")
                .child(userID)
                .child("todolist");
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                List<TodoNotation> notations = dataSnapshot.getValue(typeIndicator);
                for(TodoNotation notation : notations)
                    if (notation.isDone()) reference
                            .child(notation.getId())
                            .removeValue()
                            .addOnSuccessListener(aVoid -> Log.d("FIREBASE","Removed "+ notation.getTitle()));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("FIREBASE",databaseError.getMessage());
                Log.d("FIREBASE",databaseError.getDetails());
            }
        });

        return null;
    }

    @Override
    public Observable<List<TodoNotation>> getTodoList() {
        return Observable.create(emitter -> {
            try {
                GenericTypeIndicator<List<TodoNotation>> typeIndicator =
                        new GenericTypeIndicator<List<TodoNotation>>() {
                        };
                final DatabaseReference reference = firebaseDatabase.getReference()
                        .child("users")
                        .child(userID)
                        .child("todolist");
                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        List<TodoNotation> notations = new ArrayList<>();
                        dataSnapshot.getValue(typeIndicator).forEach(notation -> {
                            if (!notation.isDone()) notations.add(notation);
                        });
                        emitter.onNext(notations);
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {}
                });
                emitter.onComplete();
            }
            catch (Exception e){emitter.onError(e);}
        });

    }

    @Override
    public Observable<List<TodoNotation>> getDoneList() {
// TODO: 22.08.2017 add code equals to getTodoList()
        return null;
    }

    private void insertIntoFirebase(DatabaseReference reference, TodoNotation notation){
        reference.setValue(notation).addOnSuccessListener(aVoid -> insertIntoRoom(notation));
    }

    private void insertIntoRoom(TodoNotation notation){
        todoDatabase.todoDao().insertOrUpdateNotation(notation);
    }

    private void deleteFromFirebase(DatabaseReference reference, TodoNotation notation){
        reference.removeValue().addOnSuccessListener((aVoid -> deleteFromRoom(notation)));
    }

    private void deleteFromRoom(TodoNotation notation){
        todoDatabase.todoDao().deleteNotation(notation);
    }
}
