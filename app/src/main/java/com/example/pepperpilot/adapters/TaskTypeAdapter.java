package com.example.pepperpilot.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.pepperpilot.R;
import com.example.pepperpilot.interfaces.CallbackI;
import com.example.pepperpilot.interfaces.ClickI;
import com.example.pepperpilot.models.AddTaskType;

import java.util.List;

public class TaskTypeAdapter extends RecyclerView.Adapter<TaskTypeAdapter.MyViewHolder> {
    private Context context;
    private List<AddTaskType> addTaskTypes;
    private int selected = 0;
    private ClickI clickI;


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout taskTypeLayout;
        TextView taskTypeTV;
        ImageView taskTypeIV;

        public MyViewHolder(View view) {
            super(view);
            taskTypeTV = view.findViewById(R.id.textViewTaskType);
            taskTypeIV = view.findViewById(R.id.imageViewTaskType);
            taskTypeLayout = view.findViewById(R.id.linearLayoutAddTaskType);

        }
    }


    public TaskTypeAdapter(List<AddTaskType> addTaskTypes,Context context, ClickI clickI) {
        this.context = context;
        this.addTaskTypes = addTaskTypes;
        this.clickI = clickI;
    }


    public TaskTypeAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.scenario_task_type_card,parent,false);
        return new TaskTypeAdapter.MyViewHolder(view);
    }


    public void onBindViewHolder(final TaskTypeAdapter.MyViewHolder holder, int position) {
        AddTaskType addTaskType = addTaskTypes.get(holder.getAdapterPosition());

        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        int width = displayMetrics.widthPixels;
        int singleWidth = width / addTaskTypes.size();
        holder.taskTypeLayout.setMinimumWidth(singleWidth);
        holder.taskTypeIV.setImageResource(addTaskType.getIcon());
        holder.taskTypeTV.setText(addTaskType.getName());


        if(holder.getAdapterPosition() == selected) {
            holder.itemView.setBackgroundColor(Color.parseColor("#f1c40f"));
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickI.onClick(holder.getAdapterPosition());
                selected = holder.getAdapterPosition();
                notifyDataSetChanged();
            }
        });

    }


    public int getItemCount() {
        return addTaskTypes.size();
    }

}
