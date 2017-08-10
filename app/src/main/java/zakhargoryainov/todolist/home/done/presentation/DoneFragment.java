package zakhargoryainov.todolist.home.done.presentation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import zakhargoryainov.todolist.R;
import zakhargoryainov.todolist.app.TodoApplication;
import zakhargoryainov.todolist.base.MvpAppCompatFragment;
import zakhargoryainov.todolist.entities.TodoNotation;
import zakhargoryainov.todolist.home.done.presentation.adapter.DoneRecyclerViewAdapter;


public class DoneFragment extends MvpAppCompatFragment implements DoneView {

    @InjectPresenter  DonePresenter presenter;
    RecyclerView doneRecyclerView;
    DoneRecyclerViewAdapter adapter;

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
        adapter.setItems(getTodoNotations());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private List<TodoNotation> getTodoNotations(){
        List<TodoNotation> notations = new ArrayList<>(20);
        notations.add(new TodoNotation("Завтрак","9:30",1));
        notations.add(new TodoNotation("Обед","13:30",1));
        notations.add(new TodoNotation("Выгуливать собаку","14:30",1));
        notations.add(new TodoNotation("Договориться о встрече с Митей","15:00",3));
        notations.add(new TodoNotation("Пробежка","19:10",2));
        notations.add(new TodoNotation("Завтрак","9:30",1));
        notations.add(new TodoNotation("Обед","13:30",1));
        notations.add(new TodoNotation("Выгуливать собаку","14:30",1));
        notations.add(new TodoNotation("Договориться о встрече с Митей","15:00",3));
        notations.add(new TodoNotation("Пробежка","19:10",2));
        return notations;
    }
}
