package zakhargoryainov.todolist.home.todo.presentation.dialog;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arellomobile.mvp.MvpDelegate;
import com.arellomobile.mvp.presenter.InjectPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zakhargoryainov.todolist.R;
import zakhargoryainov.todolist.app.TodoApplication;
import zakhargoryainov.todolist.base.MvpAppCompatFragment;
import zakhargoryainov.todolist.base.MvpDialogFragment;
import zakhargoryainov.todolist.entities.TodoNotation;
import zakhargoryainov.todolist.home.todo.presentation.TodoPresenter;
import zakhargoryainov.todolist.home.todo.presentation.listener.OnDismissListener;

/**
 * Created by Захар on 09.08.2017.
 */

public class TodoNotationEditDialogFragment extends MvpDialogFragment implements TodoNotationEditView {

    @InjectPresenter
    TodoNotationEditPresenter presenter;
    @BindView(R.id.edit_text_title)
    EditText titleEditText;
    @BindView(R.id.edit_text_date)
    EditText dateEditText;
    @BindView(R.id.edit_text_body)
    EditText bodyEditText;
    @BindView(R.id.edit_text_priority)
    EditText priorityEditText;
    @BindView(R.id.button_confirm)
    Button confirmButton;

    TodoNotation notation;
    OnDismissListener listener;

    public static TodoNotationEditDialogFragment newInstance(OnDismissListener listener) {
        TodoNotationEditDialogFragment fragment = new TodoNotationEditDialogFragment();
        fragment.listener = listener;
        return fragment;
    }
    @Override
    public void extractData(TodoNotation notation) {
        titleEditText.setText(notation.getTitle());
        bodyEditText.setText(notation.getBody());
        priorityEditText.setText(String.valueOf(notation.getPriority()));
        dateEditText.setText(notation.getDate());
    }

    @Override
    public void showSmth() {
        Toast.makeText(getContext(),"AAAAAAARGS",Toast.LENGTH_LONG);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_todo, container, false);
        ButterKnife.bind(this, view);
        TodoApplication.getAppComponent().inject(this);
        presenter.getViewState().showSmth();
        notation = presenter.getCurrentNotation();
        getDialog().setTitle("Редактирование записей");
        return view;
    }

    @OnClick(R.id.button_confirm)
    public void onConfirmButtonClick() {
        notation.setBody(bodyEditText.getText().toString());
        notation.setDate(dateEditText.getText().toString());
        notation.setPriority(Integer.valueOf(priorityEditText.getText().toString()));
        notation.setTitle(titleEditText.getText().toString());
        dismiss();
    }

    @Override
    public void dismiss() {
        listener.onDismiss();
        super.dismiss();

    }
}
