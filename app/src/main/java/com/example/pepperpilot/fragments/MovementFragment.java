package com.example.pepperpilot.fragments;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pepperpilot.R;
import com.example.pepperpilot.enums.MovementDirection;
import com.example.pepperpilot.interfaces.StringCallback;
import com.example.pepperpilot.requests.RequestMaker;

import java.util.ArrayList;
import java.util.List;


public class MovementFragment extends Fragment {
    private FloatingActionButton forwardB;
    private FloatingActionButton backwardB;
    private FloatingActionButton leftB;
    private FloatingActionButton rightB;

    private EditText forwardET;
    private EditText backwardET;

    private Spinner rotateLeftS;
    private Spinner rotateRightS;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movement, container, false);

        forwardB = view.findViewById(R.id.forward_button);
        backwardB = view.findViewById(R.id.backward_button);
        leftB = view.findViewById(R.id.left_button);
        rightB = view.findViewById(R.id.right_button);

        forwardET = view.findViewById(R.id.foward_edit_text);
        backwardET = view.findViewById(R.id.backward_edit_text);

        rotateLeftS = view.findViewById(R.id.spinner_rotate_left);
        rotateRightS = view.findViewById(R.id.spinner_rotate_right);

        List<String> rotationDegree = new ArrayList<>();
        rotationDegree.add("30");
        rotationDegree.add("45");
        rotationDegree.add("60");
        rotationDegree.add("90");
        rotationDegree.add("135");
        rotationDegree.add("180");
        rotationDegree.add("270");
        rotationDegree.add("360");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getActivity(), R.layout.spinner_item, rotationDegree);

        rotateLeftS.setAdapter(dataAdapter);
        rotateRightS.setAdapter(dataAdapter);


        // Steering buttons actions
        forwardB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Forward", Toast.LENGTH_SHORT).show();

                double value = Double.valueOf(forwardET.getText().toString());

                RequestMaker.sendMovement(new StringCallback() {
                    @Override
                    public void onSuccess(String result) {
                        Toast.makeText(getActivity(), "SUCCESS", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(String result) {
                        Toast.makeText(getActivity(), "ERROR", Toast.LENGTH_SHORT).show();
                    }
                }, MovementDirection.FORWARD, value, getActivity());

            }
        });

        backwardB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Backward", Toast.LENGTH_SHORT).show();
                double value = Double.valueOf(backwardET.getText().toString());

                RequestMaker.sendMovement(new StringCallback() {
                    @Override
                    public void onSuccess(String result) {
                        Toast.makeText(getActivity(), "SUCCESS", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(String result) {
                        Toast.makeText(getActivity(), "ERROR", Toast.LENGTH_SHORT).show();
                    }
                }, MovementDirection.BACK, value, getActivity());
            }
        });

        leftB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Left", Toast.LENGTH_SHORT).show();
                double value = degreeToRad(Integer.valueOf(rotateLeftS.getSelectedItem().toString()));


                RequestMaker.sendMovement(new StringCallback() {
                    @Override
                    public void onSuccess(String result) {
                        Toast.makeText(getActivity(), "SUCCESS", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(String result) {
                        Toast.makeText(getActivity(), "ERROR", Toast.LENGTH_SHORT).show();
                    }
                }, MovementDirection.LEFT, value, getActivity());
            }
        });

        rightB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Right", Toast.LENGTH_SHORT).show();
                double value = degreeToRad(Integer.valueOf(rotateRightS.getSelectedItem().toString()));

                RequestMaker.sendMovement(new StringCallback() {
                    @Override
                    public void onSuccess(String result) {
                        Toast.makeText(getActivity(), "SUCCESS", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(String result) {
                        Toast.makeText(getActivity(), "ERROR", Toast.LENGTH_SHORT).show();
                    }
                }, MovementDirection.RIGHT, value, getActivity());
            }
        });


        return view;
    }

    private double degreeToRad(int degree) {
        return Math.round((Math.PI/180.0)*degree * 100.0)/100.0;
    }
}
