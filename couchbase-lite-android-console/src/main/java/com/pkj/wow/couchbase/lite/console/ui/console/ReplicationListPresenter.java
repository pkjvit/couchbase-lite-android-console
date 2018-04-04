package com.pkj.wow.couchbase.lite.console.ui.console;

import android.database.DataSetObserver;

import com.pkj.wow.couchbase.lite.console.CblConsole;
import com.pkj.wow.couchbase.lite.console.data.AppDataManager;
import com.pkj.wow.couchbase.lite.console.data.db.model.CouchDatabase;
import com.pkj.wow.couchbase.lite.console.ui.base.BasePresenter;
import com.pkj.wow.couchbase.lite.console.ui.base.MvpView;

/**
 * Created by Pankaj on 25-01-2018.
 */

public class ReplicationListPresenter<V extends MvpView> extends BasePresenter<V> implements ReplicationListContract.Presenter<V> {
    private ReplicationListContract.View mMvpView;
    private AppDataManager mAppDataManager;
    private DataSetObserver mDatabaseObserver;

    public ReplicationListPresenter(AppDataManager appDataManager, ReplicationListContract.View mvpView){
        mAppDataManager = appDataManager;
        mMvpView = mvpView;
        if (mDatabaseObserver == null) {
            setDatabaseObserver(new DatabaseObserver());
        }
    }

    private void dataSetChanged(){
        mMvpView.onUpdate(mAppDataManager.getAllReplicationList());
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

    @Override
    public void onInit() {
        mMvpView.onUpdate(mAppDataManager.getAllReplicationList());
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
