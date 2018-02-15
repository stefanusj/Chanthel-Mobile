package com.solusi247.chanthelmobile;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class WorkflowFragment extends Fragment {

    BottomSheetBehavior bottomSheetBehavior;

    LinearLayout bottomSheet;
    RecyclerView rvFolders;
    private OverflowListener onOverflowClicked = new OverflowListener() {
        @Override
        public void showOptions() {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);

//          set the peek height
            bottomSheetBehavior.setPeekHeight((int) (80 * Resources.getSystem().getDisplayMetrics().density));

//          set hideable or not
            bottomSheetBehavior.setHideable(true);

        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_workflow, container, false);
        rvFolders = view.findViewById(R.id.rvFolders);
        bottomSheet = view.findViewById(R.id.bottom_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        rvFolders.setHasFixedSize(true);
        rvFolders.setLayoutManager(new GridLayoutManager(getActivity().getBaseContext(), 2));
        rvFolders.setAdapter(new WorkflowAdapter(onOverflowClicked));

    }
}
