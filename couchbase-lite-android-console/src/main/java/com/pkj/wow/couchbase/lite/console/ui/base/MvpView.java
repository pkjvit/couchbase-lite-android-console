package com.pkj.wow.couchbase.lite.console.ui.base;

import android.support.annotation.StringRes;

/**
 * Created by Pankaj on 20-01-2018.
 */

public interface MvpView {
    void showLoading();

    void hideLoading();

    void onError(@StringRes int resId);

    void onError(String message);

    void showMessage(String message);

    void showMessage(@StringRes int resId);

    boolean isNetworkConnected();

    void hideKeyboard();
}
