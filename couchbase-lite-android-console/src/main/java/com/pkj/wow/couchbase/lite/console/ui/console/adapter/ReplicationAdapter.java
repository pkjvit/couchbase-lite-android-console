package com.pkj.wow.couchbase.lite.console.ui.console.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.couchbase.lite.replicator.Replication;
import com.pkj.wow.couchbase.lite.console.R;

import java.util.List;

public class ReplicationAdapter extends RecyclerView.Adapter<ReplicationAdapter.MyViewHolder> {

    private List<Replication> mReplicationList;
    private RecyclerViewClickListener mRecyclerViewClickListener;
    private Context mContext;
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public TextView urlTV;
        public TextView lifeCycleTV;
        public TextView statusTV;
        public TextView changeCountTV;
        public TextView completedChangeCountTV;
        public TextView lastErrorTV;
        public TextView pendingCountTV;
        private RecyclerViewClickListener mListener;


        public MyViewHolder(View view, RecyclerViewClickListener listener) {
            super(view);
            mListener = listener;
            urlTV = (TextView) view.findViewById(R.id.tv_url);
            lifeCycleTV = (TextView) view.findViewById(R.id.tv_lifeCycle);
            statusTV = (TextView) view.findViewById(R.id.tv_status);
            changeCountTV = (TextView) view.findViewById(R.id.tv_changeCount);
            pendingCountTV = (TextView) view.findViewById(R.id.tv_pendingChangeCount);
            completedChangeCountTV = (TextView) view.findViewById(R.id.tv_completedChangeCount);
            lastErrorTV = (TextView) view.findViewById(R.id.tv_lastError);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            mListener.onClick(view, getAdapterPosition());
        }
    }


    public ReplicationAdapter(Context context, List<Replication> replicationList, RecyclerViewClickListener recyclerViewClickListener) {
        mContext = context;
        mReplicationList = replicationList;
        mRecyclerViewClickListener = recyclerViewClickListener;
    }

    public void addList(List<Replication> replicationList){
        mReplicationList.clear();
        mReplicationList.addAll(replicationList);
        this.notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_replication, parent, false);
 
        return new MyViewHolder(itemView, mRecyclerViewClickListener);
    }
 
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        Replication replication = mReplicationList.get(position);
        holder.urlTV.setText(Html.fromHtml(String.format(mContext.getString(R.string.url_db),replication.getRemoteUrl().toString())));
        holder.lifeCycleTV.setText(Html.fromHtml(String.format("<b>%s | %s</b>",replication.isContinuous()?"Continuous":"OneShot",
                                        replication.isPull()?"Pull":"Push")));
        holder.statusTV.setText(Html.fromHtml(String.format("<b>"+replication.getStatus().name()+"</b>")));
        switch (replication.getStatus()){
            case REPLICATION_IDLE:
                holder.statusTV.setBackgroundColor(mContext.getResources().getColor(R.color.idle));
                break;
            case REPLICATION_ACTIVE:
                holder.statusTV.setBackgroundColor(mContext.getResources().getColor(R.color.active));
                break;
            case REPLICATION_OFFLINE:
                holder.statusTV.setBackgroundColor(mContext.getResources().getColor(R.color.offline));
                break;
            case REPLICATION_STOPPED:
                holder.statusTV.setBackgroundColor(mContext.getResources().getColor(R.color.stopped));
                break;
        }
        holder.changeCountTV.setText(Html.fromHtml(String.format(mContext.getString(R.string.change_count),replication.getChangesCount())));
        holder.completedChangeCountTV.setText(Html.fromHtml(String.format(mContext.getString(R.string.completed_changes),replication.getCompletedChangesCount())));
        if(!replication.isPull()){
            holder.pendingCountTV.setText(Html.fromHtml(String.format(mContext.getString(R.string.pending_count),
                    (replication.getChangesCount()-replication.getCompletedChangesCount()))));
        }else{
            holder.pendingCountTV.setText("");
        }
        holder.lastErrorTV.setText(String.format(mContext.getString(R.string.last_error),String.valueOf(replication.getLastError())));
    }
 
    @Override
    public int getItemCount() {
        return mReplicationList.size();
    }
}