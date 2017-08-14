package zakhargoryainov.todolist.home.todo.presentation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import lombok.Getter;
import zakhargoryainov.todolist.R;
import zakhargoryainov.todolist.base.MvpAppCompatFragment;
import zakhargoryainov.todolist.entities.TodoNotation;
import zakhargoryainov.todolist.home.todo.presentation.adapter.TodoRecyclerViewAdapter;
import zakhargoryainov.todolist.home.todo.presentation.dialog.create.TodoCreateDialogFragment;
import zakhargoryainov.todolist.home.todo.presentation.listener.OnSuccessDismissListener;
import zakhargoryainov.todolist.home.todo.presentation.listener.OnNotationClickListener;



public class TodoFragment extends MvpAppCompatFragment implements OnNotationClickListener, OnSuccessDismissListener, TodoView {

    RecyclerView todoRecyclerView;
    @InjectPresenter TodoPresenter presenter;
    TodoRecyclerViewAdapter adapter;
    DialogFragment dialogFragment;
    private @Getter FloatingActionButton.OnClickListener onFabClickListener;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todo, container, false);
        unbinder = ButterKnife.bind(getActivity());
        //TodoApplication.getAppComponent().inject(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        todoRecyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_view_todo);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        todoRecyclerView.setLayoutManager(llm);
        adapter = new TodoRecyclerViewAdapter(this, getContext());
        todoRecyclerView.setAdapter(adapter);
        onFabClickListener = v -> {
            presenter.prepareDialogForNewNotation();
            dialogFragment = TodoCreateDialogFragment.newInstance(getOnDismissListener());
            dialogFragment.show(getActivity().getSupportFragmentManager(), "+1 Notation");
        };
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    @Override
    public void onNotationClick(TodoNotation notation) {
        presenter.sendNotationToDialog(notation);
        dialogFragment = TodoCreateDialogFragment.newInstance(this); //details dialog
        dialogFragment.show(getActivity().getSupportFragmentManager(),"za4em?");
    }

    @Override
    public void onSuccessfulDismiss() {
        adapter.notifyDataSetChanged();
    }

    public OnSuccessDismissListener getOnDismissListener() {
        return this;
    }

    @Override
    public void onDataChanged(List<TodoNotation> notations) {
        adapter.setItems(notations);
    }

    @Override
    public void onDataError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG);
    }


    @Override
    public void onSuccess() {
        Toast.makeText(getContext(),"DONE",Toast.LENGTH_SHORT).show();
    }
}
