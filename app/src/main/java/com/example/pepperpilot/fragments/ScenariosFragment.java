package com.example.pepperpilot.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pepperpilot.activities.ScenariosActivity;
import com.example.pepperpilot.adapters.ScenariosAdapter;
import com.example.pepperpilot.R;
import com.example.pepperpilot.enums.Mode;
import com.example.pepperpilot.models.Scenario;

import java.util.ArrayList;
import java.util.List;

public class ScenariosFragment extends Fragment {
    private List<Scenario> scenarioList;
    private FloatingActionButton floatingActionButton;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scenarios,container,false);

        scenarioList = new ArrayList<>();
        scenarioList.add(new Scenario("Pokaz robota nr 1","To jest jakiś przykładowy krotki opis scenariusz. Możesz tu napisac jakiego typu to scenariusz itp."));
        scenarioList.add(new Scenario("Pokaz robota nr 2","To jest jakiś przykładowy krotki opis scenariusz. Możesz tu napisac jakiego typu to scenariusz itp."));
        scenarioList.add(new Scenario("Scenariusz 3","To jest jakiś przykładowy krotki opis scenariusz. Możesz tu napisac jakiego typu to scenariusz itp."));

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewScenarios);
        floatingActionButton = view.findViewById(R.id.floatingActionButtonAdScenario);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        ScenariosAdapter scenariosAdapter = new ScenariosAdapter(getActivity(),scenarioList);
        recyclerView.setAdapter(scenariosAdapter);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ScenariosActivity.class);
                intent.putExtra("mode", Mode.CREATE);
                startActivity(intent);
            }
        });

        return view;
    }
}
