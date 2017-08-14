package zakhargoryainov.todolist.home.todo.presentation.dialog.create;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.Locale;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zakhargoryainov.todolist.R;
import zakhargoryainov.todolist.app.TodoApplication;
import zakhargoryainov.todolist.base.MvpDialogFragment;
import zakhargoryainov.todolist.entities.TodoNotation;
import zakhargoryainov.todolist.home.todo.presentation.listener.OnSuccessDismissListener;

/**
 * Created by Захар on 09.08.2017.
 */

public class TodoCreateDialogFragment extends MvpDialogFragment implements TodoCreateView {

    @InjectPresenter
    TodoCreatePresenter presenter;
    @BindView(R.id.edit_text_title)
    EditText titleEditText;
    @BindView(R.id.edit_text_body)
    EditText bodyEditText;
    @BindViews({R.id.view_priority_tier_1, R.id.view_priority_tier_2,
            R.id.view_priority_tier_3, R.id.view_priority_tier_4,})
    TextView[] priorityViews;
    @BindView(R.id.date_picker)
    DatePicker datePicker;
    @BindView(R.id.time_picker)
    TimePicker timePicker;
    @BindView(R.id.button_confirm)
    Button confirmButton;

    private TodoNotation notation;
    private OnSuccessDismissListener listener;
    private int priority = 1;

    public static TodoCreateDialogFragment newInstance(OnSuccessDismissListener listener) {
        TodoCreateDialogFragment fragment = new TodoCreateDialogFragment();
        fragment.listener = listener;
        return fragment;
    }

    @Override
    public void extractData(TodoNotation notation) {

    }

    @Override
    public void onSuccess() {
        listener.onSuccessfulDismiss();
        dismiss();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_todo_create, container, false);
        ButterKnife.bind(this, view);
        initPriorityPicker();
        TodoApplication.getAppComponent().inject(this);
        notation = presenter.getCurrentNotation();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        DisplayMetrics metrics = new DisplayMetrics();
        Window window = getDialog().getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        window.getWindowManager().getDefaultDisplay().getMetrics(metrics);

        int width = 0, height = 0;
        width = metrics.widthPixels - getResources().getDimensionPixelSize(R.dimen.padding_small) * 2;
        height = metrics.heightPixels - getResources().getDimensionPixelSize(R.dimen.padding_small) * 2;
        getDialog().getWindow().setGravity(Gravity.CENTER);
        getDialog().getWindow().setLayout(width, height);
    }

    @OnClick(R.id.button_confirm)
    public void onConfirmButtonClick() {
        // todo валидация данных
        notation.setTitle(titleEditText.getText().toString());
        notation.setBody(bodyEditText.getText().toString());
        notation.setPriority(priority);
        notation.setDate(String.format(Locale.ENGLISH,"%02d",datePicker.getDayOfMonth()) + "." +
                String.format(Locale.ENGLISH,"%02d",datePicker.getMonth()) + "." +
                datePicker.getYear() + "    " +
                String.format(Locale.ENGLISH,"%02d",timePicker.getCurrentHour()) + ":" +
                String.format(Locale.ENGLISH,"%02d",timePicker.getCurrentMinute()));
        Toast.makeText(getContext(),notation.getDate(),Toast.LENGTH_LONG).show();
        presenter.insertOrUpdateTodoNotation(notation);
    }

    private void initPriorityPicker() {
        for (int i = 0; i < 4; i++) {
            int color = findPriorityColor(i);
            GradientDrawable shape = new GradientDrawable();
            shape.setShape(GradientDrawable.OVAL);
            shape.setStroke(getResources().getDimensionPixelSize(R.dimen.stroke_width_common), color);
            priorityViews[i].setBackground(shape);
            priorityViews[i].setTextColor(color);
            priorityViews[i].setText(String.valueOf(i+1));
            priorityViews[i].setOnClickListener( v -> {
                for (int j = 0; j < 4; j++){
                    priorityViews[j].setScaleX(1);
                    priorityViews[j].setScaleY(1);
                    if (v.equals(priorityViews[j])) priority = j+1;
                }
                v.setScaleX(1.2f);
                v.setScaleY(1.2f);
            });
        }
    }

    private int findPriorityColor(int priority) {
        switch (priority) {
            case 0:
                return ContextCompat.getColor(getContext(), R.color.priority_tier_1);
            case 1:
                return ContextCompat.getColor(getContext(), R.color.priority_tier_2);
            case 2:
                return ContextCompat.getColor(getContext(), R.color.priority_tier_3);
            case 3:
                return ContextCompat.getColor(getContext(), R.color.priority_tier_4);
            default:
                return -1;
        }
    }

}
