package com.pkj.wow.couchbase.lite.console.ui.console;

import com.couchbase.lite.replicator.Replication;
import com.pkj.wow.couchbase.lite.console.data.db.model.CouchDatabase;
import com.pkj.wow.couchbase.lite.console.ui.base.MvpPresenter;
import com.pkj.wow.couchbase.lite.console.ui.base.MvpView;

import java.util.List;

/**
 * Created by Pankaj on 25-01-2018.
 */

public interface ReplicationListContract {

    interface View extends MvpView {
        void onUpdate(List<Replication> replicationList);
    }

    interface Presenter<V extends MvpView> extends MvpPresenter<V> {
        void onInit();
    }
}
