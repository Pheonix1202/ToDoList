package zakhargoryainov.todolist.home.todo;


public interface OnDetailsDialogDismissListener {
    void onCancel();
    void onDeleteSuccess(int position);
    void onCompleteSuccess(int position);
}
