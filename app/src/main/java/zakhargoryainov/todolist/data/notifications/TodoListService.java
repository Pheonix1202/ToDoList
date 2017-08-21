package zakhargoryainov.todolist.data.notifications;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import zakhargoryainov.todolist.R;
import zakhargoryainov.todolist.app.TodoApplication;
import zakhargoryainov.todolist.data.TodoListRoomService;
import zakhargoryainov.todolist.database.TodoDatabase;
import zakhargoryainov.todolist.entities.TodoNotation;


public class TodoListService extends Service {

    public static final String TITLE = "zakhargoryainov.todolist.TITLE";
    public static final String MESSAGE = "zakhargoryainov.todolist.MESSAGE";
    public static final String IS_EXPIRED = "zakhargoryainov.todolist.IS_EXPIRED";

    private static final long DURATION_MONTH = 2629746000l,
            DURATION_WEEK = 604800000,
            DURATION_DAY = 86400000,
            DURATION_HOUR = 3600000;

    private AlarmManager alarmManager;
    private TodoDatabase database;
    private int uniqueId = 0;
    private List<PendingIntent> pendingIntents;

    @Override
    public void onCreate() {
        super.onCreate();
        database = TodoApplication.getAppComponent().getTodoDatabase();
        TodoListRoomService roomService = TodoApplication.getAppComponent().provideRoom();
        roomService.getTodoList()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(this::setAlarms)
                .subscribe();
        pendingIntents = new ArrayList<>();
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        database.todoDao().getExpiredNotations(false,System.currentTimeMillis())
                .toObservable()
                .subscribeOn(Schedulers.io())
                .doOnNext(this::setNotationFailed)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(throwable -> Toast.makeText(getBaseContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show())
                .subscribe();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void setAlarms(List<TodoNotation> notations) {
        long timeCurrent = Calendar.getInstance().getTimeInMillis();
        for (PendingIntent pendingIntent : pendingIntents)
            alarmManager.cancel(pendingIntent);
        for (TodoNotation notation : notations) {
            trySetSingleAlarm(notation, timeCurrent, 0);
            trySetSingleAlarm(notation, timeCurrent, DURATION_MONTH);
            trySetSingleAlarm(notation, timeCurrent, DURATION_WEEK);
            trySetSingleAlarm(notation, timeCurrent, DURATION_DAY);
            trySetSingleAlarm(notation, timeCurrent, DURATION_HOUR);
        }
    }

    private void trySetSingleAlarm(TodoNotation notation, long timeCurrent, long timeOffset) {
        String message = "";
        if (timeOffset == 0) message = getString(R.string.todo_expired_notification, notation.getTitle());
        if (timeOffset == DURATION_MONTH) message = getString(R.string.month_notification);
        if (timeOffset == DURATION_WEEK) message = getString(R.string.week_notification);
        if (timeOffset == DURATION_DAY) message = getString(R.string.day_notification);
        if (timeOffset == DURATION_HOUR) message = getString(R.string.hour_notification);

        if (timeCurrent < notation.getDeadlineTimestamp() - timeOffset) {
            Intent intent = new Intent(this, AlarmReceiver.class);
            intent.putExtra(TITLE, notation.getTitle());
            intent.putExtra(MESSAGE, message);
            if (timeOffset != 0) intent.putExtra(IS_EXPIRED, false);
            else intent.putExtra(IS_EXPIRED, true);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, uniqueId++, intent, 0);
            pendingIntents.add(pendingIntent);
            alarmManager.set(AlarmManager.RTC,
                    notation.getDeadlineTimestamp() - timeOffset,
                    pendingIntent);
        }
    }

    private void setNotationFailed(List<TodoNotation> notations) {
        if (notations != null) Log.d("TimeStamp",String.valueOf(notations.size()));
        else Log.d("TimeStamp", "null notations");
        for(TodoNotation notation: notations) {
            notation.setFailed(true);
            notation.setDone(true);
            database.todoDao().insertOrUpdateNotation(notation);
        }
    }
}
