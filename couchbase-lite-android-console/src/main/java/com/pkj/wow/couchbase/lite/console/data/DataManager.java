package com.pkj.wow.couchbase.lite.console.data;

import com.couchbase.lite.replicator.Replication;
import com.pkj.wow.couchbase.lite.console.data.db.model.CouchDatabase;

import java.util.List;

/**
 * Created by Pankaj on 23-01-2018.
 */

public interface DataManager {

    List<String> getDbNameList();

    CouchDatabase getCouchDatabase(String dbName);

    List<Replication> getReplicationList(String dbName);

}
