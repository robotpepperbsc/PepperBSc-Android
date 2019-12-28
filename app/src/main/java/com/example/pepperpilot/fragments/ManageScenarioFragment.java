package com.example.pepperpilot.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pepperpilot.requests.Parser;
import com.example.pepperpilot.R;
import com.example.pepperpilot.dialogs.TaskChoiceDialog;
import com.example.pepperpilot.activities.NewTaskBehaviorActivity;
import com.example.pepperpilot.activities.NewTaskMovementActivity;
import com.example.pepperpilot.activities.NewTaskMultimediaActivity;
import com.example.pepperpilot.activities.NewTaskSpeechActivity;
import com.example.pepperpilot.adapters.ManageTasksAdapter;
import com.example.pepperpilot.enums.CallbackFragment;
import com.example.pepperpilot.enums.MediaType;
import com.example.pepperpilot.enums.Mode;
import com.example.pepperpilot.enums.MovementDirection;
import com.example.pepperpilot.enums.TaskType;
import com.example.pepperpilot.interfaces.JsonObjectCallback;
import com.example.pepperpilot.interfaces.StringCallback;
import com.example.pepperpilot.models.Behavior;
import com.example.pepperpilot.models.Media;
import com.example.pepperpilot.models.Movement;
import com.example.pepperpilot.models.Scenario;
import com.example.pepperpilot.models.Speech;
import com.example.pepperpilot.requests.RequestMaker;

import org.json.JSONException;
import org.json.JSONObject;

import static android.app.Activity.RESULT_OK;


public class ManageScenarioFragment extends Fragment {
    private EditText scenarioNameET;
    private EditText scenarioDescriptionET;
    private Button addTaskB;
    private Button saveAndExitB;
    private Mode mode;
    private String scenarioName;
    private Scenario scenario;
    ManageTasksAdapter editTasksAdapter;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        scenario = new Scenario();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_manage_scenario, container, false);
        scenarioNameET = view.findViewById(R.id.editTextScenarioName);
        scenarioDescriptionET = view.findViewById(R.id.editTextScenarioDescription);
        addTaskB = view.findViewById(R.id.buttonAddTask);
        saveAndExitB = view.findViewById(R.id.buttonSaveAndExit);


        Bundle bundle = getArguments();
        mode = (Mode) bundle.getSerializable("mode");
        scenarioName = bundle.getString("name");

        Log.d("MODE",mode.toString());

        if (mode.equals(Mode.EDIT)) {
            // POBIERZ Z SERWERA SCENARIUSZ

            String description = bundle.getString("description");

            scenarioNameET.setText(scenarioName);
            scenarioDescriptionET.setText(description);

            RequestMaker.getScenarioDetails(new JsonObjectCallback() {
                @Override
                public void onSuccess(JSONObject result) {

                    try {
                        scenario.getTasksList().addAll(Parser.jsonObjectToScenarioTasks(result));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onError(JSONObject error) {

                }
            },getActivity(),scenarioName);

            //Wypelnic edit texty nazwa i opis scenariusza


        } else if (mode.equals(Mode.CREATE)) {



        }


        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewEditTasks);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        editTasksAdapter = new ManageTasksAdapter(scenario.getTasksList(), getActivity());
        recyclerView.setAdapter(editTasksAdapter);


        saveAndExitB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (mode.equals(Mode.EDIT)) {
                    scenario.setName(scenarioNameET.getText().toString());
                    scenario.setDescription(scenarioDescriptionET.getText().toString());
                    scenario.setLastDateTimeEdited("2019-10-10, 12:25");

                    RequestMaker.modifyScenario(new StringCallback() {
                        @Override
                        public void onSuccess(String result) {
                            Toast.makeText(getActivity(),"Wyedytowano pomyślnie!",Toast.LENGTH_SHORT).show();
                            getActivity().finish();
                        }

                        @Override
                        public void onError(String result) {
                            Toast.makeText(getActivity(),"Błąd edycji scenariusza!",Toast.LENGTH_SHORT).show();
                        }
                    },getActivity(),scenario);


                } else if (mode.equals(Mode.CREATE)) {

                    if(areScenarioEditTextsFilled()) {
                        scenario.setName(scenarioNameET.getText().toString());
                        scenario.setDescription(scenarioDescriptionET.getText().toString());
                        scenario.setLastDateTimeEdited("2019-10-10, 12:25");

                        RequestMaker.createScenario(new StringCallback() {
                            @Override
                            public void onSuccess(String result) {
                                Toast.makeText(getActivity(),"SUCCESS!",Toast.LENGTH_SHORT).show();
                                getActivity().finish();
                            }

                            @Override
                            public void onError(String result) {

                            }
                        },getActivity(),scenario);
                    } else {
                        Toast.makeText(getActivity(),"Wprowadź nazwę i opis scenariusza!",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        addTaskB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TaskChoiceDialog dialog = new TaskChoiceDialog(getActivity(), new TaskChoiceDialog.OnClickListener() {
                    @Override
                    public void taskEvent(CallbackFragment fragment) {
                        Intent intent;

                        switch (fragment) {
                            case MOVEMENT:
                                intent = new Intent(getActivity(), NewTaskMovementActivity.class);
                                startActivityForResult(intent, 1);
                                break;
                            case SPEECH:
                                intent = new Intent(getActivity(), NewTaskSpeechActivity.class);
                                startActivityForResult(intent, 2);
                                break;
                            case MULTIMEDIA:
                                intent = new Intent(getActivity(), NewTaskMultimediaActivity.class);
                                startActivityForResult(intent, 3);
                                break;
                            case BEHAVIOR:
                                intent = new Intent(getActivity(), NewTaskBehaviorActivity.class);
                                startActivityForResult(intent, 4);
                                break;
                        }
                    }
                });
                dialog.show();
            }
        });


        return view;
    }

    private boolean areScenarioEditTextsFilled() {
        return !TextUtils.isEmpty(scenarioNameET.getText().toString()) && !TextUtils.isEmpty(scenarioDescriptionET.getText().toString());
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                String description = data.getStringExtra("description");
                String taskName = data.getStringExtra("taskName");
                MovementDirection direction = (MovementDirection) data.getSerializableExtra("movementDirection");
                float distance = data.getFloatExtra("distance", (float) 0.1);


                scenario.addTask(new Movement(TaskType.MOVEMENT, taskName, description, direction, distance));
            }
        } else if (requestCode == 2) {
            if (resultCode == RESULT_OK) {

                String description = data.getStringExtra("description");
                String text = data.getStringExtra("text");
                String taskName = data.getStringExtra("taskName");
                float volume = data.getFloatExtra("volume", (float) 0.5);
                int speed = data.getIntExtra("speed", 100);
                String language = data.getStringExtra("language");

                scenario.addTask(new Speech(TaskType.SPEECH, taskName, description, text, (float) volume, speed, language));
            }
        } else if (requestCode == 3) {
            if (resultCode == RESULT_OK) {

                String description = data.getStringExtra("description");
                String name = data.getStringExtra("name");
                MediaType type = (MediaType)data.getSerializableExtra("mediaType");


                scenario.addTask(new Media(TaskType.SHOW_MULTIMEDIA,name,description,name,type));
            }
        } else if (requestCode == 4) {
            if (resultCode == RESULT_OK) {
                String name = data.getStringExtra("name");
                String description = data.getStringExtra("description");
                scenario.addTask(new Behavior(TaskType.BEHAVIOR,name,description,name));
            }
        }

        editTasksAdapter.notifyDataSetChanged();

    }
}