package com.example.pepperpilot.fragments;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.pepperpilot.R;

public class IpConnectionFragment extends Fragment {

    private Button connectB;
    private TextView connectionStatusTV;
    private EditText ipAddressET;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ip_connect, container, false);

        connectB = view.findViewById(R.id.buttonConnect);
        ipAddressET = view.findViewById(R.id.editTextIP);
        connectionStatusTV = view.findViewById(R.id.textViewConnectionStatus);


        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
            connectB.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Toast.makeText(getActivity(), "CLICKED!", Toast.LENGTH_SHORT).show();

                    RequestQueue queue = Volley.newRequestQueue(getActivity());
                    String ipText = ipAddressET.getText().toString();
                    String url = "http://" + ipText + "/add_move?id=nazwa_ruchu";

                    StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    Toast.makeText(getActivity(), response, Toast.LENGTH_SHORT).show();

                                    if(response.equals("success")) {
                                        connectionStatusTV.setText("Connected succesfully to server!");
                                        connectionStatusTV.setTextColor(Color.parseColor("#43A047"));
                                    } else {
                                        connectionStatusTV.setText("Connection error!");
                                        connectionStatusTV.setTextColor(Color.parseColor("#e53935"));
                                    }




                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            connectionStatusTV.setText("Connection error!");
                            connectionStatusTV.setTextColor(Color.parseColor("#e53935"));
                            error.printStackTrace();
                            Toast.makeText(getActivity(), "ERROR", Toast.LENGTH_SHORT).show();
                        }
                    });

                    queue.add(stringRequest);
                }
            });
        }

        return view;
    }


}
