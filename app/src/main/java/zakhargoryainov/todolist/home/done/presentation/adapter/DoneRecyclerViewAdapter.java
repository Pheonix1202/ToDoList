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


public class DoneRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<TodoNotation> items;
    @Inject Context context;


    public DoneRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        switch (viewType) {
            case 0: view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.recycler_view_item_empty, parent, false);
                    return new EmptyViewHolder(view);

            case 1: view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.recycler_view_item_done, parent, false);
                    return new DoneViewHolder(view);

            default: return null;
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount()-1) return 0;
        else return 1;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder someHolder, int position) {
        DoneViewHolder holder;
        if (someHolder instanceof DoneViewHolder) {
            holder = (DoneViewHolder) someHolder;
            TodoNotation notation = items.get(position);
            holder.dateTextView.setText(notation.getDate());
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
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    public void setItems(List<TodoNotation> items) {
        this.items = items;
        items.add(null);
        notifyDataSetChanged();
    }

    public void addItem(TodoNotation item){
        items.remove(items.size()-1);
        items.add(item);
        items.add(null);
        notifyDataSetChanged();
    }


    /** ViewHolder */
     public class EmptyViewHolder extends RecyclerView.ViewHolder{

        private EmptyViewHolder(View itemView) {
            super(itemView);
        }
    }

    /** ViewHolder */
     public class DoneViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.text_view_title_done)
        TextView titleTextView;

        @BindView(R.id.text_view_date_done)
        TextView dateTextView;

        @BindView(R.id.image_view_indicator_done)
        ImageView indicatorImageView;

        private DoneViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
