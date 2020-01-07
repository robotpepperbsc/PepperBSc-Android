package com.example.pepperpilot.dialogs;


import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.pepperpilot.R;
import com.example.pepperpilot.interfaces.RunScenarioClick;

import java.util.ArrayList;
import java.util.List;

public class RunScenarioDialog extends Dialog {

    private Spinner fromS;
    private Spinner toS;
    private Button runB;
    private RunScenarioClick runScenarioClick;

    private Context context;


    private int size;

    public RunScenarioDialog(Context context, int size, RunScenarioClick runScenarioClick) {
        super(context);
        this.context = context;
        this.size = size;
        this.runScenarioClick = runScenarioClick;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_run_scenario);

        fromS = findViewById(R.id.from_spinner);
        toS = findViewById(R.id.to_spinner);
        runB = findViewById(R.id.run_scenario_button);

        List<Integer> stepsList = new ArrayList<>();
        for(int i=1; i<=size; i++) {
            stepsList.add(i);
        }
        ArrayAdapter<Integer> dataAdapter = new ArrayAdapter<>(context,R.layout.spinner_item,stepsList);

        fromS.setAdapter(dataAdapter);
        toS.setAdapter(dataAdapter);


        runB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runScenarioClick.runScenario(Integer.parseInt(fromS.getSelectedItem().toString()),Integer.parseInt(toS.getSelectedItem().toString()));
                dismiss();
            }
        });

    }
}
