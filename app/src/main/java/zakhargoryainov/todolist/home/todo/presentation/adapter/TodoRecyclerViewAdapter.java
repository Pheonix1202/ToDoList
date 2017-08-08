package zakhargoryainov.todolist.home.todo.presentation.adapter;

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
import zakhargoryainov.todolist.TodoNotation;
import zakhargoryainov.todolist.home.todo.TodoNotationCollapsed;

/**
 * Created by Захар on 07.08.2017.
 */

public class TodoRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<TodoNotationCollapsed> items;

    @Inject
    Context context;

    public TodoRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == 1){
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item_todo,parent,false);
            return new TodoViewHolder(view);}
        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_view_item_empty,parent,false);
        return new EmptyViewHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount()-1) return 0;
        else return 1;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder someHolder, int position) {
        TodoViewHolder holder;
        if (someHolder instanceof TodoViewHolder) {
            holder = (TodoViewHolder) someHolder;
            TodoNotationCollapsed notation = items.get(position);
            holder.dateTextView.setText(notation.getDate());
            holder.titleTextView.setText(notation.getTitle());
            switch (notation.getPriority()) {
                case TodoNotation.TIER_1:
                    holder.indicatorImageView.setBackgroundColor(context.getResources().getColor(R.color.priority_tier_1));
                    break;
                case TodoNotation.TIER_2:
                    holder.indicatorImageView.setBackgroundColor(context.getResources().getColor(R.color.priority_tier_2));
                    break;
                case TodoNotation.TIER_3:
                    holder.indicatorImageView.setBackgroundColor(context.getResources().getColor(R.color.priority_tier_3));
                    break;
                case TodoNotation.TIER_4:
                    holder.indicatorImageView.setBackgroundColor(context.getResources().getColor(R.color.priority_tier_4));
                    break;
            }
        }
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }


    public void setItems(List<TodoNotationCollapsed> items) {
        this.items = items;
        items.add(null);
        notifyDataSetChanged();
    }

    public void addItem(TodoNotationCollapsed item){
        items.remove(items.size()-1);
        items.add(item);
        items.add(null);
        notifyDataSetChanged();
    }

    public class EmptyViewHolder extends RecyclerView.ViewHolder{

        public EmptyViewHolder(View itemView) {
            super(itemView);
        }
    }

    public class TodoViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.text_view_title)
        TextView titleTextView;

        @BindView(R.id.text_view_date)
        TextView dateTextView;

        @BindView(R.id.image_view_indicator)
        ImageView indicatorImageView;


        public TodoViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
}
