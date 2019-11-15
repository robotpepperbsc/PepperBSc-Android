package com.example.pepperpilot.fragments;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.pepperpilot.R;
import com.example.pepperpilot.activities.ScenariosActivity;
import com.example.pepperpilot.adapters.BehaviorsAdapter;
import com.example.pepperpilot.enums.CallbackFragment;
import com.example.pepperpilot.interfaces.CallbackTask;

import java.util.LinkedList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NewTaskBehaviorFragment extends Fragment {
    private Button saveB;
    private CallbackTask callbackTask;


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            callbackTask = (CallbackTask) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement onViewSelected");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_task_behavior, container, false);

        saveB = view.findViewById(R.id.save_button);

        List<String> behaviorsList = new LinkedList<>();

        for (int i = 0; i < 100; i++) {
            behaviorsList.add("Title " + i);
        }

        RecyclerView recyclerView = view.findViewById(R.id.behaviors_recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        BehaviorsAdapter adapter = new BehaviorsAdapter(behaviorsList, getActivity());
        recyclerView.setAdapter(adapter);


        saveB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //callbackTask.onClick(null);

                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragmentAddTask, new ManageScenarioFragment());
                transaction.commit();

            }
        });


        return view;
    }

}
