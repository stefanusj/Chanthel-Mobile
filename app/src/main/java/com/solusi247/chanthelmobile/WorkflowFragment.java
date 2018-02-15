package com.solusi247.chanthelmobile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class WorkflowFragment extends Fragment {

    public Boolean insideFolder = false;

    Menu menu;

    TextView tvFolder;
    RecyclerView rvFolders, rvFiles;
    private WorkflowListener onWorkflowClicked = new WorkflowListener() {

        @Override
        public void gotoFolder() {
            WorkflowFragment workflowFragment = new WorkflowFragment();
            workflowFragment.insideFolder = true;
            getFragmentManager().beginTransaction()
                    .replace(R.id.container, workflowFragment)
                    .addToBackStack(null)
                    .commit();
        }

        @Override
        public void showOptions() {
            BottomSheetOverflowFragment bottomSheetOverflowFragment = new BottomSheetOverflowFragment();
            bottomSheetOverflowFragment.show(getFragmentManager(), "");
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_workflow, container, false);

        setHasOptionsMenu(true);

        rvFolders = view.findViewById(R.id.rvFolders);
        rvFiles = view.findViewById(R.id.rvFiles);
        tvFolder = view.findViewById(R.id.tvFolder);

        rvFolders.setHasFixedSize(true);
        rvFolders.setNestedScrollingEnabled(false);
        rvFiles.setHasFixedSize(true);
        rvFiles.setNestedScrollingEnabled(false);

        if (insideFolder) tvFolder.setVisibility(View.VISIBLE);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        rvFolders.setLayoutManager(new GridLayoutManager(getActivity().getBaseContext(), 2));
        rvFolders.setAdapter(new WorkflowFoldersAdapter(onWorkflowClicked, rvFolders.getLayoutManager()));
        rvFiles.setLayoutManager(new GridLayoutManager(getActivity().getBaseContext(), 2));
        rvFiles.setAdapter(new WorkflowFilesAdapter(onWorkflowClicked, rvFolders.getLayoutManager()));

        tvFolder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });

    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        this.menu = menu;
        inflater.inflate(R.menu.workflow, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.actionGrid:
                rvFolders.setLayoutManager(new GridLayoutManager(getActivity().getBaseContext(), 2));
                rvFolders.setAdapter(new WorkflowFoldersAdapter(onWorkflowClicked, rvFolders.getLayoutManager()));
                rvFiles.setLayoutManager(new GridLayoutManager(getActivity().getBaseContext(), 2));
                rvFiles.setAdapter(new WorkflowFilesAdapter(onWorkflowClicked, rvFolders.getLayoutManager()));
                showMenu(R.id.actionList);
                hideMenu(R.id.actionGrid);
                break;
            case R.id.actionList:
                rvFolders.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
                rvFolders.setAdapter(new WorkflowFoldersAdapter(onWorkflowClicked, rvFolders.getLayoutManager()));
                rvFiles.setLayoutManager(new LinearLayoutManager(getActivity().getBaseContext()));
                rvFiles.setAdapter(new WorkflowFilesAdapter(onWorkflowClicked, rvFolders.getLayoutManager()));
                showMenu(R.id.actionGrid);
                hideMenu(R.id.actionList);
                break;
        }
        return true;
    }

    private void showMenu(int id) {
        MenuItem menuItem = menu.findItem(id);
        menuItem.setVisible(true);
    }

    private void hideMenu(int id) {
        MenuItem menuItem = menu.findItem(id);
        menuItem.setVisible(false);
    }
}
