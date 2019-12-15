package com.example.pepperpilot.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.example.pepperpilot.R;
import com.example.pepperpilot.enums.CallbackFragment;


public class TaskChoiceDialog extends Dialog {

    public Button movementB;
    public Button speechB;
    public Button multimediaB;
    public Button behaviorsB;
    private OnClickListener listener;

    public interface OnClickListener {
        void taskEvent(CallbackFragment fragment);
    }

    public TaskChoiceDialog(Context context, OnClickListener listener) {
        super(context);
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_choice_task);

        movementB = findViewById(R.id.movement_button);
        speechB = findViewById(R.id.speech_button);
        multimediaB = findViewById(R.id.multimedia_button);
        behaviorsB = findViewById(R.id.behaviors_button);

        movementB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.taskEvent(CallbackFragment.MOVEMENT);
                dismiss();
            }
        });
        speechB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.taskEvent(CallbackFragment.SPEECH);
                dismiss();
            }
        });
        multimediaB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.taskEvent(CallbackFragment.MULTIMEDIA);
                dismiss();
            }
        });
        behaviorsB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.taskEvent(CallbackFragment.BEHAVIOR);
                dismiss();
            }
        });
    }
}
