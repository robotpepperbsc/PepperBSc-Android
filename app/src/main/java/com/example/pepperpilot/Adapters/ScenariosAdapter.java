package com.example.pepperpilot.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pepperpilot.R;
import com.example.pepperpilot.other.Scenario;

import java.util.List;

public class ScenariosAdapter extends RecyclerView.Adapter<ScenariosAdapter.MyViewHolder> {
    private List<Scenario> scenarioList;


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nameTV;

        public MyViewHolder(View view) {
            super(view);
            nameTV = view.findViewById(R.id.textViewScenarioName);
        }
    }


    public ScenariosAdapter(List<Scenario> scenarioList) {
        this.scenarioList = scenarioList;
    }


    public ScenariosAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.scenario_card,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ScenariosAdapter.MyViewHolder holder, int position) {
        holder.nameTV.setText(scenarioList.get(position).getName());


    }

    @Override
    public int getItemCount() {
        return scenarioList.size();
    }
}
