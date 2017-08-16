package zakhargoryainov.todolist.data.notifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import java.util.Calendar;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import zakhargoryainov.todolist.R;
import zakhargoryainov.todolist.app.TodoApplication;
import zakhargoryainov.todolist.database.TodoDatabase;
import zakhargoryainov.todolist.entities.TodoNotation;

import static android.content.Context.NOTIFICATION_SERVICE;


public class AlarmReceiver extends BroadcastReceiver {

    private TodoDatabase database;
    @Override
    public void onReceive(Context context, Intent intent) {
        database = TodoApplication.getAppComponent().getTodoDatabase();

        Notification notification = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.ic_todolist)
                .setContentTitle(intent.getStringExtra("title"))
                .setContentText(intent.getStringExtra("message"))
                .setTicker(intent.getStringExtra("title"))
                .build();
        database.todoDao().getExpiredNotations(false,System.currentTimeMillis())
                .subscribeOn(Schedulers.io())
                .doOnNext(this::setNotationFailed)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();

        NotificationManager manager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        manager.notify((int) System.currentTimeMillis(), notification);

    }

    private void setNotationFailed(List<TodoNotation> notations) {
        for(TodoNotation notation: notations) {
            notation.setFailed(false);
            notation.setDone(true);
            database.todoDao().insertOrUpdateNotation(notation);
        }
    }
}
