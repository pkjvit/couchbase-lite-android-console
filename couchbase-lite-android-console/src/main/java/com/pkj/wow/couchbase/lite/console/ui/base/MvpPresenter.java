package com.pkj.wow.couchbase.lite.console.ui.base;

/**
 * Created by Pankaj on 20-01-2018.
 */
public interface MvpPresenter<V extends MvpView> {

    void onAttach(V mvpView);

    void onDetach();

    void onPause();

    void onResume();

    void notifyDataSetChange();
}
