package com.conformiz.milkconsumerapp.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.conformiz.milkconsumerapp.R;
import com.conformiz.milkconsumerapp.mainfragmentmanager.MyFragmentManager;

import java.util.EmptyStackException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MyFragmentManager myFragmentManager;
    Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myFragmentManager = MyFragmentManager.getInstance();
        myFragmentManager.initDashboardFragments(MainActivity.this);

        currentFragment = myFragmentManager.getHomeFragment();

        findViewById(R.id.iv_toolbar_back).setOnClickListener(this);

       // ((TextView)findViewById(R.id.tv_screen_header)).setText("Main Menu");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, currentFragment);
        transaction.commit();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch ((item.getItemId())) {
            case android.R.id.home:
                onBackPressed();
                //NavUtils.navigateUpFromSameTask(MainActivity.this);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onBackPressed() {

        try {
//            MyApplication.getInstance(getApplicationContext()).stopAutoLogoutTimeFun();
            if (myFragmentManager.getHomeTabStack().size() > 0)
                currentFragment = myFragmentManager.popFragment();
        } catch (EmptyStackException e) {
            e.printStackTrace();
            super.onBackPressed();
        }
        if (myFragmentManager.isStackEmpty()) {
            super.onBackPressed();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_toolbar_back:
                onBackPressed();
                break;
        }
    }
}
