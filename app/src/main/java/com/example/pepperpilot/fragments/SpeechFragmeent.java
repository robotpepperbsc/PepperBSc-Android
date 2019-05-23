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

    private Button sendB;

    public View onCreateView( LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_speech,container,false);

        sendB = view.findViewById(R.id.buttonSend);



        sendB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Send text to robot",Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }
}
