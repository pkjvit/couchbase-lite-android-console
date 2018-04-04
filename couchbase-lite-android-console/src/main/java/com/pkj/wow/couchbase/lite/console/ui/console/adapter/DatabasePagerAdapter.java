package com.pkj.wow.couchbase.lite.console.ui.console.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


import com.pkj.wow.couchbase.lite.console.ui.console.DatabaseDetailFragment;
import com.pkj.wow.couchbase.lite.console.ui.console.ReplicationListFragment;

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
        Fragment fragment;
        switch (position){
            case 0 :
                fragment = ReplicationListFragment.newInstance();
                break;
            default :
                fragment = DatabaseDetailFragment.newInstance(mDbNameList.get(position-1));
                break;
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return mDbNameList.size()+1;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        String title;
        switch (position){
            case 0 :
                title = "Sync *";
                break;
            default :
                title = mDbNameList.get(position-1);
                break;
        }
        return title;
    }
}