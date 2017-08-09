package zakhargoryainov.todolist.home.todo.presentation;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import zakhargoryainov.todolist.R;
import zakhargoryainov.todolist.app.TodoApplication;
import zakhargoryainov.todolist.base.MvpAppCompatActivity;
import zakhargoryainov.todolist.entities.TodoNotation;

/**
 * Created by Захар on 09.08.2017.
 */

public class TodoNotationEditActivity extends MvpAppCompatActivity  {

    @Inject
    TodoPresenter presenter;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TodoApplication.getAppComponent().inject(this);
        setContentView(R.layout.activity_notation_edit);
    }

    @OnClick(R.id.button_confirm)
    public void onConfirmButtonClick(){
        TodoNotation notation = presenter.getCurrentNotation();
        notation.setBody(bodyEditText.getText().toString());
        notation.setDate(dateEditText.getText().toString());
        notation.setPriority(Integer.valueOf(bodyEditText.getText().toString()));
        notation.setTitle(titleEditText.getText().toString());
        presenter.updateDatabases(notation);
    }
}
