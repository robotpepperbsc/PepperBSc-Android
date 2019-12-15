package com.example.pepperpilot.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pepperpilot.R;
import com.example.pepperpilot.activities.ScenariosActivity;
import com.example.pepperpilot.enums.Mode;
import com.example.pepperpilot.interfaces.StringCallback;
import com.example.pepperpilot.models.Scenario;
import com.example.pepperpilot.requests.RequestMaker;

import java.util.List;

public class ScenariosAdapter extends RecyclerView.Adapter<ScenariosAdapter.MyViewHolder> {
    private List<Scenario> scenarioList;
    private Context context;


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nameTV;
        TextView descriptionTV;
        TextView editDateTimeTV;
        FloatingActionButton editB;
        FloatingActionButton deleteB;

        public MyViewHolder(View view) {
            super(view);
            nameTV = view.findViewById(R.id.textViewScenarioName);
            editB = view.findViewById(R.id.imageButtonEdit);
            deleteB = view.findViewById(R.id.imageButtonDelete);
            descriptionTV = view.findViewById(R.id.textViewScenarioDescription);
            editDateTimeTV = view.findViewById(R.id.textViewEditDateTime);
        }
    }


    public ScenariosAdapter(Context context, List<Scenario> scenarioList) {
        this.context = context;
        this.scenarioList = scenarioList;
    }


    public ScenariosAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.scenario_card, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ScenariosAdapter.MyViewHolder holder, final int position) {
        holder.nameTV.setText(scenarioList.get(position).getName());
        holder.descriptionTV.setText(scenarioList.get(position).getDescription());
        holder.editDateTimeTV.setText(scenarioList.get(position).getLastDateTimeEdited());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ScenariosActivity.class);
                intent.putExtra("mode", Mode.SHOW);
                intent.putExtra("position", holder.getAdapterPosition());
                intent.putExtra("name", scenarioList.get(holder.getAdapterPosition()).getName());
                context.startActivity(intent);
            }
        });

        holder.editB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ScenariosActivity.class);
                intent.putExtra("mode", Mode.EDIT);
                intent.putExtra("position", holder.getAdapterPosition());
                intent.putExtra("name", scenarioList.get(holder.getAdapterPosition()).getName());
                intent.putExtra("description",scenarioList.get(holder.getAdapterPosition()).getDescription());
                context.startActivity(intent);
            }
        });

        holder.deleteB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Delete clicked", Toast.LENGTH_SHORT).show();


                RequestMaker.removeScenario(new StringCallback() {
                    @Override
                    public void onSuccess(String result) {
                        //ScenariosSingleton.getInstance().getScenarios().remove(holder.getAdapterPosition());
                        Toast.makeText(context, "Usunięto pomyślnie!", Toast.LENGTH_SHORT).show();
                        scenarioList.remove(position);
                        notifyDataSetChanged();
                    }

                    @Override
                    public void onError(String result) {
                        Toast.makeText(context, "Problem z usunięciem!", Toast.LENGTH_SHORT).show();
                    }
                }, context, scenarioList.get(position).getName());

                notifyDataSetChanged();


            }
        });

    }

    @Override
    public int getItemCount() {
        return scenarioList.size();
    }
}
