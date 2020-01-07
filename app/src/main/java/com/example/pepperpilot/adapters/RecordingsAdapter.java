package com.example.pepperpilot.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pepperpilot.R;
import com.example.pepperpilot.models.Recording;

import java.util.List;

public class RecordingsAdapter extends RecyclerView.Adapter<RecordingsAdapter.MyViewHolder> {
    private List<Recording> recordingList;
    private Context context;


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView titleTV;
        TextView recordDateTimeTV;
        ImageView deleteRecordingB;

        public MyViewHolder(View view) {
            super(view);
            titleTV = view.findViewById(R.id.textViewRecordingName);
            recordDateTimeTV = view.findViewById(R.id.textViewRecordingDateTime);
            deleteRecordingB = view.findViewById(R.id.imageButtonDeleteRecording);
        }
    }


    public RecordingsAdapter(List<Recording> recordingList, Context context) {
        this.recordingList = recordingList;
        this.context = context;
    }


    public RecordingsAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recording_card,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecordingsAdapter.MyViewHolder holder, int position) {
        holder.titleTV.setText(recordingList.get(position).getName());
        holder.recordDateTimeTV.setText(recordingList.get(position).getName());

        holder.deleteRecordingB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Delete button clicked!",Toast.LENGTH_SHORT).show();
                //TODO implement delete button action
            }
        });
        
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Recording card clicked!", Toast.LENGTH_SHORT).show();
                //TODO implement on card click action
            }
        });

    }

    @Override
    public int getItemCount() {
        return recordingList.size();
    }
}