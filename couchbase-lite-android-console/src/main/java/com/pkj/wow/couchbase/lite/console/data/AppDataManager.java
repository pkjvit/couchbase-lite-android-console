package com.pkj.wow.couchbase.lite.console.data;

import com.couchbase.lite.CouchbaseLiteException;
import com.couchbase.lite.Database;
import com.couchbase.lite.Manager;
import com.couchbase.lite.replicator.Replication;
import com.pkj.wow.couchbase.lite.console.CblConsole;
import com.pkj.wow.couchbase.lite.console.data.db.model.CouchDatabase;

import java.util.List;

/**
 * Created by Pankaj on 23-01-2018.
 */

public class AppDataManager implements DataManager {
    private Manager mManager;


    public AppDataManager(){
        mManager = CblConsole.instance().getmManager();
    }

    @Override
    public List<String> getDbNameList() {
        return mManager.getAllDatabaseNames();
    }

    @Override
    public CouchDatabase getCouchDatabase(String dbName) {
        try {
            Database database = mManager.getExistingDatabase(dbName);
            if(database != null){
                return new CouchDatabase(database);
            }else{
                return null;
            }
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Replication> getReplicationList(String dbName) {
        try {
            return mManager.getExistingDatabase(dbName).getAllReplications();
        } catch (CouchbaseLiteException e) {
            e.printStackTrace();
        }
        return null;
    }
}
