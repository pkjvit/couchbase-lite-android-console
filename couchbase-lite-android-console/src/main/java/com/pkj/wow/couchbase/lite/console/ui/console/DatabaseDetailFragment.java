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

public class DatabaseDetailFragment extends BaseFragment implements DatabaseDetailContract.View{
    private static final String ARG_DB_NAME = "db_name";
    private String mDbName;

    private TextView mDbNameTV;
    private TextView mDocCountTV;
    private TextView mLastSequenceTV;
    private TextView mRevisionCountTV;
    private TextView mConflictCountTV;
    private TextView mReplicationCountTV;
    private TextView mPublicUUIDTV;
    private TextView mPrivateUUIDTV;

    private RecyclerView mRecyclerView;
    private ReplicationAdapter mReplicationAdapter;
    private DatabaseDetailContract.Presenter mPresenter;

    public DatabaseDetailFragment() {
        // Required empty public constructor
        mPresenter = new DatabaseDetailPresenter(new AppDataManager(), this);
    }


    public static DatabaseDetailFragment newInstance(String dbName) {
        DatabaseDetailFragment fragment = new DatabaseDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_DB_NAME, dbName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mDbName = getArguments().getString(ARG_DB_NAME);
        }
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
        return inflater.inflate(R.layout.fragment_database_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = (RecyclerView)  view.findViewById(R.id.recyclerView);
        mDbNameTV = (TextView) view.findViewById(R.id.tv_dbName);
        mDocCountTV = (TextView) view.findViewById(R.id.tv_docCount);
        mConflictCountTV = (TextView) view.findViewById(R.id.tv_conflictCount);
        mLastSequenceTV = (TextView) view.findViewById(R.id.tv_lastSeq);
        mRevisionCountTV = (TextView) view.findViewById(R.id.tv_revCount);
        mReplicationCountTV = (TextView) view.findViewById(R.id.tv_replicationCount);
        mPrivateUUIDTV = (TextView) view.findViewById(R.id.tv_privateUUID);
        mPublicUUIDTV = (TextView) view.findViewById(R.id.tv_publicUUID);

        mReplicationAdapter = new ReplicationAdapter(getContext(), new ArrayList<Replication>(), new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {

            }
        });

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mReplicationAdapter);

        mPresenter.onDatabaseInit(mDbName);
    }

    @Override
    protected void setUp(View view) {

    }

    @Override
    public void onUpdate(CouchDatabase couchDatabase) {
        mDbNameTV.setText(Html.fromHtml(String.format(getString(R.string.db_name),couchDatabase.getDbName())));
        mDocCountTV.setText(Html.fromHtml(String.format(getString(R.string.doc_count),couchDatabase.getDocumentCount())));
        mConflictCountTV.setText(Html.fromHtml(String.format(getString(R.string.conflict_count),couchDatabase.getConflictCount())));
        mLastSequenceTV.setText(Html.fromHtml(String.format(getString(R.string.last_seq),couchDatabase.getLastSequenceNumber())));
        mRevisionCountTV.setText(Html.fromHtml(String.format(getString(R.string.revision_count),couchDatabase.getRevisionCount())));
        mReplicationCountTV.setText(Html.fromHtml(String.format(getString(R.string.replication_count),couchDatabase.getReplicationCount())));
        mPrivateUUIDTV.setText(Html.fromHtml(String.format(getString(R.string.private_uuid),couchDatabase.getPrivateUUID())));
        mPublicUUIDTV.setText(Html.fromHtml(String.format(getString(R.string.public_uuid),couchDatabase.getPublicUUID())));

        mReplicationAdapter.addList(couchDatabase.getReplicationList());
    }
}
