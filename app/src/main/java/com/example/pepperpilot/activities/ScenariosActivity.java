package com.example.pepperpilot.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.pepperpilot.R;
import com.example.pepperpilot.enums.CallbackFragment;
import com.example.pepperpilot.enums.Mode;
import com.example.pepperpilot.fragments.AddTaskFragment;
import com.example.pepperpilot.fragments.CreateScenarioFragment;
import com.example.pepperpilot.fragments.EditScenarioFragment;
import com.example.pepperpilot.fragments.ScenarioTasksFragment;
import com.example.pepperpilot.interfaces.CallbackI;

public class ScenariosActivity extends AppCompatActivity implements CallbackI {
    private Mode mode;
    private static int scenarioPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenarios);

        Intent intent = getIntent();
        mode = (Mode)intent.getSerializableExtra("mode");



        if(mode.equals(Mode.EDIT)) {
            scenarioPosition = intent.getIntExtra("position",-1);
            handleEditMode();
        }
        else if(mode.equals(Mode.SHOW)) {
            String name = intent.getStringExtra("name");
            scenarioPosition = intent.getIntExtra("position",-1);
            handleShow(name);
        } else {
            handleCreateMode();
        }

    }

    public static int getScenarioPosition() {
        return scenarioPosition;
    }

    private void handleEditMode() {
        EditScenarioFragment fragment = new EditScenarioFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        setTitle("Edit scenario");
        setFragment(fragment);
    }

    private void handleCreateMode() {
        setTitle("Create scenario");
        setFragment(new CreateScenarioFragment());
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
        transaction.replace(R.id.fragment,fragment);
        transaction.commit();
    }

    @Override
    public void callbackMethod(CallbackFragment callbackFragment) {
        Log.d("=======================","=====================");
        Log.d("ARG: : : ", String.valueOf(callbackFragment));
        Log.d("=======================","=====================");
        if(callbackFragment.equals(CallbackFragment.ADD_TASK)) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment,new AddTaskFragment());
            transaction.commit();
        } else if(callbackFragment.equals(CallbackFragment.EDIT_SCENARIO)) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.fragment,new EditScenarioFragment());
            transaction.commit();
        }


    }

    @Override
    protected void onStop() {
        super.onStop();







    }
}
