package zakhargoryainov.todolist.home.todo.presentation.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import zakhargoryainov.todolist.R;

/**
 * Created by Захар on 07.08.2017.
 */

public class TodoRecyclerViewAdapter extends RecyclerView.Adapter<TodoRecyclerViewAdapter.TodoViewHolder>{



    @Override
    public TodoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(TodoViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
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
