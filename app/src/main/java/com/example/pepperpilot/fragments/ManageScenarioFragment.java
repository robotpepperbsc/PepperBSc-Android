package com.example.pepperpilot.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pepperpilot.R;
import com.example.pepperpilot.activities.ScenariosActivity;
import com.example.pepperpilot.adapters.ManageTasksAdapter;
import com.example.pepperpilot.enums.CallbackFragment;
import com.example.pepperpilot.enums.Mode;
import com.example.pepperpilot.interfaces.CallbackI;
import com.example.pepperpilot.models.Scenario;
import com.example.pepperpilot.models.Task;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class ManageScenarioFragment extends Fragment {
    private EditText scenarioNameET;
    private EditText scenarioDescriptionET;
    private Button addTaskB;
    private Button saveAndExitB;
    private List<Task> taskList;
    private Activity activity;
    private Mode mode;
    private CallbackI callback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            callback = (CallbackI) context;
        }catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement onViewSelected");
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manage_scenario, container, false);
        scenarioNameET = view.findViewById(R.id.editTextScenarioName);
        scenarioDescriptionET = view.findViewById(R.id.editTextScenarioDescription);
        addTaskB = view.findViewById(R.id.buttonAddTask);
        saveAndExitB = view.findViewById(R.id.buttonSaveAndExit);

        Bundle bundle = getArguments();

        mode = (Mode) bundle.getSerializable("mode");

        taskList = new ArrayList();

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewEditTasks);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        ManageTasksAdapter editTasksAdapter = new ManageTasksAdapter(taskList,getActivity());
        recyclerView.setAdapter(editTasksAdapter);


        saveAndExitB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // tu bedzie trzeba zlecic do serwera zapisanie scenariusza



                getActivity().finish();
            }
        });

        addTaskB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.callbackMethod(CallbackFragment.ADD_TASK);
            }
        });


        return view;
    }


}