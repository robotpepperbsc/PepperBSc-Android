package com.example.pepperpilot.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.example.pepperpilot.R;
import com.example.pepperpilot.enums.MovementDirection;

import java.util.ArrayList;
import java.util.List;

import static com.example.pepperpilot.enums.MovementDirection.BACK;
import static com.example.pepperpilot.enums.MovementDirection.FORWARD;
import static com.example.pepperpilot.enums.MovementDirection.LEFT;
import static com.example.pepperpilot.enums.MovementDirection.RIGHT;

public class NewTaskMovementActivity extends AppCompatActivity {

    private Button addTaskB;
    private Spinner rotateS;

    private RadioButton forwardRB;
    private RadioButton backwardRB;
    private RadioButton leftRB;
    private RadioButton rightRB;

    private LinearLayout distanceLL;
    private LinearLayout rotationLL;

    private EditText descriptionET;
    private EditText distanceET;
    private MovementDirection direction = MovementDirection.FORWARD;

    private String taskName;
    private String description;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task_movement);

        addTaskB = findViewById(R.id.add_task_button);
        rotateS = findViewById(R.id.rotate_spinner);

        distanceET = findViewById(R.id.distance_edit_text);

        forwardRB = findViewById(R.id.forward_move_radio_button);
        backwardRB = findViewById(R.id.backward_move_radio_button);
        leftRB = findViewById(R.id.left_rotate_radio_button);
        rightRB = findViewById(R.id.right_rotate_radio_button);

        distanceLL = findViewById(R.id.distance_linear_layout);
        rotationLL = findViewById(R.id.rotation_linear_layout);

        final List<String> rotationDegree = new ArrayList<>();
        rotationDegree.add("30");
        rotationDegree.add("45");
        rotationDegree.add("60");
        rotationDegree.add("90");
        rotationDegree.add("135");
        rotationDegree.add("180");
        rotationDegree.add("270");
        rotationDegree.add("360");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(NewTaskMovementActivity.this, R.layout.spinner_item, rotationDegree);

        rotateS.setAdapter(dataAdapter);

        addTaskB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float distance;


                if(direction.equals(FORWARD)) {
                    description = "W przód o " + distanceET.getText().toString() + " metrów.";
                } else if(direction.equals(BACK)) {
                    description = "W tył o " + distanceET.getText().toString() + " metrów.";
                }else if(direction.equals(LEFT)) {
                    description = "W lewo o " + rotateS.getSelectedItem().toString() + " stopni.";
                } else if(direction.equals(RIGHT)) {
                    description = "W prawo o " + rotateS.getSelectedItem().toString() + " stopni.";
                }

                Intent intent = new Intent();
                intent.putExtra("description",description);
                intent.putExtra("taskName",taskName);
                intent.putExtra("movementDirection",direction);

                if(direction.equals(MovementDirection.FORWARD) || direction.equals(BACK)) {
                    distance = Float.valueOf(distanceET.getText().toString());
                    intent.putExtra("distance",distance);
                } else {
                    distance = (float)degreeToRad(Integer.valueOf(rotateS.getSelectedItem().toString()));
                    intent.putExtra("distance",distance);
                }

                setResult(RESULT_OK,intent);
                finish();
            }
        });

        forwardRB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                distanceLL.setVisibility(View.VISIBLE);
                rotationLL.setVisibility(View.GONE);
                direction = FORWARD;
                taskName = "do przodu";
            }
        });

        backwardRB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                distanceLL.setVisibility(View.VISIBLE);
                rotationLL.setVisibility(View.GONE);
                direction = BACK;
                taskName = "do tyłu";
            }
        });

        leftRB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                distanceLL.setVisibility(View.GONE);
                rotationLL.setVisibility(View.VISIBLE);
                direction = LEFT;
                taskName = "w lewo";
            }
        });

        rightRB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                distanceLL.setVisibility(View.GONE);
                rotationLL.setVisibility(View.VISIBLE);
                direction = RIGHT;
                taskName = "w prawo";
            }
        });
    }

    private double degreeToRad(int degree) {
        return Math.round((Math.PI/180.0)*degree * 100.0)/100.0;
    }
}
