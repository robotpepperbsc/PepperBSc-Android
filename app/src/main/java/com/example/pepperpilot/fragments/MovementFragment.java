package com.example.pepperpilot.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.pepperpilot.R;
import com.example.pepperpilot.interfaces.ServerCallback;
import com.example.pepperpilot.requests.RequestMaker;

import java.util.ArrayList;
import java.util.List;

public class MovementFragment extends Fragment {
    private Spinner predefinedMovesS;
    private ImageButton playB;
    private ImageButton forwardB;
    private ImageButton backwardB;
    private ImageButton leftB;
    private ImageButton rightB;

    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movement,container,false);

        predefinedMovesS = view.findViewById(R.id.spinnerPredefinedMoves);
        playB = view.findViewById(R.id.buttonPlay);
        forwardB = view.findViewById(R.id.buttonForward);
        backwardB = view.findViewById(R.id.buttonBackward);
        leftB = view.findViewById(R.id.buttonLeft);
        rightB = view.findViewById(R.id.buttonRight);


        List<String> predefinedMoves = new ArrayList<>();
        predefinedMoves.add("Taniec1");
        predefinedMoves.add("Taniec2");
        predefinedMoves.add("Taniec3");
        predefinedMoves.add("Taniec4");
        predefinedMoves.add("Taniec5");
        predefinedMoves.add("Taniec6");


        ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(),R.layout.spinner_item,predefinedMoves);
        predefinedMovesS.setAdapter(adapter);


        playB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"PLAY", Toast.LENGTH_SHORT).show();
                //TODO implement button play action
            }
        });

        // Steering buttons actions
        forwardB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Forward",Toast.LENGTH_SHORT).show();

                RequestMaker.sendMovement(new ServerCallback() {
                    @Override
                    public void onSuccess(String result) {
                        Toast.makeText(getActivity(),"SUCCESS",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(String result) {
                        Toast.makeText(getActivity(),"ERROR",Toast.LENGTH_SHORT).show();
                    }
                },"","",getActivity());

            }
        });

        backwardB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Backward",Toast.LENGTH_SHORT).show();
                RequestMaker.sendMovement(new ServerCallback() {
                    @Override
                    public void onSuccess(String result) {
                        Toast.makeText(getActivity(),"SUCCESS",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(String result) {
                        Toast.makeText(getActivity(),"ERROR",Toast.LENGTH_SHORT).show();
                    }
                },"","",getActivity());
            }
        });

        leftB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Left",Toast.LENGTH_SHORT).show();
                RequestMaker.sendMovement(new ServerCallback() {
                    @Override
                    public void onSuccess(String result) {
                        Toast.makeText(getActivity(),"SUCCESS",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(String result) {
                        Toast.makeText(getActivity(),"ERROR",Toast.LENGTH_SHORT).show();
                    }
                },"","",getActivity());
            }
        });

        rightB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(),"Right",Toast.LENGTH_SHORT).show();
                RequestMaker.sendMovement(new ServerCallback() {
                    @Override
                    public void onSuccess(String result) {
                        Toast.makeText(getActivity(),"SUCCESS",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(String result) {
                        Toast.makeText(getActivity(),"ERROR",Toast.LENGTH_SHORT).show();
                    }
                },"","",getActivity());
            }
        });


        return view;
    }
}
