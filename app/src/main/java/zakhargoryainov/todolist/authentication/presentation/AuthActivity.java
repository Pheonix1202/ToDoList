package zakhargoryainov.todolist.authentication.presentation;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import zakhargoryainov.todolist.authentication.presentation.adapter.AuthPagerAdapter;
import zakhargoryainov.todolist.base.MvpAppCompatActivity;
import zakhargoryainov.todolist.R;

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

    @Override
    public void onBackPressed() {
        finish();
    }
}
