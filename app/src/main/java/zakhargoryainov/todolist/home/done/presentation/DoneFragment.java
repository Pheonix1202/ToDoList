package zakhargoryainov.todolist.home.done.presentation;

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
import com.dgreenhalgh.android.simpleitemdecoration.linear.EndOffsetItemDecoration;
import java.util.List;
import zakhargoryainov.todolist.R;
import zakhargoryainov.todolist.app.TodoApplication;
import zakhargoryainov.todolist.base.MvpAppCompatFragment;
import zakhargoryainov.todolist.entities.TodoNotation;
import zakhargoryainov.todolist.home.OnNotationClickListener;
import zakhargoryainov.todolist.home.done.OnDoneDialogDismissListener;
import zakhargoryainov.todolist.home.done.presentation.adapter.DoneRecyclerViewAdapter;
import zakhargoryainov.todolist.home.done.presentation.dialog.details.DoneDetailsDialogFragment;


public class DoneFragment extends MvpAppCompatFragment
        implements DoneView, OnNotationClickListener, OnDoneDialogDismissListener {

    @InjectPresenter DonePresenter presenter;
    private RecyclerView doneRecyclerView;
    private DoneRecyclerViewAdapter adapter;
    private FloatingActionButton.OnClickListener onFabClickListener;
    private DialogFragment dialogFragment;
    private FloatingActionButton fab;

    public static DoneFragment newInstance(FloatingActionButton fab) {
        DoneFragment fragment = new DoneFragment();
        fragment.fab = fab;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_done, container, false);
        TodoApplication.getAppComponent().inject(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initRecyclerView();
        onFabClickListener = v -> presenter.deleteNotations();
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
        Toast.makeText(getContext(), message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccess() {
        Toast.makeText(getContext(), "Hasagi", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void hideItems() {
       doneRecyclerView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showItems() {
        doneRecyclerView.setVisibility(View.VISIBLE);
    }

    private void initRecyclerView() {
        doneRecyclerView = (RecyclerView) getActivity().findViewById(R.id.recycler_view_done);
        LinearLayoutManager llm = new LinearLayoutManager(getContext());
        doneRecyclerView.setLayoutManager(llm);
        adapter = new DoneRecyclerViewAdapter(this,getContext());
        doneRecyclerView.setAdapter(adapter);
        doneRecyclerView.addItemDecoration(
                new EndOffsetItemDecoration(getResources().getDimensionPixelOffset(R.dimen.padding_normal)));
        doneRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy <= 0) fab.show();
                else  fab.hide();
            }
        });
    }

    @Override
    public void onNotationClick(TodoNotation notation,int position) {
        presenter.hideItems();
        dialogFragment = DoneDetailsDialogFragment.newInstance(notation,this,position);
        dialogFragment.show(getActivity().getSupportFragmentManager(), "za4em?");
    }

    @Override
    public void onCancel() {
        presenter.showItems();
    }

    @Override
    public void onItemDeleted(int position) {
        presenter.showItems();
        adapter.notifyItemRemoved(position);
    }

    @Override
    public void onItemRetrieved(int position) {
        presenter.showItems();
//        doneRecyclerView.setItemAnimator();
        adapter.notifyItemRemoved(position);
    }


    public FloatingActionButton.OnClickListener getOnFabClickListener() {
        return onFabClickListener;
    }


}
