package com.pkj.wow.couchbase.lite.console.ui.console;

import com.pkj.wow.couchbase.lite.console.ui.base.MvpPresenter;
import com.pkj.wow.couchbase.lite.console.ui.base.MvpView;

import java.util.List;

/**
 * Created by Pankaj on 22-01-2018.
 */

public interface ConsoleContract {

    interface View extends MvpView{
        void updateDbViewPager(List<String> dbNameList);

    }

    interface Presenter<V extends MvpView> extends MvpPresenter<V>{
        void onRefresh();
    }

}
