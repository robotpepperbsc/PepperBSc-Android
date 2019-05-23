package com.example.pepperpilot.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.pepperpilot.Adapters.RecordingsAdapter;
import com.example.pepperpilot.R;
import com.example.pepperpilot.other.Recording;

import java.util.ArrayList;
import java.util.List;

public class RecordingsFragment extends Fragment {

    private List<Recording> recordingList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recordings,container,false);

        recordingList = new ArrayList<>();
        recordingList.add(new Recording("VID0001","2019-10-10, 17:34"));
        recordingList.add(new Recording("VID0002","2019-04-10, 16:54"));
        recordingList.add(new Recording("VID0003","2019-10-19, 08:51"));
        recordingList.add(new Recording("VID0004","2019-03-02, 10:00"));
        recordingList.add(new Recording("VID0005","2019-05-17, 14:34"));

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewRecordings);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        RecordingsAdapter recordingAdapter = new RecordingsAdapter(recordingList,getActivity());
        recyclerView.setAdapter(recordingAdapter);


        return view;
    }
}
