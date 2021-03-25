package com.example.assignment11;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class CursorFragment extends Fragment implements LoaderManager.LoaderCallbacks<Cursor> {

    ListView lstVideo;
    Uri videoUri;
    public static final int REQ_CODE = 99;
    ArrayList<String> videoList;

    public CursorFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cursor, container, false);
        lstVideo = view.findViewById(R.id.lst_video);
        videoList = new ArrayList<>();

        if(ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(getActivity(),new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},REQ_CODE);
        }
        else {
            LoaderManager.getInstance(this).initLoader(0,null,this);
        }
        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQ_CODE && grantResults[0]==PackageManager.PERMISSION_GRANTED){
            LoaderManager.getInstance(this).initLoader(0,null,this);
        }
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        videoUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        return new CursorLoader(getContext(),videoUri,null,null,null,null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        data = getContext().getContentResolver().query(videoUri,null,null,null,null);
        if(data!=null){
            while (data.moveToNext()){
                String video = data.getString(data.getColumnIndex(MediaStore.Video.Media.TITLE));
                videoList.add(video);
            }
            data.close();
        }

        lstVideo.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1,videoList));
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

    }
}