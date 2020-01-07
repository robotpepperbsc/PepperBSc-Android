package com.example.pepperpilot.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pepperpilot.R;
import com.example.pepperpilot.interfaces.StringCallback;
import com.example.pepperpilot.requests.RequestMaker;

import java.util.ArrayList;
import java.util.List;

public class SpeechFragment extends Fragment {

    private Button sendSpeechB;
    private EditText textSpeechET;
    private SeekBar voiceVolumeSB;
    private EditText voiceSpeedET;
    private Spinner languageS;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_speech, container, false);

        sendSpeechB = view.findViewById(R.id.send_speech_button);
        textSpeechET = view.findViewById(R.id.text_speech_edit_text);
        voiceVolumeSB = view.findViewById(R.id.voice_volume_seek_bar);
        voiceSpeedET = view.findViewById(R.id.voice_speed_edit_text);
        languageS = view.findViewById(R.id.language_spinner);

        List<String> languages = new ArrayList<>();
        languages.add("Polish");
        languages.add("English");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_item, languages);

        languageS.setAdapter(dataAdapter);

        sendSpeechB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String text = textSpeechET.getText().toString();
                double volume = voiceVolumeSB.getProgress() / 100.0;
                int voiceSpeed = Integer.parseInt(voiceSpeedET.getText().toString());
                String language = languageS.getSelectedItem().toString();

                if (isInputCorrect(text, volume, voiceSpeed)) {
                    RequestMaker.sendSpeech(new StringCallback() {
                        @Override
                        public void onSuccess(String result) {
                            Toast.makeText(getActivity(), "SUCCESS", Toast.LENGTH_SHORT).show();

                        }

                        @Override
                        public void onError(String result) {
                            Toast.makeText(getActivity(), "FAILURE", Toast.LENGTH_SHORT).show();
                        }
                    },text,volume,voiceSpeed,language,getActivity());
                }
            }
        });

        return view;
    }

    private boolean isInputCorrect(String text, double volume, int voiceSpeed) {
        if (400 < voiceSpeed || voiceSpeed < 50) return false;

        return true;
    }
}
