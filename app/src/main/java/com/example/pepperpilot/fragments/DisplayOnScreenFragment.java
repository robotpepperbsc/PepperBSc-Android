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
import com.example.pepperpilot.adapters.MediaFileAdapter;
import com.example.pepperpilot.interfaces.JsonObjectCallback;
import com.example.pepperpilot.models.Media;
import com.example.pepperpilot.requests.RequestMaker;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

public class DisplayOnScreenFragment extends Fragment {

    public static final int PICK_MEDIA_REQUEST = 1;
    private EditText searchET;
    private MediaFileAdapter multimediaFileAdapter;
    private EditText durationET;
    private LinkedList<Media> listFull;
    private LinkedList<Media> listPartial;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listFull = new LinkedList<>();
        listPartial = new LinkedList<>();
    }

    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display_on_screen, container, false);


        searchET = view.findViewById(R.id.editTextSearch);
        swipeRefreshLayout = view.findViewById(R.id.swipe_refresh_layout);


        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewMultimedia);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        multimediaFileAdapter = new MediaFileAdapter(listPartial, getActivity());
        recyclerView.setAdapter(multimediaFileAdapter);

        swipeRefreshLayout.setRefreshing(true);
        RequestMaker.getMedia(new JsonObjectCallback() {
            @Override
            public void onSuccess(JSONObject result) {
                swipeRefreshLayout.setRefreshing(false);
                try {
                    listPartial.clear();
                    listFull.clear();

                    listFull.addAll(Parser.jsonObjectToMediaList(result));
                    listPartial.addAll(listFull);

                    multimediaFileAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(JSONObject error) {
                swipeRefreshLayout.setRefreshing(false);
            }
        }, getActivity());



        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                RequestMaker.getMedia(new JsonObjectCallback() {
                    @Override
                    public void onSuccess(JSONObject result) {
                        try {

                            listPartial.clear();
                            listFull.clear();

                            listFull.addAll(Parser.jsonObjectToMediaList(result));
                            listPartial.addAll(listFull);

                            multimediaFileAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        swipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onError(JSONObject error) {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, getActivity());
            }
        });

        searchET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                listPartial.clear();

                for (Media m : listFull) {
                    if (m.getName().contains(s.toString())) listPartial.add(m);
                }

                //Wybierz z listy tylko te slowa ktore zawieraja tekst
                multimediaFileAdapter.notifyDataSetChanged();
            }
        });


        return view;
    }

}
