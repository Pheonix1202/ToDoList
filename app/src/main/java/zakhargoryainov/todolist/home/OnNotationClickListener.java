package zakhargoryainov.todolist.home;

import zakhargoryainov.todolist.entities.TodoNotation;

/**
 * Created by Захар on 09.08.2017.
 */

public interface OnNotationClickListener {
    void onNotationClick(TodoNotation notation, int position);
}
