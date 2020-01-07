package com.example.pepperpilot.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.pepperpilot.R;
import com.example.pepperpilot.interfaces.JsonObjectCallback;
import com.example.pepperpilot.interfaces.StringCallback;
import com.example.pepperpilot.requests.RequestMaker;

import org.json.JSONException;
import org.json.JSONObject;

public class SettingsFragment extends Fragment {

    private Button getLogsB;
    private TextView batteryTV;
    private TextView queueTV;
    private TextView recordingTV;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);


        batteryTV = view.findViewById(R.id.log_battery_info);
        queueTV = view.findViewById(R.id.loq_queue_info);
        recordingTV = view.findViewById(R.id.log_recording_info);
        getLogsB = view.findViewById(R.id.button_get_logs);


        getLogsB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestMaker.getLogger(new JsonObjectCallback() {
                    @Override
                    public void onSuccess(JSONObject result) {
                        try {

                            int batteryValue = Integer.parseInt(result.getString("battery").split("%")[0]);


                            if (batteryValue <= 20) {
                                batteryTV.setTextColor(Color.parseColor("#e53935"));
                            } else if (batteryValue <= 60) {
                                batteryTV.setTextColor(Color.parseColor("#FFB300"));
                            } else {
                                batteryTV.setTextColor(Color.parseColor("#43A047"));
                            }
                            batteryTV.setText(result.getString("battery"));

                            boolean is_queue_empty = result.getBoolean("is_queue_empty");
                            boolean is_recording = result.getBoolean("is_recording");

                            if (is_queue_empty) {
                                queueTV.setText("Tak");
                                queueTV.setTextColor(Color.parseColor("#43A047"));
                            } else {
                                queueTV.setText("Nie");
                                queueTV.setTextColor(Color.parseColor("#e53935"));
                            }

                            if (is_recording) {
                                recordingTV.setText("Tak");
                                recordingTV.setTextColor(Color.parseColor("#43A047"));
                            } else {
                                recordingTV.setText("Nie");
                                recordingTV.setTextColor(Color.parseColor("#e53935"));
                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onError(JSONObject error) {
                        batteryTV.setText("Connection problem");
                        queueTV.setText("Connection problem");
                        recordingTV.setText("Connection problem");
                    }
                }, getActivity());
            }
        });

        return view;
    }
}
