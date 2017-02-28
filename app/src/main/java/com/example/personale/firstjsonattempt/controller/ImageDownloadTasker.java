package com.example.personale.firstjsonattempt.controller;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.personale.firstjsonattempt.R;

import java.lang.ref.WeakReference;

/**
 * Created by personale on 27/02/2017.
 */

public class ImageDownloadTasker extends AsyncTask<String, Void, Bitmap> {
    private ProgressDialog progress;
    private WeakReference<ImageView> imageViewWeakReference;

    public ImageDownloadTasker(ImageView imageView){
        imageViewWeakReference = new WeakReference<>(imageView);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        return ServiceUils.downloadBitmap(params[0]);
    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        super.onPostExecute(bitmap);

        if(isCancelled()) {
            bitmap = null;
        }

        if(imageViewWeakReference != null){
            ImageView imageView = imageViewWeakReference.get();

            if(imageView != null) {
                if(bitmap != null) {
                    imageView.setImageBitmap(bitmap);
                } else {
                    Drawable placeHolder = ContextCompat.getDrawable(imageView.getContext(), R.drawable.face_profile);
                    imageView.setImageDrawable(placeHolder);
                }
            }
        }
    }
}
