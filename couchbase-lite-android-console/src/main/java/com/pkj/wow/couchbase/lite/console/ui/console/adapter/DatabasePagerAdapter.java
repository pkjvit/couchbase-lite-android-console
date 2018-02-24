package com.pkj.wow.couchbase.lite.console.ui.console.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


import com.pkj.wow.couchbase.lite.console.ui.console.DatabaseDetailFragment;

import java.util.ArrayList;
import java.util.List;

public class DatabasePagerAdapter extends FragmentStatePagerAdapter {
    private List<String> mDbNameList = new ArrayList<>();

    public DatabasePagerAdapter(FragmentManager fm, List<String> dbNameList) {
        super(fm);
        update(dbNameList);
    }

    //call this method to update fragments in ViewPager dynamically
    public void update(List<String> dbNameList) {
        mDbNameList.clear();
        this.mDbNameList.addAll(dbNameList);
        notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(Object object) {
        return super.getItemPosition(object);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = DatabaseDetailFragment.newInstance(mDbNameList.get(position));
        return fragment;
    }

    @Override
    public int getCount() {
        return mDbNameList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mDbNameList.get(position);
    }
}