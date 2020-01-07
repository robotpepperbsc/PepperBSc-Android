package com.example.pepperpilot.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.pepperpilot.requests.Parser;
import com.example.pepperpilot.R;
import com.example.pepperpilot.adapters.BehaviorsChooseAdapter;
import com.example.pepperpilot.interfaces.BehaviorCallback;
import com.example.pepperpilot.interfaces.JsonObjectCallback;
import com.example.pepperpilot.models.Behavior;
import com.example.pepperpilot.requests.RequestMaker;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;

public class NewTaskBehaviorActivity extends AppCompatActivity {

    private Button addTaskB;
    private TextView selectedBehaviorTV;
    private EditText descriptionET;

    private String behaviorName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task_behavior);

        addTaskB = findViewById(R.id.add_task_button);
        selectedBehaviorTV = findViewById(R.id.selected_behavior_text_view);
        descriptionET = findViewById(R.id.description_edit_text);

        RecyclerView recyclerView = findViewById(R.id.behaviors_recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        final LinkedList<Behavior> behaviors = new LinkedList<>();

        final BehaviorsChooseAdapter behaviorsChooseAdapter = new BehaviorsChooseAdapter(behaviors, this, new BehaviorCallback() {
            @Override
            public void onClick(Behavior behavior) {
                behaviorName = behavior.getBehaviorName();
                selectedBehaviorTV.setText(behavior.getBehaviorName());
            }
        });

        recyclerView.setAdapter(behaviorsChooseAdapter);


        addTaskB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("name", behaviorName);
                intent.putExtra("description", descriptionET.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });


        RequestMaker.getBehaviors(new JsonObjectCallback() {
            @Override
            public void onSuccess(JSONObject result) {
                try {
                    behaviors.addAll(Parser.jsonObjectToBehaviorsList(result));
                    behaviorsChooseAdapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(JSONObject error) {

            }
        }, this);

    }
}
