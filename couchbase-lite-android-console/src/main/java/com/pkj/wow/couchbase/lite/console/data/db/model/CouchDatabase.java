package com.pkj.wow.couchbase.lite.console.data.db.model;

import android.os.Parcelable;

import com.couchbase.lite.Database;
import com.couchbase.lite.replicator.Replication;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Pankaj on 23-01-2018.
 */

public class CouchDatabase {

    private String dbName;
    private int documentCount;
    private long conflictCount;
    private long lastSequenceNumber;
    private long revisionCount;
    private int replicationCount;
    private String publicUUID = "";
    private String privateUUID = "";
    private Database mDatabase;

    public CouchDatabase(String dbName, int documentCount, int conflictCount, int lastSequenceNumber, int revisionCount, String publicUUID, String privateUUID) {
        this.dbName = dbName;
        this.documentCount = documentCount;
        this.conflictCount = conflictCount;
        this.lastSequenceNumber = lastSequenceNumber;
        this.revisionCount = revisionCount;
        this.publicUUID = publicUUID;
        this.privateUUID = privateUUID;
    }

    public CouchDatabase(Database database){
        mDatabase = database;
        dbName = database.getName();
        documentCount = database.getDocumentCount();
        lastSequenceNumber = database.getLastSequenceNumber();
        replicationCount = database.getAllReplications().size();
        publicUUID = database.publicUUID();
        privateUUID = database.privateUUID();
    }

    public List<Replication> getReplicationList(){
        if(mDatabase!=null)
            return  mDatabase.getAllReplications();
        else
            return new ArrayList<>();
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }

    public int getDocumentCount() {
        return documentCount;
    }

    public void setDocumentCount(int documentCount) {
        this.documentCount = documentCount;
    }

    public long getConflictCount() {
        return conflictCount;
    }

    public void setConflictCount(long conflictCount) {
        this.conflictCount = conflictCount;
    }

    public long getLastSequenceNumber() {
        return lastSequenceNumber;
    }

    public void setLastSequenceNumber(long lastSequenceNumber) {
        this.lastSequenceNumber = lastSequenceNumber;
    }

    public long getRevisionCount() {
        return revisionCount;
    }

    public int getReplicationCount() {
        return replicationCount;
    }

    public void setReplicationCount(int replicationCount) {
        this.replicationCount = replicationCount;
    }

    public void setRevisionCount(long revisionCount) {
        this.revisionCount = revisionCount;
    }

    public String getPublicUUID() {
        return publicUUID;
    }

    public void setPublicUUID(String publicUUID) {
        this.publicUUID = publicUUID;
    }

    public String getPrivateUUID() {
        return privateUUID;
    }

    public void setPrivateUUID(String privateUUID) {
        this.privateUUID = privateUUID;
    }
}
