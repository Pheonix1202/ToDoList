package zakhargoryainov.todolist.home;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import zakhargoryainov.todolist.home.done.DoneFragment;
import zakhargoryainov.todolist.home.todo.presentation.TodoFragment;

public class TabsPagerAdapter extends FragmentPagerAdapter {

    private String tabTitles[] = new String[] { "To do", "Completed" };

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {
        switch (index) {
            case 0:
                // Top Rated fragment activity
                return new TodoFragment();
            case 1:
                // Games fragment activity
                return new DoneFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 2;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position

        return tabTitles[position];
    }

}