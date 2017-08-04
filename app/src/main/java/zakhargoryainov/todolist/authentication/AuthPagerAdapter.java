package zakhargoryainov.todolist.todo.authentication;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import zakhargoryainov.todolist.todo.authentication.login.LoginFragment;
import zakhargoryainov.todolist.todo.authentication.registration.RegistrationFragment;
import zakhargoryainov.todolist.todo.home.done.DoneFragment;
import zakhargoryainov.todolist.todo.home.todo.TodoFragment;

/**
 * Created by Захар on 04.08.2017.
 */

public class AuthPagerAdapter extends FragmentPagerAdapter {

    private String tabTitles[] = new String[] { "To do", "Completed" };

    public AuthPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {
        switch (index) {
            case 0:
                // Top Rated fragment activity
                return new LoginFragment();
            case 1:
                // Games fragment activity
                return new RegistrationFragment();
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
