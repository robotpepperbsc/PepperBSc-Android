package com.example.pepperpilot.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.pepperpilot.R;
import com.example.pepperpilot.enums.MovementDirection;
import com.example.pepperpilot.interfaces.ServerCallback;
import com.example.pepperpilot.requests.RequestMaker;


public class MovementFragment extends Fragment {
    private ImageButton forwardB;
    private ImageButton backwardB;
    private ImageButton leftB;
    private ImageButton rightB;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movement, container, false);

        forwardB = view.findViewById(R.id.forward_button);
        backwardB = view.findViewById(R.id.backward_button);
        leftB = view.findViewById(R.id.left_button);
        rightB = view.findViewById(R.id.right_button);


        // Steering buttons actions
        forwardB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Forward", Toast.LENGTH_SHORT).show();

                RequestMaker.sendMovement(new ServerCallback() {
                    @Override
                    public void onSuccess(String result) {
                        Toast.makeText(getActivity(), "SUCCESS", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(String result) {
                        Toast.makeText(getActivity(), "ERROR", Toast.LENGTH_SHORT).show();
                    }
                }, MovementDirection.FORWARD, getActivity());

            }
        });

        backwardB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Backward", Toast.LENGTH_SHORT).show();
                RequestMaker.sendMovement(new ServerCallback() {
                    @Override
                    public void onSuccess(String result) {
                        Toast.makeText(getActivity(), "SUCCESS", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(String result) {
                        Toast.makeText(getActivity(), "ERROR", Toast.LENGTH_SHORT).show();
                    }
                }, MovementDirection.BACK, getActivity());
            }
        });

        leftB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Left", Toast.LENGTH_SHORT).show();
                RequestMaker.sendMovement(new ServerCallback() {
                    @Override
                    public void onSuccess(String result) {
                        Toast.makeText(getActivity(), "SUCCESS", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(String result) {
                        Toast.makeText(getActivity(), "ERROR", Toast.LENGTH_SHORT).show();
                    }
                }, MovementDirection.LEFT, getActivity());
            }
        });

        rightB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Right", Toast.LENGTH_SHORT).show();
                RequestMaker.sendMovement(new ServerCallback() {
                    @Override
                    public void onSuccess(String result) {
                        Toast.makeText(getActivity(), "SUCCESS", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(String result) {
                        Toast.makeText(getActivity(), "ERROR", Toast.LENGTH_SHORT).show();
                    }
                }, MovementDirection.RIGHT, getActivity());
            }
        });


        return view;
    }
}
