package com.example.pepperpilot.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.pepperpilot.requests.Parser;
import com.example.pepperpilot.R;
import com.example.pepperpilot.adapters.BehaviorsAdapter;
import com.example.pepperpilot.interfaces.JsonObjectCallback;
import com.example.pepperpilot.models.Behavior;
import com.example.pepperpilot.requests.RequestMaker;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

public class BehaviorsFragment extends Fragment {
    private List<Behavior> behaviorsListFull;
    private List<Behavior> behaviorsListPartial;
    private SwipeRefreshLayout swipeRefreshLayout;
    private EditText searchBehaviorET;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        behaviorsListFull = new LinkedList<>();
        behaviorsListPartial = new LinkedList<>();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_behaviors, container, false);

        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);
        searchBehaviorET = view.findViewById(R.id.search_edit_text);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewBehaviors);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        final BehaviorsAdapter adapter = new BehaviorsAdapter(behaviorsListPartial, getActivity());
        recyclerView.setAdapter(adapter);


        swipeRefreshLayout.setRefreshing(true);
        RequestMaker.getBehaviors(new JsonObjectCallback() {
            @Override
            public void onSuccess(JSONObject result) {

                try {
                    behaviorsListPartial.clear();
                    behaviorsListFull.clear();
                    behaviorsListFull.addAll(Parser.jsonObjectToBehaviorsList(result));
                    behaviorsListPartial.addAll(behaviorsListFull);
                    adapter.notifyDataSetChanged();
                }catch (JSONException e) {
                    e.printStackTrace();
                }

                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onError(JSONObject error) {
                swipeRefreshLayout.setRefreshing(false);
            }
        },getActivity());



        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                RequestMaker.getBehaviors(new JsonObjectCallback() {
                    @Override
                    public void onSuccess(JSONObject result) {
                        try {
                            behaviorsListFull.clear();
                            behaviorsListPartial.clear();

                            behaviorsListFull.addAll(Parser.jsonObjectToBehaviorsList(result));
                            behaviorsListPartial.addAll(behaviorsListFull);
                            adapter.notifyDataSetChanged();


                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        swipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onError(JSONObject error) {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, getContext());


            }
        });

        searchBehaviorET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                behaviorsListPartial.clear();

                for (Behavior b : behaviorsListFull) {
                    if (b.getBehaviorName().contains(s.toString())) behaviorsListPartial.add(b);
                }


                //Wybierz z listy tylko te slowa ktore zawieraja tekst

                adapter.notifyDataSetChanged();

            }
        });

        return view;
    }

}
