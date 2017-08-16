package zakhargoryainov.todolist.data.notifications;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import java.util.Calendar;
import java.util.List;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import zakhargoryainov.todolist.R;
import zakhargoryainov.todolist.app.TodoApplication;
import zakhargoryainov.todolist.database.TodoDatabase;
import zakhargoryainov.todolist.entities.TodoNotation;


public class TodoListService extends Service {

    private static final long DURATION_MONTH = 2629746000l,
            DURATION_WEEK = 604800000,
            DURATION_DAY = 86400000,
            DURATION_HOUR = 3600000;

    private AlarmManager alarmManager;
    private TodoDatabase todoDatabase;
    private int uniqueId = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        todoDatabase = TodoApplication.getAppComponent().getTodoDatabase();
        todoDatabase.todoDao().getNotations(false)
                .toObservable()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(this::setAlarms)
                .subscribe();

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    private void setAlarms(List<TodoNotation> notations) {
        long timeCurrent = Calendar.getInstance().getTimeInMillis();
        for (TodoNotation notation : notations) {
            trySetSingleAlarm(notation,timeCurrent,DURATION_MONTH);
            trySetSingleAlarm(notation,timeCurrent,DURATION_WEEK);
            trySetSingleAlarm(notation,timeCurrent,DURATION_DAY);
            trySetSingleAlarm(notation,timeCurrent,DURATION_HOUR);
        }
    }

    private void trySetSingleAlarm(TodoNotation notation, long timeCurrent, long timeOffset) {
        String message = "";
        if (timeOffset == DURATION_MONTH) message = getString(R.string.month_notification);
        if (timeOffset == DURATION_WEEK) message = getString(R.string.week_notification);
        if (timeOffset == DURATION_DAY) message = getString(R.string.day_notification);
        if (timeOffset == DURATION_HOUR) message = getString(R.string.hour_notification);

        if (timeCurrent < notation.getDeadlineTimestamp() - timeOffset) {
            Intent intent = new Intent(this, AlarmReceiver.class);
            intent.putExtra("title", notation.getTitle());
            intent.putExtra("message", message);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, uniqueId++, intent, 0);
            alarmManager.set(AlarmManager.RTC,
                    notation.getDeadlineTimestamp() - timeOffset,
                    pendingIntent);
        }

    }
}
