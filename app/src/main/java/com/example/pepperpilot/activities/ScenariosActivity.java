package com.example.pepperpilot.activities;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.pepperpilot.R;
import com.example.pepperpilot.enums.Mode;
import com.example.pepperpilot.fragments.CreateScenarioFragment;
import com.example.pepperpilot.fragments.EditScenarioFragment;
import com.example.pepperpilot.fragments.ScenarioWorkshopsFragment;

public class ScenariosActivity extends AppCompatActivity {
    private Mode mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenarios);

        Intent intent = getIntent();
        mode = (Mode)intent.getSerializableExtra("mode");

        if(mode.equals(Mode.EDIT)) {
            String name = intent.getStringExtra("name");
            handleEditMode(name);
        }
        else if(mode.equals(Mode.SHOW)) {
            String name = intent.getStringExtra("name");
            handleShow(name);
        } else {
            handleCreateMode();
        }

    }

    private void handleEditMode(String name) {
        EditScenarioFragment fragment = new EditScenarioFragment();
        Bundle args = new Bundle();
        args.putString("name",name);
        fragment.setArguments(args);
        setTitle("Edit scenario");
        setFragment(fragment);
    }

    private void handleCreateMode() {
        setTitle("Create scenario");
        setFragment(new CreateScenarioFragment());
    }

    private void handleShow(String name) {
        setTitle(name);
        setFragment(new ScenarioWorkshopsFragment());
    }

    private void setFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment,fragment);
        transaction.commit();
    }

}
