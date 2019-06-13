package com.example.pepperpilot.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pepperpilot.R;
import com.example.pepperpilot.ScenariosSingleton;
import com.example.pepperpilot.activities.ScenariosActivity;
import com.example.pepperpilot.enums.CallbackFragment;
import com.example.pepperpilot.enums.TaskType;
import com.example.pepperpilot.models.Image;


public class NewTaskShowImageFragment extends Fragment {

    private Button saveB;
    private Activity activity;

    private EditText titleET;
    private EditText descriptionET;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activity = getActivity();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_task_show_image,container,false);

        titleET = view.findViewById(R.id.editTextTitle);
        descriptionET = view.findViewById(R.id.editTextDescription);

        saveB = view.findViewById(R.id.buttonSave);

        saveB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleET.getText().toString();
                String description = descriptionET.getText().toString();

                Image image = new Image(TaskType.SHOW_IMAGE_ON_THE_SCREEN,title,description);
                ScenariosSingleton.getInstance().getScenarios().get(ScenariosActivity.getScenarioPosition()).addTask(image);
                ((ScenariosActivity)activity).callbackMethod(CallbackFragment.EDIT_SCENARIO);
            }
        });

        return view;
    }
}
