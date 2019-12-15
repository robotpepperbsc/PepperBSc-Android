package com.example.pepperpilot.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;

import com.example.pepperpilot.R;
import com.example.pepperpilot.enums.TaskType;
import com.example.pepperpilot.models.Speech;

import java.util.ArrayList;
import java.util.List;

public class NewTaskSpeechActivity extends AppCompatActivity {


    private Button addTaskB;
    private Spinner languageS;

    private EditText taskNameET;
    private EditText textET;
    private EditText speedET;
    private SeekBar voiceVolumeSB;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task_speech);

        languageS = findViewById(R.id.language_spinner);
        taskNameET = findViewById(R.id.task_name_edit_text);
        textET = findViewById(R.id.text_edit_text);
        speedET = findViewById(R.id.voice_speed_edit_text);
        voiceVolumeSB = findViewById(R.id.voice_volume_seek_bar);


        List<String> languages = new ArrayList<>();
        languages.add("Polish");
        languages.add("English");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, languages);

        languageS.setAdapter(dataAdapter);

        addTaskB = findViewById(R.id.add_task_button);

        addTaskB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();


                String text = textET.getText().toString();
                String description = generateDescription();
                String taskName = taskNameET.getText().toString();
                float volume = (float) (voiceVolumeSB.getProgress() / 100.0);
                int speed = Integer.parseInt(speedET.getText().toString());
                String language = languageS.getSelectedItem().toString();

                intent.putExtra("description", description);
                intent.putExtra("taskName",taskName);
                intent.putExtra("text", text);
                intent.putExtra("volume", volume);
                intent.putExtra("speed", speed);
                intent.putExtra("language", language);


                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    private String generateDescription() {
        StringBuilder stringBuilder = new StringBuilder();

        if(textET.getText().toString().length() < 101) {
            stringBuilder.append(textET.getText().toString());
        } else {
            stringBuilder.append(textET.getText().toString().substring(0,100)).append("...");
        }

        stringBuilder.append("\n\n");
        stringBuilder.append("Parametry:\n");
        stringBuilder.append("Głośność: ").append(voiceVolumeSB.getProgress() / 100.0).append("\n");
        stringBuilder.append("Szybkość: ").append(speedET.getText().toString()).append("\n");
        stringBuilder.append("Język: ").append(languageS.getSelectedItem().toString()).append("\n");


        return stringBuilder.toString();
    }
}
