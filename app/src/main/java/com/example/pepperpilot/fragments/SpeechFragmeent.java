package com.example.pepperpilot.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.pepperpilot.R;
import com.example.pepperpilot.interfaces.ServerCallback;
import com.example.pepperpilot.requests.RequestMaker;

public class SpeechFragmeent extends Fragment {

    private Button sendSpeechB;
    private EditText speechET;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_speech, container, false);

        sendSpeechB = view.findViewById(R.id.buttonSendSpeech);
        speechET = view.findViewById(R.id.editTextSpeech);

        sendSpeechB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                RequestMaker.sendSpeech(new ServerCallback() {
                    @Override
                    public void onSuccess(String result) {
                        Toast.makeText(getActivity(), "SUCCESS", Toast.LENGTH_SHORT).show();
                        Toast.makeText(getActivity(), speechET.getText().toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(String result) {
                        Toast.makeText(getActivity(), "FAILURE", Toast.LENGTH_SHORT).show();
                    }
                },speechET.getText().toString(), getActivity());


            }
        });


        return view;
    }
}
