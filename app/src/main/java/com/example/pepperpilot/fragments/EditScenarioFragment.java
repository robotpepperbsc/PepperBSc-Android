package com.example.pepperpilot.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.pepperpilot.R;


public class EditScenarioFragment extends Fragment {
    private EditText scenarioNameET;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_scenario, container, false);
        scenarioNameET = view.findViewById(R.id.editTextScenarioName);


        Bundle bundle = getArguments();


        scenarioNameET.setText(bundle.getString("name","DEFAULT"));



        return view;
    }


}