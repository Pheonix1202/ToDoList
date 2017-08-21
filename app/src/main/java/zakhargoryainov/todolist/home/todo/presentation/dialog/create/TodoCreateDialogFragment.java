package zakhargoryainov.todolist.home.todo.presentation.dialog.create;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zakhargoryainov.todolist.PriorityViewUtils;
import zakhargoryainov.todolist.R;
import zakhargoryainov.todolist.app.TodoApplication;
import zakhargoryainov.todolist.base.MvpDialogFragment;
import zakhargoryainov.todolist.entities.TodoNotation;
import zakhargoryainov.todolist.home.todo.OnCreateDialogDismissListener;


public class TodoCreateDialogFragment extends MvpDialogFragment implements TodoCreateView {

    @InjectPresenter TodoCreatePresenter presenter;
    @BindView(R.id.edit_text_title) EditText titleEditText;
    @BindView(R.id.edit_text_body)  EditText bodyEditText;
    @BindView(R.id.date_picker) DatePicker datePicker;
    @BindView(R.id.time_picker) TimePicker timePicker;
    @BindView(R.id.button_confirm) Button confirmButton;
    @BindViews({R.id.view_priority_tier_1, R.id.view_priority_tier_2,
            R.id.view_priority_tier_3, R.id.view_priority_tier_4,})
    TextView[] priorityViews;

    private OnCreateDialogDismissListener listener;
    private int priority = 1;

    public static TodoCreateDialogFragment newInstance(OnCreateDialogDismissListener listener) {
        TodoCreateDialogFragment fragment = new TodoCreateDialogFragment();
        fragment.listener = listener;
        return fragment;
    }

    @Override
    public void onCreateSuccess() {
        listener.onCreateSuccess();
        dismiss();
    }

    @Override
    public void onError(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setListener(OnCreateDialogDismissListener listener) {
        this.listener = listener;
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        listener.onCancel();
        super.onCancel(dialog);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_todo_create, container, false);
        ButterKnife.bind(this, view);
        initPriorityPicker();
        TodoApplication.getAppComponent().inject(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        presenter.reinitDialog(listener);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener.onCancel();
    }

    @OnClick(R.id.button_confirm)
    public void onConfirmButtonClick() {
        TodoNotation notation = new TodoNotation();
        notation.setTitle(titleEditText.getText().toString());
        notation.setBody(bodyEditText.getText().toString());
        notation.setPriority(priority);
        notation.setFormattedDeadline(String.format(Locale.ENGLISH, "%02d", datePicker.getDayOfMonth()) + "." +
                String.format(Locale.ENGLISH, "%02d", datePicker.getMonth()) + "." +
                datePicker.getYear() + "   " +
                String.format(Locale.ENGLISH, "%02d", timePicker.getCurrentHour()) + ":" +
                String.format(Locale.ENGLISH, "%02d", timePicker.getCurrentMinute()));
        Calendar calendar = new GregorianCalendar(
                datePicker.getYear(),
                datePicker.getMonth(),
                datePicker.getDayOfMonth(),
                timePicker.getCurrentHour(),
                timePicker.getCurrentMinute());
        notation.setDeadlineTimestamp(calendar.getTimeInMillis());
        presenter.addNewNotation(notation);
    }

    private void initPriorityPicker() {
        for (int i = 0; i < 4; i++) {
            PriorityViewUtils.setPriority(priorityViews[i],i);
            priorityViews[i].setOnClickListener(v -> {
                for (int j = 0; j < 4; j++) {
                    priorityViews[j].setScaleX(1);
                    priorityViews[j].setScaleY(1);
                    if (v.equals(priorityViews[j])) priority = j;
                }
                v.setScaleX(1.2f);
                v.setScaleY(1.2f);
            });
        }
    }
}
