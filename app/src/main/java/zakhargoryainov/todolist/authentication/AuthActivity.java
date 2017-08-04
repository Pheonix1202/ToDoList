package zakhargoryainov.todolist.todo.authentication;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;

import com.arellomobile.mvp.MvpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import zakhargoryainov.todolist.MvpAppCompatActivity;
import zakhargoryainov.todolist.R;
import zakhargoryainov.todolist.todo.authentication.login.LoginFragment;
import zakhargoryainov.todolist.todo.authentication.login.LoginFragment$$PresentersBinder;

/**
 * Created by Захар on 04.08.2017.
 */

public class AuthActivity extends MvpAppCompatActivity {

    @BindView(R.id.view_pager_auth)
    ViewPager authViewPager;

    Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        unbinder = ButterKnife.bind(this);
        authViewPager.setAdapter(new AuthPagerAdapter(getSupportFragmentManager()));
    }
}
