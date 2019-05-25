package com.example.pepperpilot.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pepperpilot.R;
import com.example.pepperpilot.adapters.TasksAdapter;
import com.example.pepperpilot.enums.TaskType;
import com.example.pepperpilot.models.Task;

import java.util.ArrayList;
import java.util.List;

public class ScenarioTasksFragment extends Fragment {
    private List<Task> taskList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scenario_tasks, container, false);

        taskList = new ArrayList<>();
        taskList.add(new Task(TaskType.MOVE,"ruch robota","Robot obraca się o 30 stopni a nastepnie jedzie do przodu 0,5m."));
        taskList.add(new Task(TaskType.TELL,"ruch robota","Robot obraca się o 30 stopni a nastepnie jedzie do przodu 0,5m."));
        taskList.add(new Task(TaskType.TELL,"ruch robota","Robot obraca się o 30 stopni a nastepnie jedzie do przodu 0,5m."));
        taskList.add(new Task(TaskType.MOVE,"ruch robota","Robot obraca się o 30 stopni a nastepnie jedzie do przodu 0,5m."));


        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewTasks);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        TasksAdapter tasksAdapter = new TasksAdapter(getActivity(),taskList);
        recyclerView.setAdapter(tasksAdapter);

        return view;
    }


}
