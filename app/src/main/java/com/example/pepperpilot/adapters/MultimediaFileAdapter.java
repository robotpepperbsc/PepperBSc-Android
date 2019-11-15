package com.example.pepperpilot.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.pepperpilot.R;
import com.example.pepperpilot.enums.MultimediaFileType;
import com.example.pepperpilot.models.MultimediaFile;

import java.util.List;

public class MultimediaFileAdapter extends RecyclerView.Adapter<MultimediaFileAdapter.MyViewHolder> {
    private List<MultimediaFile> filesList;
    private Context context;


    public static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView nameTV;
        ImageView typeIV;


        public MyViewHolder(View view) {
            super(view);
            nameTV = view.findViewById(R.id.textViewName);
            typeIV = view.findViewById(R.id.imageViewMultimediaType);
        }
    }


    public MultimediaFileAdapter(List<MultimediaFile> filesList, Context context) {
        this.filesList = filesList;
        this.context = context;
    }


    public MultimediaFileAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.multimedia_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MultimediaFileAdapter.MyViewHolder holder, int position) {
        final MultimediaFile file = filesList.get(position);


        Log.d("BIND - VISIBLE", file.getFileName());
        holder.nameTV.setText(file.getFileName());

        if (file.getType().equals(MultimediaFileType.IMAGE)) {
            holder.typeIV.setImageResource(R.drawable.ic_image_green_24dp);
        } else {
            holder.typeIV.setImageResource(R.drawable.ic_movie_orange_24dp);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, file.getFileName(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    @Override
    public int getItemCount() {
        return filesList.size();
    }
}