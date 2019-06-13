package com.example.pepperpilot.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pepperpilot.R;
import com.example.pepperpilot.models.Task;

import java.util.List;

public class TasksAdapter extends RecyclerView.Adapter<TasksAdapter.MyViewHolder> {
    private List<Task> taskList;
    private Context context;


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView taskNumberTV;
        TextView taskTypeTV;
        TextView taskNameTV;
        TextView descriptionTV;


        public MyViewHolder(View view) {
            super(view);
            taskNumberTV = view.findViewById(R.id.textViewTaskNumber);
            taskTypeTV = view.findViewById(R.id.textViewTaskType);
            taskNameTV = view.findViewById(R.id.textViewTaskName);
            descriptionTV = view.findViewById(R.id.textViewTaskDescription);
        }
    }

    public TasksAdapter(Context context, List<Task> taskList) {
        this.context = context;
        this.taskList = taskList;
    }


    public TasksAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.scenario_task_card,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TasksAdapter.MyViewHolder holder, final int position) {
        holder.taskNumberTV.setText(String.valueOf(position));
        holder.taskTypeTV.setText(taskList.get(position).getStringTaskType());
        holder.taskNameTV.setText(taskList.get(position).getTaskName());
        holder.descriptionTV.setText(taskList.get(position).getTaskDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Clicked " + holder.getAdapterPosition(),Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }
}
