package com.example.pepperpilot.fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.pepperpilot.R;
import com.example.pepperpilot.ScenariosSingleton;
import com.example.pepperpilot.activities.ScenariosActivity;
import com.example.pepperpilot.adapters.MultimediaFileAdapter;
import com.example.pepperpilot.enums.CallbackFragment;
import com.example.pepperpilot.enums.MultimediaFileType;
import com.example.pepperpilot.enums.TaskType;
import com.example.pepperpilot.models.Image;
import com.example.pepperpilot.models.MultimediaFile;

import java.util.ArrayList;


public class NewTaskShowImageVideoFragment extends Fragment {

    private Button saveB;

    private EditText titleET;
    private EditText descriptionET;
    private EditText imageUrlET;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_task_show_image_video,container,false);


        ArrayList<MultimediaFile> list = new ArrayList<>();
        list.add(new MultimediaFile("piesek.jpg", MultimediaFileType.IMAGE));
        list.add(new MultimediaFile("piesek2.jpg",MultimediaFileType.IMAGE));
        list.add(new MultimediaFile("sowa.jpg",MultimediaFileType.IMAGE));
        list.add(new MultimediaFile("zabawa1.mp4",MultimediaFileType.MOVIE));
        list.add(new MultimediaFile("kaczuski.mp4",MultimediaFileType.MOVIE));
        list.add(new MultimediaFile("krajobraz.jpg",MultimediaFileType.IMAGE));
        list.add(new MultimediaFile("wodospad.mp4",MultimediaFileType.MOVIE));

        RecyclerView recyclerView = view.findViewById(R.id.multimedia_recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        MultimediaFileAdapter multimediaFileAdapter = new MultimediaFileAdapter(list, getActivity());
        recyclerView.setAdapter(multimediaFileAdapter);


        saveB = view.findViewById(R.id.save_button);

        saveB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        return view;
    }

    private String getImageUrl() {
        return imageUrlET.getText().toString();
    }

}
