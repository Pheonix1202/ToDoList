package zakhargoryainov.todolist.data.notifications;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class TodoListBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent serviceIntent = new Intent(context, TodoListService.class);
        context.startService(serviceIntent);
    }
}
