package com.example.pepperpilot.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pepperpilot.R;
import com.example.pepperpilot.enums.CallbackFragment;
import com.example.pepperpilot.enums.Mode;
import com.example.pepperpilot.fragments.AddTaskFragment;
import com.example.pepperpilot.fragments.ManageScenarioFragment;
import com.example.pepperpilot.fragments.ScenarioTasksFragment;
import com.example.pepperpilot.interfaces.CallbackI;
import com.example.pepperpilot.interfaces.CallbackTask;
import com.example.pepperpilot.models.Scenario;
import com.example.pepperpilot.models.Task;

public class ScenariosActivity extends AppCompatActivity implements CallbackI, CallbackTask {
    private Mode mode;
    private static int scenarioPosition = -1;
    private TextView titleTV;
    private Button stopB;
    private Scenario currentScenario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenarios);

        stopB = findViewById(R.id.stop_button);
        titleTV = findViewById(R.id.toolbar_title_text_view);
        Intent intent = getIntent();
        mode = (Mode) intent.getSerializableExtra("mode");

        currentScenario = new Scenario("NAME","DESCRIPTION","---");

        if (mode.equals(Mode.EDIT) || mode.equals(Mode.CREATE)) {
            handleManageMode(mode);
        } else if (mode.equals(Mode.SHOW)) {
            String name = intent.getStringExtra("name");
            scenarioPosition = intent.getIntExtra("position", -1);
            handleShow(name);
        }

        stopB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ScenariosActivity.this, "STOP", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public static int getScenarioPosition() {
        return scenarioPosition;
    }

    private void handleManageMode(Mode mode) {
        ManageScenarioFragment fragment = new ManageScenarioFragment();
        Bundle args = new Bundle();
        args.putSerializable("mode", mode);
        fragment.setArguments(args);

        if (mode.equals(Mode.EDIT)) {
            titleTV.setText("Edytuj scenariusz");
        } else if (mode.equals(Mode.CREATE)) {
            titleTV.setText("Stwórz scenariusz");
        }

        setFragment(fragment);
    }

    private void handleShow(String name) {
        ScenarioTasksFragment fragment = new ScenarioTasksFragment();
        Bundle args = new Bundle();
        setTitle(name);
        fragment.setArguments(args);
        setFragment(fragment);
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.scenario_fragment, fragment);
        transaction.commit();
    }

    @Override
    public void callbackMethod(CallbackFragment callbackFragment) {
        Log.d("=======================", "=====================");
        Log.d("ARG: : : ", String.valueOf(callbackFragment));
        Log.d("=======================", "=====================");

        if (callbackFragment.equals(CallbackFragment.ADD_TASK)) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.scenario_fragment, new AddTaskFragment());
            transaction.addToBackStack(null);
            transaction.commit();
        }


    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public void onClick(Task task) {
        Log.d("Callback","Callback with task");
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.scenario_fragment, new ManageScenarioFragment());
        transaction.commit();
    }
}
