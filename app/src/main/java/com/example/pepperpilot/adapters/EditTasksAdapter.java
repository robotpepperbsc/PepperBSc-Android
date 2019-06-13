package com.example.pepperpilot.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pepperpilot.R;
import com.example.pepperpilot.models.Task;

import java.util.List;

public class EditTasksAdapter extends RecyclerView.Adapter<EditTasksAdapter.MyViewHolder> {
    private List<Task> taskList;
    private Context context;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView taskNumberTV;
        TextView taskTypeTV;
        TextView taskNameTV;
        TextView descriptionTV;
        ImageButton upB;
        ImageButton deleteB;
        ImageButton downB;

        public MyViewHolder(View view) {
            super(view);
            taskNumberTV = view.findViewById(R.id.textViewTaskNumber);
            taskTypeTV = view.findViewById(R.id.textViewTaskType);
            taskNameTV = view.findViewById(R.id.textViewTaskName);
            descriptionTV = view.findViewById(R.id.textViewTaskDescription);
            upB = view.findViewById(R.id.imageButtonUp);
            deleteB = view.findViewById(R.id.imageButtonDelete);
            downB = view.findViewById(R.id.imageButtonDown);
        }
    }

    public EditTasksAdapter(List<Task> taskList, Context context) {
        this.taskList = taskList;
        this.context = context;
    }

    @Override
    public EditTasksAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.scenario_edit_task_card,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final EditTasksAdapter.MyViewHolder holder, final int position) {
        holder.taskNumberTV.setText(String.valueOf(position));
        holder.taskTypeTV.setText(taskList.get(position).getStringTaskType());
        holder.taskNameTV.setText(taskList.get(position).getTaskName());
        holder.descriptionTV.setText(taskList.get(position).getTaskDescription());

        holder.upB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(holder.getAdapterPosition() > 0) {
                    Task temp = taskList.get(holder.getAdapterPosition());
                    taskList.set(holder.getAdapterPosition(), taskList.get(holder.getAdapterPosition() - 1));
                    taskList.set(holder.getAdapterPosition() - 1, temp);
                    notifyDataSetChanged();
                }
            }
        });

        holder.deleteB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"DELETE",Toast.LENGTH_SHORT).show();
                taskList.remove(holder.getAdapterPosition());
                notifyDataSetChanged();
            }
        });

        holder.downB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(holder.getAdapterPosition() < taskList.size()-1) {
                    Task temp = taskList.get(holder.getAdapterPosition());
                    taskList.set(holder.getAdapterPosition(),taskList.get(holder.getAdapterPosition()+1));
                    taskList.set(holder.getAdapterPosition()+1,temp);
                    notifyDataSetChanged();
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }
}
