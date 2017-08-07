package zakhargoryainov.todolist.authentication.presentation.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import zakhargoryainov.todolist.authentication.login.LoginFragment;
import zakhargoryainov.todolist.authentication.registration.RegistrationFragment;

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
