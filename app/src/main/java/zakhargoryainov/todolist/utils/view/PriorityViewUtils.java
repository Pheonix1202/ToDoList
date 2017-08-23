package zakhargoryainov.todolist.utils.view;


import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import zakhargoryainov.todolist.R;
import zakhargoryainov.todolist.app.TodoApplication;

public class PriorityViewUtils {

    public static TextView setPriority(TextView view, int priority){
        int color = findPriorityColor(priority);
        GradientDrawable shape = new GradientDrawable();
        shape.setShape(GradientDrawable.OVAL);
        shape.setStroke(
                TodoApplication.getAppComponent().getContext().getResources().getDimensionPixelSize(R.dimen.stroke_width_common),
                color
        );
        view.setBackground(shape);
        view.setTextColor(color);
        view.setText(String.valueOf(priority+1));
        return view;
    }

    private static int findPriorityColor(int priority) {
        Context context = TodoApplication.getAppComponent().getContext();
        switch (priority) {
            case 0:
                return ContextCompat.getColor(context, R.color.priority_tier_1);
            case 1:
                return ContextCompat.getColor(context, R.color.priority_tier_2);
            case 2:
                return ContextCompat.getColor(context, R.color.priority_tier_3);
            case 3:
                return ContextCompat.getColor(context, R.color.priority_tier_4);
            default:
                return -1;
        }
    }
}
