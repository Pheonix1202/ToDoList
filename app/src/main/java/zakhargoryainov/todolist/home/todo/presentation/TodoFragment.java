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
import android.view.animation.OvershootInterpolator;
import android.widget.Toast;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.dgreenhalgh.android.simpleitemdecoration.linear.EndOffsetItemDecoration;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import jp.wasabeef.recyclerview.animators.FadeInDownAnimator;
import jp.wasabeef.recyclerview.animators.SlideInLeftAnimator;
import jp.wasabeef.recyclerview.animators.SlideInRightAnimator;
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator;
import lombok.Getter;
import zakhargoryainov.todolist.R;
import zakhargoryainov.todolist.base.MvpAppCompatFragment;
import zakhargoryainov.todolist.entities.TodoNotation;
import zakhargoryainov.todolist.home.todo.OnCreateDialogDismissListener;
import zakhargoryainov.todolist.home.todo.OnDetailsDialogDismissListener;
import zakhargoryainov.todolist.home.todo.presentation.adapter.TodoRecyclerViewAdapter;
import zakhargoryainov.todolist.home.todo.presentation.dialog.create.TodoCreateDialogFragment;
import zakhargoryainov.todolist.home.todo.presentation.dialog.details.TodoDetailsDialogFragment;
import zakhargoryainov.todolist.home.OnNotationClickListener;


public class TodoFragment extends MvpAppCompatFragment
        implements OnNotationClickListener, OnDetailsDialogDismissListener, OnCreateDialogDismissListener, TodoView {

    private RecyclerView todoRecyclerView;
    @InjectPresenter TodoPresenter presenter;
    private TodoRecyclerViewAdapter adapter;
    private DialogFragment dialogFragment;
    private FloatingActionButton.OnClickListener onFabClickListener;
    private Unbinder unbinder;
    private FloatingActionButton fab;

    public static TodoFragment newInstance(FloatingActionButton fab) {
        TodoFragment fragment = new TodoFragment();
        fragment.setOnFabClickListener();
        fragment.fab = fab;
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_todo, container, false);
        unbinder = ButterKnife.bind(getActivity());
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView();
    }

    @Override
    public void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }

    @Override
    public void onNotationClick(TodoNotation notation, int itemPosition) {
        presenter.hideItems();
        dialogFragment = TodoDetailsDialogFragment.newInstance(notation,this,itemPosition);
        dialogFragment.show(getActivity().getSupportFragmentManager(), "za4em?");
    }

    @Override
    public void onCancel() {
        presenter.showItems();
    }

    @Override
    public void onCreateSuccess() {
        presenter.showItems();
    }

    @Override
    public void onCompleteSuccess(int position) {
        presenter.showItems();
    }

    @Override
    public void onDeleteSuccess(int position) {
        presenter.showItems();
    }


    @Override
    public void onDataChanged(List<TodoNotation> notations) {
        adapter.setItems(notations);
    }

    @Override
    public void onDataError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccess() {
        Toast.makeText(getContext(), "DONE", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void hideItems() {
        todoRecyclerView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showItems() {
        todoRecyclerView.setVisibility(View.VISIBLE);
    }


    @Override
    public void setOnFabClickListener() {
        onFabClickListener = v -> {
            presenter.hideItems();
            dialogFragment = TodoCreateDialogFragment.newInstance(this);
            dialogFragment.show(getActivity().getSupportFragmentManager(), "+1 Notation");
        };
    }

    public FloatingActionButton.OnClickListener getOnFabClickListener() {
        return onFabClickListener;
    }

    private void initRecyclerView() {
        todoRecyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_view_todo);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        todoRecyclerView.setLayoutManager(llm);
        adapter = new TodoRecyclerViewAdapter(this);
        todoRecyclerView.setAdapter(adapter);
        todoRecyclerView.addItemDecoration(
                new EndOffsetItemDecoration(getResources().getDimensionPixelOffset(R.dimen.padding_normal)));
        todoRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy <= 0) fab.show();
                else  fab.hide();
            }
        });
    }
}
