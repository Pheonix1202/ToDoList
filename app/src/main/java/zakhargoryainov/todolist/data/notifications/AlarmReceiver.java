package zakhargoryainov.todolist.data.notifications;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import java.util.Calendar;
import java.util.List;
import io.reactivex.android.schedulers.AndroidSchedulers;
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
                .setColor(ContextCompat.getColor(context,R.color.colorPrimary))
                .setContentTitle(intent.getStringExtra(TodoListService.TITLE))
                .setContentText(intent.getStringExtra(TodoListService.MESSAGE))
                .setTicker(intent.getStringExtra(TodoListService.TITLE))
                .build();

        if (intent.getBooleanExtra(TodoListService.IS_EXPIRED,false))
            database.todoDao().getExpiredNotations(false, Calendar.getInstance().getTimeInMillis())
                .toObservable()
                .subscribeOn(Schedulers.io())
                .doOnNext(this::setNotationFailed)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe();

        NotificationManager manager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        manager.notify((int) System.currentTimeMillis(), notification);

    }

    private void setNotationFailed(List<TodoNotation> notations) {
        for(TodoNotation notation: notations) {
            notation.setFailed(true);
            notation.setDone(true);
            database.todoDao().insertOrUpdateNotation(notation);
        }
    }
}
