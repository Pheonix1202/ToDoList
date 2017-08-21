package zakhargoryainov.todolist.authentication.presentation.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import zakhargoryainov.todolist.authentication.login.LoginFragment;
import zakhargoryainov.todolist.authentication.registration.RegistrationFragment;


public class AuthPagerAdapter extends FragmentPagerAdapter {

    public AuthPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {
        switch (index) {
            case 0: return new LoginFragment();
            case 1: return new RegistrationFragment();
            default: return null;
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
