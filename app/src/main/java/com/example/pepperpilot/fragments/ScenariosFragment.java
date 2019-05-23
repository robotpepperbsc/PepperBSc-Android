package com.example.pepperpilot.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pepperpilot.Adapters.ScenariosAdapter;
import com.example.pepperpilot.R;
import com.example.pepperpilot.other.Scenario;

import java.util.ArrayList;
import java.util.List;

public class ScenariosFragment extends Fragment {
    private List<Scenario> scenarioList;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scenarios,container,false);

        scenarioList = new ArrayList<>();
        scenarioList.add(new Scenario("Scenariusz 1"));
        scenarioList.add(new Scenario("Scenariusz 2"));
        scenarioList.add(new Scenario("Scenariusz 3"));

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewScenarios);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        ScenariosAdapter scenariosAdapter = new ScenariosAdapter(scenarioList);
        recyclerView.setAdapter(scenariosAdapter);


        return view;
    }
}
