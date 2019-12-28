package com.example.pepperpilot.fragments;


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
import android.widget.Toast;

import com.example.pepperpilot.R;
import com.example.pepperpilot.interfaces.StringCallback;
import com.example.pepperpilot.requests.RequestMaker;

import okhttp3.internal.Util;

public class IpConnectionFragment extends Fragment {

    private Button connectB;
    private TextView connectionStatusTV;
    private EditText ipAddressET;
    private EditText portAddressET;
    private EditText passwordET;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ip_connect, container, false);

        connectB = view.findViewById(R.id.connect_button);
        ipAddressET = view.findViewById(R.id.ip_edit_text);
        portAddressET = view.findViewById(R.id.port_edit_text);
        passwordET = view.findViewById(R.id.password_edit_text);
        connectionStatusTV = view.findViewById(R.id.connection_status_text_view);


        connectB.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(isAnyFieldEmpty()) {
                    Toast.makeText(getActivity(),"Uzupełnij wszystkie pola!",Toast.LENGTH_SHORT).show();
                } else {

                    String ip = ipAddressET.getText().toString();
                    int port = Integer.parseInt(portAddressET.getText().toString());
                    String password = passwordET.getText().toString();

                    RequestMaker.connect(new StringCallback() {
                        @Override
                        public void onSuccess(String result) {
                            connectionStatusTV.setText("Połączono pomyślnie!");
                            connectionStatusTV.setTextColor(Color.parseColor("#43A047"));
                        }

                        @Override
                        public void onError(String result) {
                            connectionStatusTV.setText("Błąd połączenia!");
                            connectionStatusTV.setTextColor(Color.parseColor("#e53935"));
                        }
                    }, getActivity(), ip, port, password);
                }




            }
        });


        return view;
    }

    private boolean isAnyFieldEmpty() {
        return ipAddressET.getText().toString().equals("") || portAddressET.getText().toString().equals("") || passwordET.getText().toString().equals("");
    }


}
