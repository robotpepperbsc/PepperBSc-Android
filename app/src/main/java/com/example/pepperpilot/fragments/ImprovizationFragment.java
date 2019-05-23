package com.example.pepperpilot.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.pepperpilot.R;

public class ImprovizationFragment extends Fragment {

    private Button speechB;
    private Button movementB;
    private Button screenDisplayingB;



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_improvization,container,false);

        speechB = view.findViewById(R.id.buttonSpeech);
        movementB = view.findViewById(R.id.buttonMovement);
        screenDisplayingB = view.findViewById(R.id.buttonScreenDisplaying);


        return view;
    }


}
