package zakhargoryainov.todolist.authentication.presentation;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import zakhargoryainov.todolist.authentication.presentation.adapter.AuthPagerAdapter;
import zakhargoryainov.todolist.R;


public class AuthActivity extends AppCompatActivity {

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
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}
