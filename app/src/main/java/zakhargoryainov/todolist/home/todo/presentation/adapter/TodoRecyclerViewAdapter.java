package zakhargoryainov.todolist.home.todo.presentation.adapter;

import android.content.Context;
import android.support.v7.util.DiffUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.ButterKnife;
import zakhargoryainov.todolist.PriorityViewUtils;
import zakhargoryainov.todolist.R;
import zakhargoryainov.todolist.TodoDiffUtilsCallback;
import zakhargoryainov.todolist.entities.TodoNotation;
import zakhargoryainov.todolist.home.OnNotationClickListener;
import zakhargoryainov.todolist.home.todo.presentation.TodoFragment;


public class TodoRecyclerViewAdapter extends RecyclerView.Adapter<TodoRecyclerViewAdapter.TodoViewHolder> {

    private List<TodoNotation> items;
    private OnNotationClickListener listener;

    public TodoRecyclerViewAdapter(OnNotationClickListener listener) {
        this.listener = listener;
        items = new ArrayList<>();
    }

    @Override
    public TodoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item_todo, parent, false);
        return new TodoRecyclerViewAdapter.TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TodoViewHolder holder, int position) {
        TodoNotation notation = items.get(position);
        holder.dateTextView.setText(notation.getFormattedDeadline());
        holder.titleTextView.setText(notation.getTitle());
        PriorityViewUtils.setPriority(holder.priorityView,notation.getPriority());
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }


    public void setItems(List<TodoNotation> items) {
//        DiffUtil.DiffResult result = DiffUtil.calculateDiff(new TodoDiffUtilsCallback(this.items, items));
//        result.dispatchUpdatesTo(this);
        this.items = items;
        notifyDataSetChanged();
    }

    public class TodoViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_view_title)
        TextView titleTextView;

        @BindView(R.id.text_view_date)
        TextView dateTextView;

        @BindView(R.id.view_priority)
        TextView priorityView;

        public TodoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(view -> listener.onNotationClick(items.get(getAdapterPosition()),getAdapterPosition()));
        }
    }
}
