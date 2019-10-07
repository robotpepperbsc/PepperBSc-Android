package com.example.pepperpilot.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.pepperpilot.R;

public class SpeechFragmeent extends Fragment {

    private Button sendSpeechB;

    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_speech,container,false);

        sendSpeechB = view.findViewById(R.id.buttonSendSpeech);



        sendSpeechB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Todo Implement send speech function




            }
        });


        return view;
    }
}
