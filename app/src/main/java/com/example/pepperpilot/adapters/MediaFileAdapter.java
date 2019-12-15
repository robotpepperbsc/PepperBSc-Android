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
import com.example.pepperpilot.enums.MediaType;
import com.example.pepperpilot.interfaces.StringCallback;
import com.example.pepperpilot.models.Media;
import com.example.pepperpilot.requests.RequestMaker;

import java.util.List;

public class MediaFileAdapter extends RecyclerView.Adapter<MediaFileAdapter.MyViewHolder> {
    private List<Media> filesList;
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


    public MediaFileAdapter(List<Media> filesList, Context context) {
        this.filesList = filesList;
        this.context = context;
    }


    public MediaFileAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.multimedia_list_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final MediaFileAdapter.MyViewHolder holder, int position) {
        final Media file = filesList.get(position);


        Log.d("BIND - VISIBLE", file.getName());
        holder.nameTV.setText(file.getName());

        if (file.getType().equals(MediaType.IMAGE)) {
            holder.typeIV.setImageResource(R.drawable.ic_image_green_24dp);
        } else {
            holder.typeIV.setImageResource(R.drawable.ic_movie_orange_24dp);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, file.getName(), Toast.LENGTH_SHORT).show();

                RequestMaker.displayOnScreen(new StringCallback() {
                    @Override
                    public void onSuccess(String result) {

                    }

                    @Override
                    public void onError(String result) {

                    }
                },file,context);

            }
        });


    }

    @Override
    public int getItemCount() {
        return filesList.size();
    }
}