package zakhargoryainov.todolist.home.done.presentation.dialog.details;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.arellomobile.mvp.presenter.InjectPresenter;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import zakhargoryainov.todolist.R;
import zakhargoryainov.todolist.base.MvpDialogFragment;
import zakhargoryainov.todolist.entities.TodoNotation;
import zakhargoryainov.todolist.home.OnSuccessDismissListener;
import zakhargoryainov.todolist.home.done.OnDoneDialogDismissListener;


public class DoneDetailsDialogFragment extends MvpDialogFragment implements DoneDetailsView {

    @InjectPresenter DoneDetailsPresenter presenter;
    @BindView(R.id.text_view_title)
    TextView titleTextView;
    @BindView(R.id.text_view_body)
    TextView bodyTextView;
    @BindView(R.id.button_retrieve)
    Button retrieveButton;
    @BindView(R.id.button_delete)
    Button deleteButton;
    @BindView(R.id.text_view_done_status)
    TextView doneStatusTextView;
    private OnDoneDialogDismissListener listener;
    private int itemPosition;
    private TodoNotation notation;

    public static DoneDetailsDialogFragment newInstance(TodoNotation notation,OnDoneDialogDismissListener listener, int itemPosition) {
        DoneDetailsDialogFragment fragment = new DoneDetailsDialogFragment();
        fragment.itemPosition = itemPosition;
        fragment.notation = notation;
        fragment.listener = listener;
        return fragment;
    }



    @Override @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_done_details, container, false);
        ButterKnife.bind(this, view);
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
        if (notation.isFailed()) {
            doneStatusTextView.setText("Failed");
            doneStatusTextView.setTextColor(ContextCompat.getColor(getContext(),R.color.failed_red));
            doneStatusTextView.setCompoundDrawablesWithIntrinsicBounds(0,0, R.drawable.ic_failed,0);
        }
        else {
            doneStatusTextView.setText("Completed");
            doneStatusTextView.setTextColor(ContextCompat.getColor(getContext(),R.color.completed_green));
            doneStatusTextView.setCompoundDrawablesWithIntrinsicBounds(0,0, R.drawable.ic_completed,0);
        }
    }

    @Override
    public void onRetrieveSuccess() {
        listener.onItemRetrieved(itemPosition);
        dismiss();
    }

    @Override
    public void onDeleteSuccess() {
        listener.onItemDeleted(itemPosition);
        dismiss();
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        listener.onCancel();
    }

    @Override
    public void onError(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setListener(OnDoneDialogDismissListener listener) {
        this.listener = listener;
    }

    @Override
    public void setPosition(int position) {
        itemPosition = position;
    }

    @OnClick(R.id.button_retrieve)
    void onRetrieveButtonClick(){
        presenter.retrieveNotation();
    }

    @OnClick(R.id.button_delete)
    void onDeleteButtonClick(){
        presenter.deleteNotation();
    }
}
