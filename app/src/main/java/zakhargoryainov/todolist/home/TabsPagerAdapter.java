package zakhargoryainov.todolist.home;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import zakhargoryainov.todolist.R;
import zakhargoryainov.todolist.home.done.presentation.DoneFragment;
import zakhargoryainov.todolist.home.todo.presentation.TodoFragment;

public class TabsPagerAdapter extends FragmentPagerAdapter {

    private String tabTitles[];

    public TabsPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        tabTitles = new String[] {
                context.getString(R.string.tab_title_todo),
                context.getString(R.string.tab_title_done)};
    }

    @Override
    public Fragment getItem(int index) {
        switch (index) {
            case 0: return new TodoFragment();
            case 1: return new DoneFragment();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }

}