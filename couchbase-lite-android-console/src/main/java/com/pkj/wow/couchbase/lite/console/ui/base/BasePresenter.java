package com.pkj.wow.couchbase.lite.console.ui.base;

/**
 * Created by Pankaj on 20-01-2018.
 */

public class BasePresenter<V extends MvpView> implements MvpPresenter<V>{
    public static final String TAG = BasePresenter.class.getSimpleName();

    private V mMvpView;

    @Override
    public void onAttach(V mvpView) {
        mMvpView = mvpView;
    }

    @Override
    public void onDetach() {
        mMvpView = null;
    }

    @Override
    public void onPause() {
    }

    @Override
    public void onResume() {

    }


    @Override
    public void notifyDataSetChange() {

    }

    public boolean isViewAttached() {
        return mMvpView != null;
    }

    public V getMvpView() {
        return mMvpView;
    }
}
