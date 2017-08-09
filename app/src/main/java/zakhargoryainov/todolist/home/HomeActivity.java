package zakhargoryainov.todolist.home;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.astuetz.PagerSlidingTabStrip;

import zakhargoryainov.todolist.base.MvpAppCompatActivity;
import zakhargoryainov.todolist.R;

/**
 * Created by Захар on 02.08.2017.
 */

public class HomeActivity extends MvpAppCompatActivity {

    private ActionBarDrawerToggle toggle;
    private FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Typeface typeface = Typeface.create("casual",Typeface.BOLD_ITALIC);
        initActionBar(typeface);
        initNavigationDrawer();
        initViewPager(typeface);
        fab = (FloatingActionButton) findViewById(R.id.fab);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initActionBar(Typeface typeface){
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        for (int i = 0; i < toolbar.getChildCount(); i++){
            if (toolbar.getChildAt(i) instanceof TextView)
                ((TextView) toolbar.getChildAt(i)).setTypeface(typeface);
        }
    }

    private void initNavigationDrawer(){
        DrawerLayout drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.app_name, R.string.app_name);
        this.toggle = toggle;
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void initViewPager(Typeface typeface){
        ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        TabsPagerAdapter pagerAdapter = new TabsPagerAdapter(getSupportFragmentManager(),getBaseContext());
        viewPager.setAdapter(pagerAdapter);
        tabs.setViewPager(viewPager);
        tabs.setTypeface(typeface,Typeface.BOLD);
        tabs.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                if (positionOffset!= 0)
                    fab.setRotation(225 * positionOffset);
            }

            @Override
            public void onPageSelected(int position) {
                fab.setRotation(position*225);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
}

