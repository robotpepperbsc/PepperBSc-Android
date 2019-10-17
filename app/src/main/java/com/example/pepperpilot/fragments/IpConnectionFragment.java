package com.example.pepperpilot.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pepperpilot.R;
import com.example.pepperpilot.interfaces.ServerCallback;
import com.example.pepperpilot.requests.RequestMaker;

public class IpConnectionFragment extends Fragment {

    private Button connectB;
    private TextView connectionStatusTV;
    private EditText ipAddressET;
    private EditText portAddressET;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ip_connect, container, false);

        connectB = view.findViewById(R.id.buttonConnect);
        ipAddressET = view.findViewById(R.id.editTextIP);
        portAddressET = view.findViewById(R.id.editTextPort);
        connectionStatusTV = view.findViewById(R.id.textViewConnectionStatus);

        ipAddressET.setText(RequestMaker.IP);
        portAddressET.setText(RequestMaker.PORT);

        connectB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                RequestMaker.connect(new ServerCallback() {
                    @Override
                    public void onSuccess(String result) {
                        connectionStatusTV.setText("Connected succesfully to server!");
                        connectionStatusTV.setTextColor(Color.parseColor("#43A047"));
                    }

                    @Override
                    public void onError(String result) {
                        connectionStatusTV.setText("Connection error!");
                        connectionStatusTV.setTextColor(Color.parseColor("#e53935"));
                    }
                }, getActivity());


            }
        });


        return view;
    }


}
