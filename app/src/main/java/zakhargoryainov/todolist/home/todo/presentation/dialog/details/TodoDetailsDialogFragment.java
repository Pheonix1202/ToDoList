package zakhargoryainov.todolist.home.todo.presentation.dialog.details;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zakhargoryainov.todolist.R;
import zakhargoryainov.todolist.base.MvpDialogFragment;
import zakhargoryainov.todolist.entities.TodoNotation;

/**
 * Created by Захар on 14.08.2017.
 */

public class TodoDetailsDialogFragment extends MvpDialogFragment implements TodoDetailsView{

    @InjectPresenter TodoDetailsPresenter presenter;
    @BindView(R.id.text_view_title)
    TextView titleTextView;
    @BindView(R.id.text_view_body)
    TextView bodyTextView;
    @BindView(R.id.text_view_priority)
    TextView priorityTextView;
    @BindView(R.id.text_view_date)
    TextView dateTextView;
    @BindView(R.id.button_done)
    Button buttonDone;

    private TodoNotation notation;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_todo_details, container, false);
        ButterKnife.bind(this, view);
        notation = presenter.getCurrentNotation();
        return view;
    }

    @Override
    public void extractData(TodoNotation notation) {
        titleTextView.setText(notation.getTitle());
        bodyTextView.setText(notation.getBody());
        dateTextView.setText(notation.getFormattedDeadline());
        priorityTextView.setText(String.valueOf(notation.getPriority()));
    }

    @Override
    public void onSuccess() {
        dismiss();
    }

    @OnClick(R.id.button_done)
    public void onButtonDoneClick(){
        PendingIntent pendingIntent = null;
        notation.setDone(true);
        presenter.insertOrUpdateTodoNotation(notation);
        Notification notification = new Notification.Builder(getContext())
                .setContentTitle("HASAGI")
                .setContentText("I hate portals")
                .setSmallIcon(R.drawable.img_slide_arrow_back)
                .setAutoCancel(true)
                .addAction(R.drawable.ic_done_all_black_24_px,"Done",pendingIntent)
                .build();

        NotificationManager managerCompat = (NotificationManager)
                getContext().getSystemService(Context.NOTIFICATION_SERVICE);

        managerCompat.notify(0,notification);
    }

}
