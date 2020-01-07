package com.example.pepperpilot.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pepperpilot.requests.Parser;
import com.example.pepperpilot.R;
import com.example.pepperpilot.adapters.TasksAdapter;
import com.example.pepperpilot.dialogs.RunScenarioDialog;
import com.example.pepperpilot.interfaces.JsonObjectCallback;
import com.example.pepperpilot.interfaces.RunScenarioClick;
import com.example.pepperpilot.interfaces.StringCallback;
import com.example.pepperpilot.models.Task;
import com.example.pepperpilot.requests.RequestMaker;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

public class ScenarioTasksFragment extends Fragment {
    private LinkedList<Task> taskList;
    private FloatingActionButton playFAB;
    private String scenarioName;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       taskList = new LinkedList<>();

        Bundle bundle = getArguments();

        scenarioName = bundle.getString("name");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scenario_tasks, container, false);

        playFAB = view.findViewById(R.id.play_floating_action_button);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);

        Toast.makeText(getActivity(), "Tutaj zostanie wykonane zapytanie do serwera i wylistowane wszystkie taski dla scenariusza", Toast.LENGTH_LONG).show();

        final RecyclerView recyclerView = view.findViewById(R.id.recyclerViewTasks);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        final TasksAdapter tasksAdapter = new TasksAdapter(getActivity(), taskList);
        recyclerView.setAdapter(tasksAdapter);


        swipeRefreshLayout.setRefreshing(true);
        RequestMaker.getScenarioDetails(new JsonObjectCallback() {
            @Override
            public void onSuccess(JSONObject result) {
                taskList.clear();
                try {
                    taskList.addAll(Parser.jsonObjectToScenarioTasks(result));
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                tasksAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }
            @Override
            public void onError(JSONObject error) {
                swipeRefreshLayout.setRefreshing(false);
            }
        },getActivity(),scenarioName);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                RequestMaker.getScenarioDetails(new JsonObjectCallback() {
                    @Override
                    public void onSuccess(JSONObject result) {
                        taskList.clear();
                        try {
                            taskList.addAll(Parser.jsonObjectToScenarioTasks(result));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        tasksAdapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                    @Override
                    public void onError(JSONObject error) {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },getActivity(),scenarioName);
            }
        });

        playFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RunScenarioDialog dialog = new RunScenarioDialog(getActivity(),taskList.size(), new RunScenarioClick() {
                    @Override
                    public void runScenario(int from, int to) {

                        RequestMaker.runScenarioInRange(new StringCallback() {
                            @Override
                            public void onSuccess(String result) {
                                Toast.makeText(getActivity(),"SUCCESS",Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(String result) {
                                Toast.makeText(getActivity(),"FAILURE",Toast.LENGTH_SHORT).show();
                            }
                        },getActivity(),scenarioName,from,to);


                    }
                });
                dialog.show();

            }
        });

        return view;
    }


}
