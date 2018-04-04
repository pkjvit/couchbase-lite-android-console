package com.pkj.wow.couchbase.lite.console.ui.console;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.couchbase.lite.replicator.Replication;
import com.pkj.wow.couchbase.lite.console.R;
import com.pkj.wow.couchbase.lite.console.data.AppDataManager;
import com.pkj.wow.couchbase.lite.console.data.db.model.CouchDatabase;
import com.pkj.wow.couchbase.lite.console.ui.base.BaseFragment;
import com.pkj.wow.couchbase.lite.console.ui.console.adapter.RecyclerViewClickListener;
import com.pkj.wow.couchbase.lite.console.ui.console.adapter.ReplicationAdapter;

import java.util.ArrayList;
import java.util.List;

public class ReplicationListFragment extends BaseFragment implements ReplicationListContract.View{

    private RecyclerView mRecyclerView;
    private ReplicationAdapter mReplicationAdapter;
    private ReplicationListContract.Presenter mPresenter;

    public ReplicationListFragment() {
        // Required empty public constructor
        mPresenter = new ReplicationListPresenter<>(new AppDataManager(), this);
    }


    public static ReplicationListFragment newInstance() {
        ReplicationListFragment fragment = new ReplicationListFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onPause() {
        mPresenter.onPause();
        super.onPause();
    }

    @Override
    public void onResume() {
        mPresenter.onResume();
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_replication_all_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView)  view.findViewById(R.id.recyclerView);

        mReplicationAdapter = new ReplicationAdapter(getContext(), new ArrayList<Replication>(), new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {

            }
        });

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mReplicationAdapter);

        mPresenter.onInit();
    }

    @Override
    protected void setUp(View view) {

    }

    @Override
    public void onUpdate(List<Replication> replicationList) {
        mReplicationAdapter.addList(replicationList);
    }
}
