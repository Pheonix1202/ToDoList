package zakhargoryainov.todolist.home.todo.presentation;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import zakhargoryainov.todolist.R;
import zakhargoryainov.todolist.app.TodoApplication;
import zakhargoryainov.todolist.base.MvpAppCompatFragment;
import zakhargoryainov.todolist.entities.TodoNotation;
import zakhargoryainov.todolist.home.todo.presentation.adapter.TodoRecyclerViewAdapter;
import zakhargoryainov.todolist.home.todo.presentation.dialog.TodoNotationEditDialogFragment;
import zakhargoryainov.todolist.home.todo.presentation.listener.OnDismissListener;
import zakhargoryainov.todolist.home.todo.presentation.listener.OnNotationClickListener;

/**
 * Created by Захар on 02.08.2017.
 */


public class TodoFragment extends MvpAppCompatFragment implements OnNotationClickListener, OnDismissListener, TodoView {

    RecyclerView todoRecyclerView;
    @InjectPresenter TodoPresenter presenter;
    TodoRecyclerViewAdapter adapter;
    DialogFragment dialogFragment;
    private Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todo,container,false);
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
        adapter = new TodoRecyclerViewAdapter(this,getContext());
        todoRecyclerView.setAdapter(adapter);
        adapter.setItems(getTodoNotations());
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
    @Override
    public void onNotationClick(TodoNotation notation) {
        presenter.sendNotationInDialog(notation);
        dialogFragment = TodoNotationEditDialogFragment.newInstance(this);
        dialogFragment.show(getActivity().getSupportFragmentManager(),"cancer_pes");
    }


    //todo replace
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

    @Override
    public void onDismiss() {
        adapter.notifyDataSetChanged();
    }
}
