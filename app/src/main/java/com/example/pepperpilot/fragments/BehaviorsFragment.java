package com.example.pepperpilot.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pepperpilot.R;
import com.example.pepperpilot.adapters.BehaviorsAdapter;

import java.util.LinkedList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class BehaviorsFragment extends Fragment {


    public BehaviorsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_behaviors, container, false);


        List<String> behaviorsList = new LinkedList<>();

        for (int i = 0; i < 1000; i++) {
            behaviorsList.add("Title " + i);
        }

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewBehaviors);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        BehaviorsAdapter adapter = new BehaviorsAdapter(behaviorsList, getActivity());
        recyclerView.setAdapter(adapter);


        return view;
    }

}
