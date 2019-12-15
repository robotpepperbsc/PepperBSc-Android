package com.example.pepperpilot.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pepperpilot.adapters.RecordingsAdapter;
import com.example.pepperpilot.R;
import com.example.pepperpilot.models.Recording;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class RecordingsFragment extends Fragment {

    private List<Recording> recordingList;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recordingList = new LinkedList<>();
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recordings,container,false);

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);

        recordingList = new ArrayList<>();

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewRecordings);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        RecordingsAdapter recordingAdapter = new RecordingsAdapter(recordingList,getActivity());
        recyclerView.setAdapter(recordingAdapter);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {





            }
        });

        return view;
    }
}
