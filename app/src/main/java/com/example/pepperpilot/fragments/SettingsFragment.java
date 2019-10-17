package com.example.pepperpilot.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.SeekBar;
import android.widget.Spinner;

import com.example.pepperpilot.R;

public class SettingsFragment extends Fragment {

    private Spinner voiceS;
    private Spinner languageS;
    private SeekBar volumeSB;



    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings,container,false);

        languageS = view.findViewById(R.id.spinnerLanguage);
        String[] languagesArray = new String[]{"polish","english"};
        ArrayAdapter<String> languagesArrayAdapter = new ArrayAdapter<String>(getActivity(),R.layout.support_simple_spinner_dropdown_item,languagesArray);
        languageS.setAdapter(languagesArrayAdapter);


        voiceS = view.findViewById(R.id.spinnerVoice);
        String[] voiceArray = new String[]{"polish","english"};
        ArrayAdapter<String> voicesArrayAdapter = new ArrayAdapter<String>(getActivity(),R.layout.support_simple_spinner_dropdown_item,voiceArray);
        voiceS.setAdapter(voicesArrayAdapter);

        volumeSB = view.findViewById(R.id.seekBarVoicwVolume);
        volumeSB.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        return view;
    }
}
