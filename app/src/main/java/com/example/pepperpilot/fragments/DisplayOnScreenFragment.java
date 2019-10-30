package com.example.pepperpilot.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.pepperpilot.R;
import com.example.pepperpilot.adapters.MultimediaFileAdapter;
import com.example.pepperpilot.enums.MultimediaFileType;
import com.example.pepperpilot.models.MultimediaFile;

import java.util.ArrayList;

public class DisplayOnScreenFragment extends Fragment {

    private EditText searchET;
    private MultimediaFileAdapter multimediaFileAdapter;

    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display_on_screen, container, false);

        searchET = view.findViewById(R.id.editTextSearch);

        final ArrayList<MultimediaFile> list = new ArrayList<>();

        list.add(new MultimediaFile("panda.jpg", MultimediaFileType.IMAGE));
        list.add(new MultimediaFile("usmiech2.jpg", MultimediaFileType.IMAGE));
        list.add(new MultimediaFile("kaczuszki.mp4", MultimediaFileType.MOVIE));
        list.add(new MultimediaFile("zeszycik.jpg", MultimediaFileType.IMAGE));
        list.add(new MultimediaFile("smiech.mp4", MultimediaFileType.MOVIE));
        list.add(new MultimediaFile("koala_je_liscia.jpg", MultimediaFileType.IMAGE));
        list.add(new MultimediaFile("taniec_brzucha.mp4", MultimediaFileType.MOVIE));
        list.add(new MultimediaFile("koala_ziewa.jpg", MultimediaFileType.IMAGE));


        final RecyclerView recyclerView = view.findViewById(R.id.recyclerViewMultimedia);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        multimediaFileAdapter = new MultimediaFileAdapter(list, getActivity());
        recyclerView.setAdapter(multimediaFileAdapter);

        searchET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String text = searchET.getText().toString();

                ArrayList<MultimediaFile> sublist = new ArrayList<>();


                for (MultimediaFile file : list) {
                    if (count == 0) {
                        sublist = new ArrayList<>(list);
                        break;
                    } else if (file.getFileName().contains(text)) {
                        sublist.add(file);
                    }
                }

                multimediaFileAdapter = new MultimediaFileAdapter(sublist, getActivity());
                recyclerView.setAdapter(multimediaFileAdapter);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        return view;
    }
}
