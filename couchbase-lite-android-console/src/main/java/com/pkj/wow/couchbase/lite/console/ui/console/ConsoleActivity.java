package com.pkj.wow.couchbase.lite.console.ui.console;

import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.pkj.wow.couchbase.lite.console.R;
import com.pkj.wow.couchbase.lite.console.data.AppDataManager;
import com.pkj.wow.couchbase.lite.console.ui.base.BaseActivity;
import com.pkj.wow.couchbase.lite.console.ui.console.adapter.DatabasePagerAdapter;
import com.pkj.wow.couchbase.lite.console.ui.view.FabProgressLayout;

import java.util.ArrayList;
import java.util.List;

public class ConsoleActivity extends BaseActivity implements ConsoleContract.View{
    private DatabasePagerAdapter mDatabasePagerAdapter;
    private ViewPager mViewPager;
    private ConsoleContract.Presenter mPresenter;
    private FabProgressLayout mFabProgressLayout;
    private final Handler mHandler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_console);

        mPresenter = new ConsolePresenter(this, new AppDataManager());

        List<String> databaseList = new ArrayList<>();
        mDatabasePagerAdapter = new DatabasePagerAdapter(getSupportFragmentManager(),databaseList);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mDatabasePagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        mPresenter.onRefresh();

        mFabProgressLayout  = (FabProgressLayout) findViewById(R.id.fab_progress);

        mFabProgressLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mPresenter.notifyDataSetChange();
                mFabProgressLayout.setEnabled(false);
                mFabProgressLayout.startProgress();
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mFabProgressLayout.stopProgress();
                        mFabProgressLayout.setEnabled(true);
                    }
                },1000);
            }
        });
        refreshDbStats();
    }

    @Override
    public void updateDbViewPager(List<String> dbNameList) {
        mDatabasePagerAdapter.update(dbNameList);
    }

    public void refreshDbStats(){
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPresenter.notifyDataSetChange();
                refreshDbStats();
            }
        }, 3000);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mHandler.removeCallbacksAndMessages(null);
    }
}
