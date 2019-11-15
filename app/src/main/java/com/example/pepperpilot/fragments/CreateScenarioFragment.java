package com.example.pepperpilot.fragments;


import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pepperpilot.R;
import com.example.pepperpilot.ScenariosSingleton;
import com.example.pepperpilot.models.Scenario;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.support.constraint.Constraints.TAG;


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
                if (TextUtils.isEmpty(scenarioTitleET.getText().toString())) {
                    Toast.makeText(getActivity(), "Wprowadź tytuł scenariusza!", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(scenarioDescriptionET.getText().toString())) {
                    Toast.makeText(getActivity(), "Wprowadź opis scenariusza!", Toast.LENGTH_SHORT).show();
                } else {
                    String name = scenarioTitleET.getText().toString();
                    String description = scenarioDescriptionET.getText().toString();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    String currentDateandTime = sdf.format(new Date());


                    /*ScenariosSingleton.getInstance().getScenarios().add(new Scenario(name,description,currentDateandTime));
                    Toast.makeText(getActivity(),"Scenariusz utworzony pomyślnie!",Toast.LENGTH_SHORT).show();
                    getActivity().finish();*/
                }
            }
        });

        return view;
    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    public boolean isStoragePermissionGranted() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (getActivity().checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v(TAG, "Permission is granted");
                return true;
            } else {

                Log.v(TAG, "Permission is revoked");
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
                return false;
            }
        } else { //permission is automatically granted on sdk<23 upon installation
            Log.v(TAG, "Permission is granted");
            return true;
        }
    }

}
