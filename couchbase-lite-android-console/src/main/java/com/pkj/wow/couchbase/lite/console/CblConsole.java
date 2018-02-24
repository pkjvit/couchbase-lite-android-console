package com.pkj.wow.couchbase.lite.console;

import android.content.Context;
import android.content.Intent;
import android.database.DataSetObservable;
import android.database.DataSetObserver;

import com.couchbase.lite.Manager;
import com.pkj.wow.couchbase.lite.console.ui.console.ConsoleActivity;

/**
 * Created by Pankaj on 19-01-2018.
 */

public class CblConsole {

    private Manager mManager;
    private static CblConsole sInstance;
    private final DataSetObservable mObservable = new DataSetObservable();

    private CblConsole(Manager manager){
        mManager = manager;
    }

    public static CblConsole instance(Manager manager){
        if (null == sInstance){
            sInstance = new CblConsole(manager);
        }
        return sInstance;
    }

    public static CblConsole instance(){
        return sInstance;
    }

    public void start(Context context){
        Intent intent = new Intent(context, ConsoleActivity.class);
        context.startActivity(intent);
    }

    public Manager getmManager(){
        return mManager;
    }


    public void notifyDataSetChanged() {
        mObservable.notifyChanged();
    }

    public void registerDataSetObserver(DataSetObserver observer) {
        mObservable.registerObserver(observer);
    }

    public void unregisterDataSetObserver(DataSetObserver observer) {
        mObservable.unregisterObserver(observer);
    }

}
