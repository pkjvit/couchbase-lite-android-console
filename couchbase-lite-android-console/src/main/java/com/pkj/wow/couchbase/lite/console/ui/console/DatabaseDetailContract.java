package com.pkj.wow.couchbase.lite.console.ui.console;

import com.pkj.wow.couchbase.lite.console.data.db.model.CouchDatabase;
import com.pkj.wow.couchbase.lite.console.ui.base.MvpPresenter;
import com.pkj.wow.couchbase.lite.console.ui.base.MvpView;

/**
 * Created by Pankaj on 25-01-2018.
 */

public interface DatabaseDetailContract {

    interface View extends MvpView {
        void onUpdate(CouchDatabase couchDatabase);
    }

    interface Presenter<V extends MvpView> extends MvpPresenter<V> {
        void onDatabaseInit(String dbName);
    }
}
