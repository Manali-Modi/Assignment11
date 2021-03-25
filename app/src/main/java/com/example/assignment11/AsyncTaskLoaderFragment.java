package com.example.assignment11;

import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LifecycleOwner;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.Random;

public class AsyncTaskLoaderFragment extends Fragment{

    ImageView imgImage;
    Button btnLoad;
    LoaderManager manager;
    LoaderManager.LoaderCallbacks<String > callbacks;
    public AsyncTaskLoaderFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_async_task_loader, container, false);
        imgImage = view.findViewById(R.id.img_image);
        btnLoad = view.findViewById(R.id.btn_load);

        manager = LoaderManager.getInstance(this);
        callbacks = new LoaderManager.LoaderCallbacks<String>() {
            @NonNull
            @Override
            public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
                Toast.makeText(getContext(),"Loader created",Toast.LENGTH_SHORT).show();
                return new ImageAsyncLoader(getActivity());
            }

            @Override
            public void onLoadFinished(@NonNull Loader<String> loader, String data) {
                Toast.makeText(getContext(),"Loader finished",Toast.LENGTH_SHORT).show();
                RequestOptions options = new RequestOptions()
                        .centerCrop()
                        .placeholder(R.mipmap.ic_launcher_round)
                        .error(R.mipmap.ic_launcher_round);
                Glide.with(getContext()).load(data).apply(options).into(imgImage);
                //imgImage.setImageBitmap(data);
            }

            @Override
            public void onLoaderReset(@NonNull Loader<String> loader) {

            }
        };

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(),"Please wait...",Toast.LENGTH_SHORT).show();
                manager.initLoader(new Random().nextInt(100),null,callbacks).forceLoad();
            }
        });
        return view;
    }

}