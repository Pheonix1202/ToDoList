package zakhargoryainov.todolist.home.done;

/**
 * Created by Захар on 18.08.2017.
 */

public interface OnDoneDialogDismissListener {
    void onCancel();
    void onItemDeleted(int position);
    void onItemRetrieved(int position);
}
