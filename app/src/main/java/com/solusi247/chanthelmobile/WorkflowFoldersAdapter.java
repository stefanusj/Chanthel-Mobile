package com.solusi247.chanthelmobile;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

public class WorkflowFoldersAdapter extends RecyclerView.Adapter<WorkflowFoldersAdapter.WorkflowViewHolder> {

    private WorkflowListener listener;
    private RecyclerView.LayoutManager layoutManager;

    public WorkflowFoldersAdapter(WorkflowListener listener, RecyclerView.LayoutManager layoutManager) {
        this.listener = listener;
        this.layoutManager = layoutManager;
    }

    @Override
    public WorkflowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (layoutManager instanceof GridLayoutManager) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_folder_grid, parent, false);
            return new WorkflowViewHolder(view);
        } else if (layoutManager instanceof LinearLayoutManager) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_folder_linear, parent, false);
            return new WorkflowViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(WorkflowViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 9;
    }

    class WorkflowViewHolder extends RecyclerView.ViewHolder {

        ImageView ivOverflow;
        TextView tvFolder, tvFile;

        public WorkflowViewHolder(View itemView) {
            super(itemView);
            ivOverflow = itemView.findViewById(R.id.ivOverflow);
            tvFolder = itemView.findViewById(R.id.tvFolder);
            tvFile = itemView.findViewById(R.id.tvFile);
            tvFolder.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.gotoFolder();
                }
            });
            ivOverflow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.showOptions();
                }
            });
        }
    }
}
