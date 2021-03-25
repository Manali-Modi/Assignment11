package com.example.assignment11;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class ImageAsyncLoader extends AsyncTaskLoader<String> {
    public ImageAsyncLoader(@NonNull Context context) {
        super(context);
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return "https://media.kasperskydaily.com/wp-content/uploads/sites/92/2019/12/09084248/android-device-identifiers-featured.jpg";
    }

    /*@Nullable
    @Override
    public Bitmap loadInBackground() {
        String s1 = "http://loremflickr.com/800/800?random=28";
        InputStream is = null;
        try {
            Log.d("tag","background");
            URL url = new URL(s1);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(30000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if(urlConnection.getResponseCode()!=HttpURLConnection.HTTP_OK){
                Log.d("tag","no connection");
            }
            else {
                Log.d("tag","connected");
            }
            is = urlConnection.getInputStream();
        } catch (Exception e) {
            Log.d("tag",e.getMessage());
            e.printStackTrace();
        }
        return BitmapFactory.decodeStream(is);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
    }

    @Nullable
    @Override
    protected Bitmap onLoadInBackground() {
        return super.onLoadInBackground();
    }

    @Override
    public void deliverResult(@Nullable Bitmap data) {
        super.deliverResult(data);
    }

    @Override
    protected void onStopLoading() {
        super.onStopLoading();
    }

    @Override
    protected boolean onCancelLoad() {
        return super.onCancelLoad();
    }*/
}
