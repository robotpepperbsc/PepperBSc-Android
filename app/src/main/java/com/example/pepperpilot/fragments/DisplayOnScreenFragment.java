package com.example.pepperpilot.fragments;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.pepperpilot.R;
import com.example.pepperpilot.adapters.MultimediaFileAdapter;
import com.example.pepperpilot.enums.MultimediaFileType;
import com.example.pepperpilot.models.MultimediaFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import static android.app.Activity.RESULT_OK;
import static android.view.View.VISIBLE;

public class DisplayOnScreenFragment extends Fragment {

    public static final int PICK_MEDIA_REQUEST = 1;
    private EditText searchET;
    private MultimediaFileAdapter multimediaFileAdapter;

    private Button selectFileB;
    private Button uploadFileB;
    private ImageView previewIV;
    private EditText fileNameET;

    byte[] byteArray;


    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_display_on_screen, container, false);

        searchET = view.findViewById(R.id.editTextSearch);
        selectFileB = view.findViewById(R.id.select_file_button);
        uploadFileB = view.findViewById(R.id.upload_file_button);
        previewIV = view.findViewById(R.id.image_preview_image_view);
        fileNameET = view.findViewById(R.id.file_name_edit_text);

        ArrayList<MultimediaFile> list = new ArrayList<>();
        list.add(new MultimediaFile("piesek.jpg",MultimediaFileType.IMAGE));
        list.add(new MultimediaFile("piesek2.jpg",MultimediaFileType.IMAGE));
        list.add(new MultimediaFile("sowa.jpg",MultimediaFileType.IMAGE));
        list.add(new MultimediaFile("zabawa1.mp4",MultimediaFileType.MOVIE));
        list.add(new MultimediaFile("kaczuski.mp4",MultimediaFileType.MOVIE));
        list.add(new MultimediaFile("krajobraz.jpg",MultimediaFileType.IMAGE));
        list.add(new MultimediaFile("wodospad.mp4",MultimediaFileType.MOVIE));

        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewMultimedia);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        multimediaFileAdapter = new MultimediaFileAdapter(list, getActivity());
        recyclerView.setAdapter(multimediaFileAdapter);

        selectFileB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select movie or picture to upload"), PICK_MEDIA_REQUEST);
            }
        });

        uploadFileB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "UPLOAD", Toast.LENGTH_SHORT).show();
                previewIV.setVisibility(View.GONE);
                fileNameET.setVisibility(View.GONE);
            }
        });


        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PICK_MEDIA_REQUEST && resultCode == RESULT_OK) {

            Uri uri = data.getData();

            try {

                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), uri);

                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
                byteArray = stream.toByteArray();

                previewIV.setImageBitmap(bitmap);
                previewIV.setVisibility(VISIBLE);
                fileNameET.setVisibility(VISIBLE);
                fileNameET.setText(getFileName(uri));

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            Cursor cursor = getContext().getContentResolver().query(uri, null, null, null, null);
            try {
                if (cursor != null && cursor.moveToFirst()) {
                    result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                }
            } finally {
                cursor.close();
            }
        }
        if (result == null) {
            result = uri.getPath();
            int cut = result.lastIndexOf('/');
            if (cut != -1) {
                result = result.substring(cut + 1);
            }
        }
        return result;
    }
}
