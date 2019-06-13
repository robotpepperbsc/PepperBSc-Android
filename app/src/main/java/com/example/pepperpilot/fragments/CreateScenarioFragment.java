package com.example.pepperpilot.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pepperpilot.R;
import com.example.pepperpilot.ScenariosSingleton;
import com.example.pepperpilot.models.Scenario;

import java.text.SimpleDateFormat;
import java.util.Date;


public class CreateScenarioFragment extends Fragment {
    private Button createScenarioB;
    private EditText scenarioTitleET;
    private EditText scenarioDescriptionET;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_create_scenario, container, false);

        createScenarioB = view.findViewById(R.id.buttonCreateScenario);
        scenarioTitleET = view.findViewById(R.id.editTextScenarioTitle);
        scenarioDescriptionET = view.findViewById(R.id.editTextScenarioDescription);

        createScenarioB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextUtils.isEmpty(scenarioTitleET.getText().toString())) {
                    Toast.makeText(getActivity(),"Wprowadź tytuł scenariusza!",Toast.LENGTH_SHORT).show();
                } else if(TextUtils.isEmpty(scenarioDescriptionET.getText().toString())) {
                    Toast.makeText(getActivity(),"Wprowadź opis scenariusza!",Toast.LENGTH_SHORT).show();
                } else {
                    String name = scenarioTitleET.getText().toString();
                    String description = scenarioDescriptionET.getText().toString();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String currentDateandTime = sdf.format(new Date());
                    ScenariosSingleton.getInstance().getScenarios().add(new Scenario(name,description,currentDateandTime));
                    Toast.makeText(getActivity(),"Scenariusz utworzony pomyślnie!",Toast.LENGTH_SHORT).show();
                    getActivity().finish();
                }
            }
        });

        return view;
    }


}
