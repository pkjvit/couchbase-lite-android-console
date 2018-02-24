package com.pkj.wow.couchbase.lite.console.ui.console;

import android.database.DataSetObservable;
import android.database.DataSetObserver;

import com.pkj.wow.couchbase.lite.console.CblConsole;
import com.pkj.wow.couchbase.lite.console.data.AppDataManager;
import com.pkj.wow.couchbase.lite.console.ui.base.BasePresenter;
import com.pkj.wow.couchbase.lite.console.ui.base.MvpView;

/**
 * Created by Pankaj on 23-01-2018.
 */

public class ConsolePresenter<V extends MvpView> extends BasePresenter<V> implements ConsoleContract.Presenter<V>{

    private ConsoleContract.View mView;
    private AppDataManager mAppDataManager;

    public ConsolePresenter(ConsoleContract.View view, AppDataManager appDataManager){
        mView = view;
        mAppDataManager = appDataManager;
    }

    @Override
    public void onRefresh() {
        mView.updateDbViewPager(mAppDataManager.getDbNameList());
    }

    @Override
    public void notifyDataSetChange(){
        CblConsole.instance().notifyDataSetChanged();
    }
}
