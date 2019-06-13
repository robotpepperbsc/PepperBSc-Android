package com.example.pepperpilot.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.pepperpilot.R;
import com.example.pepperpilot.adapters.TaskTypeAdapter;
import com.example.pepperpilot.enums.TaskType;
import com.example.pepperpilot.interfaces.ClickI;
import com.example.pepperpilot.models.AddTaskType;

import java.util.ArrayList;
import java.util.List;

public class AddTaskFragment extends Fragment implements ClickI {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_task, container, false);


        List<AddTaskType> addTaskTypeList = new ArrayList<>();
        addTaskTypeList.add(new AddTaskType(TaskType.MOVE,"move",R.drawable.ic_directions_walk_black_24dp));
        addTaskTypeList.add(new AddTaskType(TaskType.TELL,"tell",R.drawable.ic_record_voice_over_black_24dp));
        addTaskTypeList.add(new AddTaskType(TaskType.SHOW_IMAGE_ON_THE_SCREEN,"show image",R.drawable.ic_cast_black_24dp));
        addTaskTypeList.add(new AddTaskType(TaskType.SHOW_VIDEO_ON_THE_SCREEN,"show video",R.drawable.ic_cast_black_24dp));


        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewTaskType);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayout.HORIZONTAL,false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        TaskTypeAdapter taskTypeAdapter = new TaskTypeAdapter(addTaskTypeList,getActivity(),this);
        recyclerView.setAdapter(taskTypeAdapter);

        getActivity().setTitle("Dodaj zadanie");


        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentAddTask,new NewTaskMovementFragment());
        transaction.commit();


        return view;
    }

    @Override
    public void onClick(int position) {
        Log.d("Position: ",String.valueOf(position));

        Fragment fragment;

        switch(position) {
            case 0:
                fragment = new NewTaskMovementFragment();
                break;
            case 1:
                fragment = new NewTaskSpeechFragment();
                break;
            case 2:
                fragment = new NewTaskShowImageFragment();
                break;
            case 3:
                fragment = new NewTaskShowVideoFragment() ;
                break;
            default:
                fragment = new NewTaskMovementFragment();
                break;
        }

        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragmentAddTask,fragment);
        transaction.commit();

    }
}
