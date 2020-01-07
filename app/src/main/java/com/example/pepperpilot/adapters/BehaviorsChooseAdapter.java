package com.example.pepperpilot.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pepperpilot.R;
import com.example.pepperpilot.interfaces.BehaviorCallback;
import com.example.pepperpilot.models.Behavior;

import java.util.List;

public class BehaviorsChooseAdapter extends RecyclerView.Adapter<BehaviorsChooseAdapter.MyViewHolder> {
    private List<Behavior> behaviors;
    private Context context;
    private BehaviorCallback behaviorCallback;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView behaviorNameTV;

        public MyViewHolder(View view) {
            super(view);
            behaviorNameTV = view.findViewById(R.id.behaviorTextView);
        }
    }

    public BehaviorsChooseAdapter(List<Behavior> behaviors, Context context, BehaviorCallback behaviorCallback) {
        this.behaviors = behaviors;
        this.context = context;
        this.behaviorCallback = behaviorCallback;
    }

    @Override
    public BehaviorsChooseAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.behavior_card,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final BehaviorsChooseAdapter.MyViewHolder holder, final int position) {
        holder.behaviorNameTV.setText(behaviors.get(position).getBehaviorName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                behaviorCallback.onClick(behaviors.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return behaviors.size();
    }
}
