package com.example.pepperpilot.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pepperpilot.R;
import com.example.pepperpilot.enums.Mode;
import com.example.pepperpilot.fragments.ManageScenarioFragment;
import com.example.pepperpilot.fragments.ScenarioTasksFragment;
import com.example.pepperpilot.interfaces.StringCallback;
import com.example.pepperpilot.models.Task;
import com.example.pepperpilot.requests.RequestMaker;


public class ScenariosActivity extends AppCompatActivity{
    private Mode mode;
    private TextView titleTV;
    private Button stopB;
    private String scenarioName;
    private String description;

    public static Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenarios);

        stopB = findViewById(R.id.stop_button);
        titleTV = findViewById(R.id.toolbar_title_text_view);

        Intent intent = getIntent();
        mode = (Mode) intent.getSerializableExtra("mode");
        scenarioName = intent.getStringExtra("name");
        description = intent.getStringExtra("description");


        switch (mode) {
            case EDIT:
                handleManageMode();
                break;
            case CREATE:
                handleManageMode();
                break;
            case SHOW:
                handleShow(intent.getStringExtra("name"));
                break;
        }


        stopB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RequestMaker.clearQueue(new StringCallback() {
                    @Override
                    public void onSuccess(String result) {
                        Toast.makeText(ScenariosActivity.this, "STOP", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(String result) {

                    }
                },ScenariosActivity.this);

            }
        });

    }


    private void handleManageMode() {
        ManageScenarioFragment fragment = new ManageScenarioFragment();
        Bundle args = new Bundle();


        if (mode.equals(Mode.EDIT)) {
            titleTV.setText("Edytuj scenariusz");
            args.putString("name", scenarioName);
            args.putString("description",description);

        } else if (mode.equals(Mode.CREATE)) {
            titleTV.setText("Stwórz scenariusz");
        }
        args.putSerializable("mode", mode);

        fragment.setArguments(args);
        setFragment(fragment);
    }

    private void handleShow(String name) {
        ScenarioTasksFragment fragment = new ScenarioTasksFragment();
        Bundle args = new Bundle();
        args.putString("name",scenarioName);
        titleTV.setText(name);
        fragment.setArguments(args);
        setFragment(fragment);
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.scenario_fragment, fragment);
        transaction.commit();
    }


    @Override
    protected void onStop() {
        super.onStop();
    }

}
