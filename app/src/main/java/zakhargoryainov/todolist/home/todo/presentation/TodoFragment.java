package zakhargoryainov.todolist.home.todo.presentation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import zakhargoryainov.todolist.R;
import zakhargoryainov.todolist.app.ToDoApplication;
import zakhargoryainov.todolist.base.MvpAppCompatFragment;
import zakhargoryainov.todolist.home.todo.TodoNotationCollapsed;
import zakhargoryainov.todolist.home.todo.presentation.adapter.TodoRecyclerViewAdapter;

/**
 * Created by Захар on 02.08.2017.
 */

public class TodoFragment extends MvpAppCompatFragment {
    @BindView(R.id.recycler_view_todo)
    RecyclerView todoRecyclerView;

    @Inject
    TodoRecyclerViewAdapter adapter;

    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todo,container,false);
        unbinder = ButterKnife.bind(getActivity());
        ToDoApplication.getAppComponent().inject(this);
        return view;
    }



    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onResume() {
        super.onResume();
        todoRecyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_view_todo);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        todoRecyclerView.setLayoutManager(llm);
        todoRecyclerView.setAdapter(adapter);
        adapter.setItems(getTodoNotations());
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    //todo replace
    private List<TodoNotationCollapsed> getTodoNotations(){
        List<TodoNotationCollapsed> notations = new ArrayList<>(20);
        notations.add(new TodoNotationCollapsed("Завтрак","9:30",1));
        notations.add(new TodoNotationCollapsed("Обед","13:30",1));
        notations.add(new TodoNotationCollapsed("Выгуливать собаку","14:30",1));
        notations.add(new TodoNotationCollapsed("Договориться о встрече с Митей","15:00",3));
        notations.add(new TodoNotationCollapsed("Пробежка","19:10",2));
        notations.add(new TodoNotationCollapsed("Завтрак","9:30",1));
        notations.add(new TodoNotationCollapsed("Обед","13:30",1));
        notations.add(new TodoNotationCollapsed("Выгуливать собаку","14:30",1));
        notations.add(new TodoNotationCollapsed("Договориться о встрече с Митей","15:00",3));
        notations.add(new TodoNotationCollapsed("Пробежка","19:10",2));
        return notations;
    }
}
