package com.solusi247.chanthelmobile;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class WorkflowAdapter extends RecyclerView.Adapter<WorkflowAdapter.WorkflowViewHolder> {

    private OverflowListener listener;

    public WorkflowAdapter(OverflowListener listener) {
        this.listener = listener;
    }

    @Override
    public WorkflowViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_folder_grid, parent, false);
        return new WorkflowViewHolder(view);
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

        public WorkflowViewHolder(View itemView) {
            super(itemView);
            ivOverflow = itemView.findViewById(R.id.ivOverflow);
            ivOverflow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.showOptions();
                }
            });
        }
    }
}
