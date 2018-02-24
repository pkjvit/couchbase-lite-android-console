package com.pkj.wow.couchbase.lite.console.ui.console;

import android.database.DataSetObservable;
import android.database.DataSetObserver;
import android.support.v4.view.ViewPager;

import com.pkj.wow.couchbase.lite.console.CblConsole;
import com.pkj.wow.couchbase.lite.console.data.AppDataManager;
import com.pkj.wow.couchbase.lite.console.data.db.model.CouchDatabase;
import com.pkj.wow.couchbase.lite.console.ui.base.BasePresenter;
import com.pkj.wow.couchbase.lite.console.ui.base.MvpView;

/**
 * Created by Pankaj on 25-01-2018.
 */

public class DatabaseDetailPresenter<V extends MvpView> extends BasePresenter<V> implements DatabaseDetailContract.Presenter<V> {
    private DatabaseDetailContract.View mMvpView;
    private AppDataManager mAppDataManager;
    private String mDatabaseName;
    private DataSetObserver mDatabaseObserver;

    public DatabaseDetailPresenter(AppDataManager appDataManager, DatabaseDetailContract.View mvpView){
        mAppDataManager = appDataManager;
        mMvpView = mvpView;
        if (mDatabaseObserver == null) {
            setDatabaseObserver(new DatabaseObserver());
        }
    }

    @Override
    public void onDatabaseInit(String dbName) {
        mDatabaseName = dbName;
        CouchDatabase couchDatabase = mAppDataManager.getCouchDatabase(dbName);
        mMvpView.onUpdate(couchDatabase);
    }

    private void dataSetChanged(){
        CouchDatabase couchDatabase = mAppDataManager.getCouchDatabase(mDatabaseName);
        mMvpView.onUpdate(couchDatabase);
    }

    void setDatabaseObserver(DataSetObserver observer) {
        synchronized (this) {
            mDatabaseObserver = observer;
//            CblConsole.instance().registerDataSetObserver(mDatabaseObserver);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if(mDatabaseObserver !=null){
            CblConsole.instance().unregisterDataSetObserver(mDatabaseObserver);
        }
    }

    @Override
    public void onResume() {
        if(mDatabaseObserver !=null){
            CblConsole.instance().registerDataSetObserver(mDatabaseObserver);
        }
        super.onResume();
    }

    private class DatabaseObserver extends DataSetObserver {
        DatabaseObserver() {
        }

        @Override
        public void onChanged() {
            dataSetChanged();
        }
        @Override
        public void onInvalidated() {
            dataSetChanged();
        }
    }
}
