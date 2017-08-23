package zakhargoryainov.todolist.home.done.presentation.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import zakhargoryainov.todolist.utils.view.PriorityViewUtils;
import zakhargoryainov.todolist.R;
import zakhargoryainov.todolist.entities.TodoNotation;
import zakhargoryainov.todolist.home.OnNotationClickListener;


public class DoneRecyclerViewAdapter extends RecyclerView.Adapter<DoneRecyclerViewAdapter.DoneViewHolder> {

    private List<TodoNotation> items;
    private OnNotationClickListener listener;
    private Context context;

    public DoneRecyclerViewAdapter(OnNotationClickListener listener, Context context) {
        this.listener = listener;
        this.context = context;
    }

    @Override
    public DoneViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item_done, parent, false);
        return new DoneViewHolder(view);

    }


    @Override
    public void onBindViewHolder(DoneViewHolder holder, int position) {
        TodoNotation notation = items.get(position);
        holder.dateTextView.setText(notation.getFormattedDeadline());
        holder.titleTextView.setText(notation.getTitle());
        PriorityViewUtils.setPriority(holder.priorityView,notation.getPriority());
        if(notation.isFailed()) holder.successView.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_failed));
        else holder.successView.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.ic_completed));
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public void setItems(List<TodoNotation> items) {
        this.items = items;
        notifyDataSetChanged();
    }


    public class DoneViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_view_title)
        TextView titleTextView;

        @BindView(R.id.text_view_date)
        TextView dateTextView;

        @BindView(R.id.view_priority)
        TextView priorityView;

        @BindView(R.id.view_success)
        ImageView successView;

        private DoneViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(view -> listener.onNotationClick(items.get(getAdapterPosition()),getAdapterPosition()));
        }
    }
}
