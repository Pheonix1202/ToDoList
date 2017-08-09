package zakhargoryainov.todolist.home.done;

import zakhargoryainov.todolist.home.NotationCollapsed;

/**
 * Created by Захар on 08.08.2017.
 */

public class DoneNotationCollapsed extends NotationCollapsed {
    private boolean isSyncronized;

    public DoneNotationCollapsed(String title, String date, boolean isSyncronized) {
        super(title,date);
        this.isSyncronized = isSyncronized;
    }

    public boolean isSyncronized() {
        return isSyncronized;
    }

    public void setSyncronized(boolean syncronized) {
        isSyncronized = syncronized;
    }
}
