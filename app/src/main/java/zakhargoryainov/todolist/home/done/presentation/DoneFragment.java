package zakhargoryainov.todolist.home.done.presentation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

import lombok.Getter;
import zakhargoryainov.todolist.R;
import zakhargoryainov.todolist.app.TodoApplication;
import zakhargoryainov.todolist.base.MvpAppCompatFragment;
import zakhargoryainov.todolist.entities.TodoNotation;
import zakhargoryainov.todolist.home.done.presentation.adapter.DoneRecyclerViewAdapter;


public class DoneFragment extends MvpAppCompatFragment implements DoneView {

    @InjectPresenter  DonePresenter presenter;
    private RecyclerView doneRecyclerView;
    private DoneRecyclerViewAdapter adapter;
    private @Getter FloatingActionButton.OnClickListener onFabClickListener;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_done,container,false);
        TodoApplication.getAppComponent().inject(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        doneRecyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_view_done);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        doneRecyclerView.setLayoutManager(llm);
        adapter = new DoneRecyclerViewAdapter(getContext());
        doneRecyclerView.setAdapter(adapter);
        onFabClickListener = v -> {
            presenter.deleteNotation(adapter.getItems());
            Toast.makeText(getContext(),"DONE FAB WORKS APPROPRIATELY",Toast.LENGTH_SHORT).show();
        };
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
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
        Toast.makeText(getContext(), "Hasagi", Toast.LENGTH_SHORT);
    }
}
