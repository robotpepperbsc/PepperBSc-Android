package com.example.pepperpilot.fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.example.pepperpilot.R;

import okhttp3.OkHttpClient;


public class IpConnectionFragment extends Fragment {

    private Button connectB;
    RequestQueue queue;
    OkHttpClient client;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ip_connect,container,false);

        connectB = view.findViewById(R.id.buttonConnect);





        connectB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Clicked!",Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }


}
