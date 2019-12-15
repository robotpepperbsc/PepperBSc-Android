package com.example.pepperpilot.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pepperpilot.Parser;
import com.example.pepperpilot.R;
import com.example.pepperpilot.adapters.MediaChooseFileAdapter;
import com.example.pepperpilot.interfaces.JsonObjectCallback;
import com.example.pepperpilot.interfaces.MediaCallback;
import com.example.pepperpilot.models.Media;
import com.example.pepperpilot.requests.RequestMaker;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

public class NewTaskMultimediaActivity extends AppCompatActivity {
    private MediaChooseFileAdapter mediaChooseFileAdapter;
    private Button addTaskB;
    private LinkedList<Media> listFull;
    private LinkedList<Media> listPartial;
    private TextView textView;
    private EditText descriptionET;
    private Media selectedMedia;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task_multimedia);

        addTaskB = findViewById(R.id.add_task_button);
        descriptionET = findViewById(R.id.description_edit_text);
        textView = findViewById(R.id.media_selection_text_view);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

        listFull = new LinkedList<>();
        listPartial = new LinkedList<>();

        RecyclerView recyclerView = findViewById(R.id.multimedia_recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        mediaChooseFileAdapter = new MediaChooseFileAdapter(listPartial, this, new MediaCallback() {
            @Override
            public void onClick(Media media) {
                selectedMedia = media;
                textView.setText(media.getName());

            }
        });


        recyclerView.setAdapter(mediaChooseFileAdapter);

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

                    mediaChooseFileAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onError(JSONObject error) {
                swipeRefreshLayout.setRefreshing(false);
            }
        }, this);



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

                            mediaChooseFileAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        swipeRefreshLayout.setRefreshing(false);
                    }

                    @Override
                    public void onError(JSONObject error) {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, NewTaskMultimediaActivity.this);
            }
        });







        addTaskB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("name",selectedMedia.getName());
                intent.putExtra("mediaType",selectedMedia.getType());
                intent.putExtra("description",descriptionET.getText().toString());
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }
}
