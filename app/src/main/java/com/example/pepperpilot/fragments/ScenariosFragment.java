package com.example.pepperpilot.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.pepperpilot.requests.Parser;
import com.example.pepperpilot.activities.ScenariosActivity;
import com.example.pepperpilot.adapters.ScenariosAdapter;
import com.example.pepperpilot.R;
import com.example.pepperpilot.enums.Mode;
import com.example.pepperpilot.interfaces.JsonObjectCallback;
import com.example.pepperpilot.models.Scenario;
import com.example.pepperpilot.requests.RequestMaker;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

public class ScenariosFragment extends Fragment {
    private List<Scenario> scenarioList;
    private FloatingActionButton floatingActionButton;
    private ScenariosAdapter scenariosAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        scenarioList = new LinkedList<>();

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_scenarios, container, false);

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewScenarios);
        floatingActionButton = view.findViewById(R.id.floatingActionButtonAdScenario);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        scenariosAdapter = new ScenariosAdapter(getActivity(), scenarioList);
        recyclerView.setAdapter(scenariosAdapter);


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ScenariosActivity.class);
                intent.putExtra("mode", Mode.CREATE);
                startActivity(intent);
            }
        });

        scenarioList.add(new Scenario("Pokaz w szkole nr 2","Tutaj znajduje się opis scenariusza związanego z pokazem robota w szkole nr 2.","2019-11-20, 15:43"));
        scenarioList.add(new Scenario("Pokaz demonstracyjny","Tutaj znajduje się opis pokazu demonstracyjnego.","2019-11-21, 12:13"));
        scenarioList.add(new Scenario("Pokaz dla przedszkola","Tutaj znajduje się opis scenariusza związanego z pokazem robota w pzedszkolu.","2019-11-22, 13:53"));
        scenariosAdapter.notifyDataSetChanged();
        /*
        swipeRefreshLayout.setRefreshing(true);
        RequestMaker.getScenarios(new JsonObjectCallback() {
            @Override
            public void onSuccess(JSONObject result) {
                scenarioList.clear();
                try {
                    scenarioList.addAll(Parser.jsonObjectToScenariosList(result));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                scenariosAdapter.notifyDataSetChanged();
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onError(JSONObject error) {
                swipeRefreshLayout.setRefreshing(false);
            }
        },getActivity());
*/
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeRefreshLayout.setRefreshing(true);
                scenarioList.clear();

                RequestMaker.getScenarios(new JsonObjectCallback() {
                    @Override
                    public void onSuccess(JSONObject result) {

                        try {
                            scenarioList.addAll(Parser.jsonObjectToScenariosList(result));
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        scenariosAdapter.notifyDataSetChanged();
                        swipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onError(JSONObject error) {
                        Toast.makeText(getActivity(),"Błąd pobierania scenariuszy!",Toast.LENGTH_SHORT).show();
                        swipeRefreshLayout.setRefreshing(false);
                    }
                },getActivity());
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        scenariosAdapter.notifyDataSetChanged();
    }
}
