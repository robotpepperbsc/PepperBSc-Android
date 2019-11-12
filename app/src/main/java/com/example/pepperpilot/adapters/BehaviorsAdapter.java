package com.example.pepperpilot.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pepperpilot.R;

import java.util.List;

public class BehaviorsAdapter extends RecyclerView.Adapter<BehaviorsAdapter.MyViewHolder> {
    private List<String> behaviors;
    private Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView behaviorNameTV;

        public MyViewHolder(View view) {
            super(view);
            behaviorNameTV = view.findViewById(R.id.behaviorTextView);
        }
    }

    public BehaviorsAdapter(List<String> behaviors, Context context) {
        this.behaviors = behaviors;
        this.context = context;
    }

    @Override
    public BehaviorsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.behavior_card,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final BehaviorsAdapter.MyViewHolder holder, final int position) {
        holder.behaviorNameTV.setText(behaviors.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Title " + position,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return behaviors.size();
    }
}
