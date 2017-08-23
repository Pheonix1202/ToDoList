package zakhargoryainov.todolist.utils.diffUtils;

import android.support.v7.util.DiffUtil;

import java.util.List;

import zakhargoryainov.todolist.entities.TodoNotation;

/**
 * Created by Захар on 21.08.2017.
 */

public class TodoDiffUtilsCallback extends DiffUtil.Callback {

    List<TodoNotation> oldList;
    List<TodoNotation> newList;

    public TodoDiffUtilsCallback(List<TodoNotation> oldList, List<TodoNotation> newList) {
        this.oldList = oldList;
        this.newList = newList;
    }

    @Override
    public int getOldListSize() {
        return oldList.size();
    }

    @Override
    public int getNewListSize() {
        return newList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getId() == newList.get(newItemPosition).getId();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        return oldList.get(oldItemPosition).getTitle().equals(newList.get(newItemPosition).getTitle());
    }


}
