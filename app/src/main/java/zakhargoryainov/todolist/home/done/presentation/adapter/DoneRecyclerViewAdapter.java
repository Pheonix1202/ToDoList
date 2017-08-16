package zakhargoryainov.todolist.home.done.presentation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import zakhargoryainov.todolist.R;
import zakhargoryainov.todolist.entities.TodoNotation;

import static zakhargoryainov.todolist.entities.TodoNotation.*;


public class DoneRecyclerViewAdapter extends RecyclerView.Adapter<DoneRecyclerViewAdapter.DoneViewHolder> {

    private List<TodoNotation> items;
    @Inject
    Context context;


    public DoneRecyclerViewAdapter(Context context) {
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
        switch (notation.getPriority()) {
            case TIER_1:
                holder.indicatorImageView.setBackgroundColor(context.getResources().getColor(R.color.priority_tier_1));
                break;
            case TIER_2:
                holder.indicatorImageView.setBackgroundColor(context.getResources().getColor(R.color.priority_tier_2));
                break;
            case TIER_3:
                holder.indicatorImageView.setBackgroundColor(context.getResources().getColor(R.color.priority_tier_3));
                break;
            case TIER_4:
                holder.indicatorImageView.setBackgroundColor(context.getResources().getColor(R.color.priority_tier_4));
                break;
        }
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public void setItems(List<TodoNotation> items) {
        this.items = items;
        notifyDataSetChanged();
    }


    /**
     * ViewHolder
     */
    public class DoneViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_view_title_done)
        TextView titleTextView;

        @BindView(R.id.text_view_date_done)
        TextView dateTextView;

        @BindView(R.id.image_view_indicator_done)
        ImageView indicatorImageView;

        private DoneViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
