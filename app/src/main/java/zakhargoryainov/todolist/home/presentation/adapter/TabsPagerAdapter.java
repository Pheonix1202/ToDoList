package zakhargoryainov.todolist.home.presentation.adapter;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import zakhargoryainov.todolist.R;
import zakhargoryainov.todolist.home.done.presentation.DoneFragment;
import zakhargoryainov.todolist.home.todo.presentation.TodoFragment;


public class TabsPagerAdapter extends FragmentPagerAdapter {

    private TodoFragment todoFragment;
    private DoneFragment doneFragment;
    private String tabTitles[];

    public TabsPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        tabTitles = new String[] {
                context.getString(R.string.tab_title_todo),
                context.getString(R.string.tab_title_done)};
        doneFragment = new DoneFragment();
        todoFragment = new TodoFragment();
    }

    @Override
    public Fragment getItem(int index) {
        switch (index) {
            case 0: return todoFragment;
            case 1: return doneFragment;
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

    public FloatingActionButton.OnClickListener getFabListener(int index){
        switch (index) {
            case 0: return todoFragment.getOnFabClickListener();
            case 1: return doneFragment.getOnFabClickListener();
            default: return null;
        }
    }

}