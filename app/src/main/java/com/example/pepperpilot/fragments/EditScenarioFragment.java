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
import com.example.pepperpilot.ScenariosSingleton;
import com.example.pepperpilot.activities.ScenariosActivity;
import com.example.pepperpilot.adapters.EditTasksAdapter;
import com.example.pepperpilot.enums.CallbackFragment;
import com.example.pepperpilot.models.Scenario;
import com.example.pepperpilot.models.Task;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class EditScenarioFragment extends Fragment {
    private EditText scenarioNameET;
    private EditText scenarioDescriptionET;
    private Button addTaskB;
    private Button saveAndExitB;
    private List<Task> taskList;
    private List<Scenario> scenarios;
    private int scenarioPosition;
    private Activity activity;
    private Scenario scenario;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_scenario, container, false);
        scenarioNameET = view.findViewById(R.id.editTextScenarioName);
        scenarioDescriptionET = view.findViewById(R.id.editTextScenarioDescription);
        addTaskB = view.findViewById(R.id.buttonAddTask);
        saveAndExitB = view.findViewById(R.id.buttonSaveAndExit);



        scenarios = ScenariosSingleton.getInstance().getScenarios();


        scenarioPosition = ScenariosActivity.getScenarioPosition();
        scenarioNameET.setText(scenarios.get(scenarioPosition).getName());
        scenarioDescriptionET.setText(scenarios.get(scenarioPosition).getDescription());

        scenario = ScenariosSingleton.getInstance().getScenarios().get(scenarioPosition);
        taskList = scenario.getTasks();

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewEditTasks);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        EditTasksAdapter editTasksAdapter = new EditTasksAdapter(taskList,getActivity());
        recyclerView.setAdapter(editTasksAdapter);


        saveAndExitB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String scenarioName = scenarioNameET.getText().toString();
                String scenarioDescription = scenarioDescriptionET.getText().toString();
                String editDateTime;

                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String currentDateandTime = sdf.format(new Date());

                scenario.setName(scenarioName);
                scenario.setDescription(scenarioDescription);
                scenario.setLastDateTimeEdited(currentDateandTime);

                getActivity().finish();
            }
        });

        addTaskB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Add task clicked",Toast.LENGTH_SHORT).show();
                ((ScenariosActivity)activity).callbackMethod(CallbackFragment.ADD_TASK);
            }
        });


        return view;
    }


}