package zakhargoryainov.todolist.home.todo.presentation.dialog.details;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zakhargoryainov.todolist.PriorityViewUtils;
import zakhargoryainov.todolist.R;
import zakhargoryainov.todolist.app.TodoApplication;
import zakhargoryainov.todolist.base.MvpDialogFragment;
import zakhargoryainov.todolist.database.TodoDatabase;
import zakhargoryainov.todolist.entities.TodoNotation;
import zakhargoryainov.todolist.home.OnSuccessDismissListener;
import zakhargoryainov.todolist.home.todo.OnDetailsDialogDismissListener;


public class TodoDetailsDialogFragment extends MvpDialogFragment implements TodoDetailsView{

    @InjectPresenter TodoDetailsPresenter presenter;
    @BindView(R.id.text_view_title) TextView titleTextView;
    @BindView(R.id.text_view_body) TextView bodyTextView;
    @BindView(R.id.text_view_priority) TextView priorityTextView;
    @BindView(R.id.text_view_date) TextView dateTextView;
    @BindView(R.id.button_completed) Button buttonDone;
    private OnDetailsDialogDismissListener listener;
    private int itemPosition;
    private TodoNotation notation;


    public static TodoDetailsDialogFragment newInstance(TodoNotation notation,
                                                        OnDetailsDialogDismissListener listener,
                                                        int itemPosition) {
        TodoDetailsDialogFragment fragment = new TodoDetailsDialogFragment();
        fragment.notation = notation;
        fragment.itemPosition = itemPosition;
        fragment.listener = listener;
        return fragment;
    }

    @Override @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_todo_details, container, false);
        ButterKnife.bind(this, view);
        //todo прокинуть слушателей и тудуху
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (notation != null && listener != null)
        presenter.reinitDialog(notation,itemPosition,listener);
    }

    @Override
    public void extractData(TodoNotation notation) {
        titleTextView.setText(notation.getTitle());
        bodyTextView.setText(notation.getBody());
        dateTextView.setText(notation.getFormattedDeadline());
        PriorityViewUtils.setPriority(priorityTextView,notation.getPriority());
    }

    @OnClick(R.id.button_completed)
    public void onButtonDoneClick(){
        presenter.completeTodoNotation();
    }

    @OnClick(R.id.button_delete)
    public void onButtonDeleteClick(){
        presenter.deleteTodoNotation();
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        listener.onCancel();
        super.onCancel(dialog);
    }

    @Override
    public void onCompleteSuccess() {
        listener.onCompleteSuccess(itemPosition);
        dismiss();
    }

    @Override
    public void onDeleteSuccess() {
        listener.onDeleteSuccess(itemPosition);
        dismiss();
    }

    @Override
    public void onError(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setPosition(int position) {
        itemPosition = position;
    }

    @Override
    public void setListener(OnDetailsDialogDismissListener listener) {
        this.listener = listener;
    }

}
